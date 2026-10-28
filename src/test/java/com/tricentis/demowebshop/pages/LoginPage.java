package com.tricentis.demowebshop.pages;
import io.qameta.allure.Step;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LoginPage extends BasePage {

    private final SelenideElement pageTitle = $("div.page-title h1");
    private final SelenideElement inputEmail = $("input#Email");
    private final SelenideElement inputPassword = $("input#Password");
    private final SelenideElement rememberMeCheckbox = $("input#RememberMe");
    private final SelenideElement loginButton = $("input.login-button");
    private final ElementsCollection headerLinks = $$("div.header-links ul li a");

    @Step("Проверить, что открыта страница входа в интернет-магазин")
    public LoginPage verifyLoginPage() {
        pageTitle.shouldHave(text("Welcome, Please Sign In!"));
        return this;
    }

    @Step("Ввести email пользователя для входа: {email}")
    public LoginPage enterEmail(String email) {
        inputEmail.setValue(email);
        return this;
    }

    @Step("Ввести пароль для входа: {password}")
    public LoginPage enterPassword(String password) {
        inputPassword.setValue(password);
        return this;
    }

    @Step("Включить запоминание пользователя при входе")
    public LoginPage selectRememberMe() {
        rememberMeCheckbox.setSelected(true);
        return this;
    }

    @Step("Нажать кнопку Login")
    public LoginPage pressLoginButton() {
        loginButton.click();
        return this;
    }

    @Step("Проверить, что после входа в шапке магазина отображается email {email}")
    public LoginPage checkEmailIsShown(String email) {
        headerLinks.get(0).shouldHave(text(email));
        return this;
    }
}
