package com.demoqa.pages;
import io.qameta.allure.Step;

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

    @Step("Открыть форму {practiceForms} в разделе Forms на DemoQA")
    public SecondDemoQaPage selectPracticeForms (String practiceForms) {

        $(byText(practiceForms)).click();

        return this;
    }

    @Step("Ввести имя студента в форме DemoQA: {value}")
    public SecondDemoQaPage inputFirstName(String value) {
        firstName.setValue(value);
        return this;
    }

    @Step("Ввести фамилию студента в форме DemoQA: {value}")
    public SecondDemoQaPage inputLastName(String value) {
        lastName.setValue(value);
        return this;
    }

    @Step("Ввести email студента в форме DemoQA: {value}")
    public SecondDemoQaPage inputEmail(String value) {
        userEmail.setValue(value);
        return this;
    }

    @Step("Выбрать пол студента в форме DemoQA: {gender}")
    public SecondDemoQaPage selectGender(String gender) {
        $("#genterWrapper").$(byText(gender)).click();
        return this;
    }

    @Step("Ввести номер телефона студента в форме DemoQA: {value}")
    public SecondDemoQaPage inputUserNumber(String value) {
        userNumber.setValue(value);
        return this;
    }

    @Step("Отправить заполненную форму регистрации студента на DemoQA")
    public SecondDemoQaPage submit() {
        submitButton.click();
        return this;
    }

    @Step("Проверить поле «{fieldName}» в результатах регистрации студента: ожидается «{expectedValue}»")
    public SecondDemoQaPage checkResult(String fieldName, String expectedValue) {
        resultsTable.shouldBe(visible)
                .$$("tbody tr").findBy(text(fieldName))
                .$$("td").get(1).shouldHave(text(expectedValue));
        return this;
    }
}
