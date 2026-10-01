package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class SecondDemoQaPage {

    private final SelenideElement firstName = $("#firstName");
    private final SelenideElement lastName = $("#lastName");
    private final SelenideElement userEmail = $("#userEmail");
    private final SelenideElement userNumber = $("#userNumber");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement resultsTable = $(".modal-content .table");

    public SecondDemoQaPage selectPracticeForms (String practiceForms) {

        $(byText(practiceForms)).click();

        return this;
    }

    public SecondDemoQaPage inputFirstName(String value) {
        firstName.setValue(value);
        return this;
    }

    public SecondDemoQaPage inputLastName(String value) {
        lastName.setValue(value);
        return this;
    }

    public SecondDemoQaPage inputEmail(String value) {
        userEmail.setValue(value);
        return this;
    }

    public SecondDemoQaPage selectGender(String gender) {
        $("#genterWrapper").$(byText(gender)).click();
        return this;
    }

    public SecondDemoQaPage inputUserNumber(String value) {
        userNumber.setValue(value);
        return this;
    }

    public SecondDemoQaPage submit() {
        submitButton.click();
        return this;
    }

    public SecondDemoQaPage checkResult(String fieldName, String expectedValue) {
        resultsTable.shouldBe(visible)
                .$$("tbody tr").findBy(text(fieldName))
                .$$("td").get(1).shouldHave(text(expectedValue));
        return this;
    }
}
