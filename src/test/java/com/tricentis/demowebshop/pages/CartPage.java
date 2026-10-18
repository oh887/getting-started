package com.tricentis.demowebshop.pages;

import com.codeborne.selenide.SelenideElement;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    private final SelenideElement productRow = $(".cart-item-row");
    private final String expectedName;
    private final BigDecimal expectedUnitPrice;
    private final int expectedQuantity;
    private final String expectedProcessor;

    public CartPage(String name, BigDecimal unitPrice, int quantity, String processor) {
        expectedName = name;
        expectedUnitPrice = unitPrice;
        expectedQuantity = quantity;
        expectedProcessor = processor;
    }

    public CartPage verifyProduct() {
        productRow.$("a.product-name").shouldHave(exactText(expectedName));
        productRow.$(".attributes").shouldHave(text("Processor: " + expectedProcessor));
        productRow.$("input.qty-input").shouldHave(value(String.valueOf(expectedQuantity)));
        productRow.$(".product-unit-price").shouldHave(exactText(formatPrice(expectedUnitPrice)));
        productRow.$(".product-subtotal").shouldHave(exactText(
                formatPrice(expectedUnitPrice.multiply(BigDecimal.valueOf(expectedQuantity)))));
        return this;
    }

    private String formatPrice(BigDecimal price) {
        return price.setScale(2, RoundingMode.UNNECESSARY).toPlainString();
    }
}
