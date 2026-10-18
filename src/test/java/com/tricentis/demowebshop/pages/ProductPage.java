package com.tricentis.demowebshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.math.BigDecimal;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage {
    private final SelenideElement productName = $("[itemprop=name]");
    private final SelenideElement productPrice = $("[itemprop=price]");
    private final SelenideElement processorOptions = $(".attributes dl dd");
    private final ElementsCollection processors = processorOptions.$$("input[type=radio]");
    private final ElementsCollection processorLabels = processorOptions.$$("label");
    private final SelenideElement quantityInput = $("input.qty-input");
    private final SelenideElement addToCartButton = $("input.add-to-cart-button");
    private final SelenideElement successNotification = $("div.bar-notification.success");
    private final SelenideElement cartQuantity = $("span.cart-qty");
    private final SelenideElement cartLink = $("a.ico-cart");

    private BigDecimal expectedUnitPrice;
    private String processorName;
    private int quantity = 1;

    public ProductPage selectProcessor(int index) {
        // index 0 = slow, 1 = medium, 2 = fast
        if (index < 0 || index > 2) {
            throw new IllegalArgumentException("Processor index must be 0, 1 or 2");
        }
        SelenideElement processor = processors.get(index);
        processor.click();
        processor.shouldBe(checked);

        String label = processorLabels.get(index).getText();
        expectedUnitPrice = new BigDecimal(productPrice.getText().trim());
        processorName = label;

        // У платных процессоров подпись содержит доплату, например Medium [+15.00].
        if (label.contains("[+")) {
            int priceStart = label.indexOf("[+");
            int priceEnd = label.indexOf("]");
            String extraPrice = label.substring(priceStart + 2, priceEnd);
            expectedUnitPrice = expectedUnitPrice.add(new BigDecimal(extraPrice));
            processorName = label.substring(0, priceStart).trim();
        }
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
        cartLink.click();
        return new CartPage(name, expectedUnitPrice, quantity, processorName);
    }
}
