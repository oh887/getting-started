package com.demoqa.pages;
import io.qameta.allure.Step;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class YandexSearchPage {

    private final SelenideElement searchInput = $("#text");
    private final SelenideElement submitButton = $("[type=submit]");

    @Step("Ввести поисковый запрос в Яндексе: {query}")
    public YandexSearchPage search(String query) {

        searchInput.setValue(query);

        return this;
    }

    @Step("Выполнить поиск в Яндексе")
    public YandexSearchPage submit() {

        submitButton.click();

        return this;
    }
}
