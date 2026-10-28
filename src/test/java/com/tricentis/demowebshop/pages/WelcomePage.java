package com.tricentis.demowebshop.pages;
import io.qameta.allure.Step;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class WelcomePage extends BasePage {

    private final SelenideElement registerButton = $("a.ico-register");
    private final SelenideElement loginButton = $("a.ico-login");

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
