package com.tricentis.demowebshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage {
    private final SelenideElement productName = $("[itemprop=name]");
    private final SelenideElement productPrice = $("[itemprop=price]");
    private final SelenideElement processorOptions = $(".attributes dl dd");
    private final ElementsCollection processors = processorOptions.$$("input[type=radio]");
    private final SelenideElement quantityInput = $("input.qty-input");
    private final SelenideElement addToCartButton = $("input.add-to-cart-button");
    private final SelenideElement successNotification = $("div.bar-notification.success");
    private final SelenideElement cartQuantity = $("span.cart-qty");
    private final SelenideElement cartLink = $("a.ico-cart");

    private BigDecimal expectedUnitPrice;
    private int quantity = 1;

    public ProductPage selectProcessor(int index) {
        // index 0 = slow, 1 = medium, 2 = fast
        if (index < 0 || index > 2) {
            throw new IllegalArgumentException("Processor index must be 0, 1 or 2");
        }
        SelenideElement processor = processors.get(index);
        processor.click();
        processor.shouldBe(checked);

        String label = processorOptions.$("label[for='" + processor.getAttribute("id") + "']").getText();
        Matcher surcharge = Pattern.compile("\\[\\+([\\d,.]+)\\]").matcher(label);
        BigDecimal extraPrice = surcharge.find()
                ? new BigDecimal(surcharge.group(1).replace(",", "")) : BigDecimal.ZERO;
        expectedUnitPrice = new BigDecimal(productPrice.getText().replace(",", "").trim())
                .add(extraPrice);
        return this;
    }

    public ProductPage enterQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.quantity = quantity;
        quantityInput.setValue(String.valueOf(quantity));
        return this;
    }

    public ProductPage addToCart() {
        if (expectedUnitPrice == null) {
            throw new IllegalStateException("Select a processor before adding to cart");
        }
        addToCartButton.click();
        successNotification.shouldBe(visible);
        cartQuantity.shouldHave(exactText("(" + quantity + ")"));
        return this;
    }

    public CartPage openCart() {
        String name = productName.getText();
        String processorLabel = processorOptions.$("input:checked + label").getText();
        cartLink.click();
        return new CartPage(name, expectedUnitPrice, quantity, processorLabel.split("\\s")[0]);
    }
}
