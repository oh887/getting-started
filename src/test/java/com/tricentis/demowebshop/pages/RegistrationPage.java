package com.tricentis.demowebshop.pages;
import io.qameta.allure.Step;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RegistrationPage {

    private final SelenideElement maleGenderButton = $("input#gender-male");
    private final SelenideElement pageTitle = $("div.page-title h1");
    private final SelenideElement inputFirstName = $("input#FirstName");
    private final SelenideElement inputLastName = $("input#LastName");
    private final SelenideElement inputEmail = $("input#Email");
    private final SelenideElement inputPassword = $("input#Password");
    private final SelenideElement inputConfirmPassword = $("input#ConfirmPassword");
    private final SelenideElement inputRegisterButton = $("input#register-button");
    private final SelenideElement resultText =  $("div.result");
    private final ElementsCollection headerLinks =  $$("div.header-links ul li a");

    @Step("Зарегистрировать пользователя {firstname} {lastname} с email {email}")
    public RegistrationPage register(String firstname, String lastname, String email, String password) {
                selectMailGender()
                .enterFirstName(firstname)
                .enterLastName(lastname)
                .enterEmail(email)
                .enterPassword(password)
                .confirmPassword(password)
                .pressConfirmButton()
                .checkRegistrationComleted();
        return this;

    }

    @Step("Проверить, что открыта страница регистрации в интернет-магазине")
    public RegistrationPage verifyRegistrationPage() {
        pageTitle.shouldHave(text("Register"));
        return this;

    }

    @Step("Выбрать мужской пол в форме регистрации")
    public RegistrationPage selectMailGender() {
        maleGenderButton.click();
        return this;

    }

    @Step("Ввести имя при регистрации: {firstName}")
    public RegistrationPage enterFirstName(String firstName) {
        inputFirstName.setValue(firstName);
        return this;

    }

    @Step("Ввести фамилию при регистрации: {lastName}")
    public RegistrationPage enterLastName(String lastName) {
        inputLastName.setValue(lastName);
        return this;

    }
    @Step("Ввести email при регистрации: {email}")
    public RegistrationPage enterEmail(String email) {
        inputEmail.setValue(email);
        return this;

    }
    @Step("Ввести пароль при регистрации: {password}")
    public RegistrationPage enterPassword(String password) {
        inputPassword.setValue(password);
        return this;

    }
    @Step("Подтвердить пароль при регистрации: {password}")
    public RegistrationPage confirmPassword(String password) {
        inputConfirmPassword.setValue(password);
        return this;

    }
    @Step("Отправить форму регистрации пользователя")
    public RegistrationPage pressConfirmButton() {
        inputRegisterButton.click();
        return this;

    }
    @Step("Проверить сообщение об успешной регистрации пользователя")
    public RegistrationPage checkRegistrationComleted() {
        resultText.shouldHave(text("Your registration completed"));
        return this;

    }
    @Step("Проверить, что после регистрации в шапке магазина отображается email {email}")
    public RegistrationPage checkEmailIsSnown(String email) {
        headerLinks.get(0).shouldHave(text(email));
        return this;

    }
}
