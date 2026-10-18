package com.tricentis.demowebshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LoginPage {

    private final SelenideElement pageTitle = $("div.page-title h1");
    private final SelenideElement inputEmail = $("input#Email");
    private final SelenideElement inputPassword = $("input#Password");
    private final SelenideElement rememberMeCheckbox = $("input#RememberMe");
    private final SelenideElement loginButton = $("input.login-button");
    private final ElementsCollection headerLinks = $$("div.header-links ul li a");

    public LoginPage verifyLoginPage() {
        pageTitle.shouldHave(text("Welcome, Please Sign In!"));
        return this;
    }

    public LoginPage enterEmail(String email) {
        inputEmail.setValue(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        inputPassword.setValue(password);
        return this;
    }

    public LoginPage selectRememberMe() {
        rememberMeCheckbox.setSelected(true);
        return this;
    }

    public LoginPage pressLoginButton() {
        loginButton.click();
        return this;
    }

    public LoginPage checkEmailIsShown(String email) {
        headerLinks.get(0).shouldHave(text(email));
        return this;
    }
}
