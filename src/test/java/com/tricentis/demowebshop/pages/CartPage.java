package com.tricentis.demowebshop.pages;
import io.qameta.allure.Step;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CartPage extends BasePage {
    private final SelenideElement productRow = $(".cart-item-row");
    private final SelenideElement productName = productRow.$("a.product-name");
    private final SelenideElement productAttributes = productRow.$(".attributes");
    private final SelenideElement quantityInput = productRow.$("input.qty-input");
    private final SelenideElement unitPrice = productRow.$(".product-unit-price");
    private final SelenideElement subtotal = productRow.$(".product-subtotal");
    @Step("Получить название товара в корзине")
    public String getProductName() {
        return productName.shouldBe(visible).getText();
    }

    @Step("Получить выбранную комплектацию товара в корзине")
    public String getAttributes() {
        return productAttributes.shouldBe(visible).getText();
    }

    @Step("Получить количество товара в корзине")
    public String getQuantity() {
        return quantityInput.shouldBe(visible).getValue();
    }

    @Step("Получить цену единицы товара в корзине")
    public String getUnitPrice() {
        return unitPrice.shouldBe(visible).getText();
    }

    @Step("Получить стоимость позиции в корзине")
    public String getSubtotal() {
        return subtotal.shouldBe(visible).getText();
    }
}
