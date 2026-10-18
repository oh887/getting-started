package com.tricentis.demowebshop.pages;

import com.codeborne.selenide.SelenideElement;

import java.math.BigDecimal;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    private final SelenideElement productRow = $(".cart-item-row");
    private final SelenideElement productName = productRow.$("a.product-name");
    private final SelenideElement productAttributes = productRow.$(".attributes");
    private final SelenideElement quantityInput = productRow.$("input.qty-input");
    private final SelenideElement unitPrice = productRow.$(".product-unit-price");
    private final SelenideElement subtotal = productRow.$(".product-subtotal");
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

    public CartPage checkProductName() {
        productName.shouldHave(exactText(expectedName));
        return this;
    }

    public CartPage checkProcessor() {
        productAttributes.shouldHave(text("Processor: " + expectedProcessor));
        return this;
    }

    public CartPage checkQuantity() {
        quantityInput.shouldHave(value(String.valueOf(expectedQuantity)));
        return this;
    }

    public CartPage checkUnitPrice() {
        unitPrice.shouldHave(exactText(expectedUnitPrice.toPlainString()));
        return this;
    }

    public CartPage checkSubtotal() {
        BigDecimal totalPrice = expectedUnitPrice.multiply(BigDecimal.valueOf(expectedQuantity));
        subtotal.shouldHave(exactText(totalPrice.toPlainString()));
        return this;
    }
}
