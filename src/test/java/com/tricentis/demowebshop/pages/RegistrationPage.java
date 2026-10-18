package com.tricentis.demowebshop.pages;

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

    public RegistrationPage verifyRegistrationPage() {
        pageTitle.shouldHave(text("Register"));
        return this;

    }

    public RegistrationPage selectMailGender() {
        maleGenderButton.click();
        return this;

    }

    public RegistrationPage enterFirstName(String firstName) {
        inputFirstName.setValue(firstName);
        return this;

    }

    public RegistrationPage enterLastName(String lastName) {
        inputLastName.setValue(lastName);
        return this;

    }
    public RegistrationPage enterEmail(String email) {
        inputEmail.setValue(email);
        return this;

    }
    public RegistrationPage enterPassword(String password) {
        inputPassword.setValue(password);
        return this;

    }
    public RegistrationPage confirmPassword(String password) {
        inputConfirmPassword.setValue(password);
        return this;

    }
    public RegistrationPage pressConfirmButton() {
        inputRegisterButton.click();
        return this;

    }
    public RegistrationPage checkRegistrationComleted() {
        resultText.shouldHave(text("Your registration completed"));
        return this;

    }
    public RegistrationPage checkEmailIsSnown(String email) {
        headerLinks.get(0).shouldHave(text(email));
        return this;

    }
}
