package com.tricentis.demowebshop.pages;
import io.qameta.allure.Step;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class WelcomePage {

    private final SelenideElement registerButton = $("a.ico-register");
    private final SelenideElement loginButton = $("a.ico-login");
    private final SelenideElement computersLink = $("ul.top-menu a[href='/computers']");
    private final SelenideElement desktopsLink = $("ul.top-menu a[href='/desktops']");

    @Step("Открыть каталог настольных компьютеров")
    public CatalogPage openDesktops() {
        computersLink.hover();
        desktopsLink.click();
        return new CatalogPage();
    }

    @Step("Открыть страницу входа в интернет-магазин")
    public LoginPage openLogin() {
        loginButton.click();
        return new LoginPage();
    }

    @Step("Открыть страницу регистрации в интернет-магазине")
    public RegistrationPage openRegistration() {
        registerButton.click();

    return new RegistrationPage();
    }
}
