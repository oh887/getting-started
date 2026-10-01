package com.demoqa.pages;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainDemoQaPage {

    public MainDemoQaPage selectForms (String forms) {

        $(byText(forms)).click();

        return this;
    }
}
