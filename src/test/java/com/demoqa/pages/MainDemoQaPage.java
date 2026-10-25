package com.demoqa.pages;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainDemoQaPage {

    @Step("Открыть раздел {forms} на главной странице DemoQA")
    public MainDemoQaPage selectForms (String forms) {

        $(byText(forms)).click();

        return this;
    }
}
