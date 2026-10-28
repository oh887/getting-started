package com.tricentis.demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class BasePage {
    private final SelenideElement cartQuantity = $("span.cart-qty");
    private final SelenideElement cartLink = $("a.ico-cart");
    private final SelenideElement computersLink = $("ul.top-menu a[href='/computers']");
    private final SelenideElement desktopsLink = $("ul.top-menu a[href='/desktops']");

    @Step("Проверить, что счетчик корзины показывает количество товаров: {quantity}")
    public BasePage checkCartQuantity(int quantity) {
        cartQuantity.shouldHave(exactText("(" + quantity + ")"));
        return this;
    }

    @Step("Открыть корзину интернет-магазина")
    public CartPage openCart() {
        cartLink.click();
        return new CartPage();
    }

    @Step("Открыть каталог настольных компьютеров")
    public CatalogPage openDesktops() {
        computersLink.hover();
        desktopsLink.click();
        return new CatalogPage();
    }
}
