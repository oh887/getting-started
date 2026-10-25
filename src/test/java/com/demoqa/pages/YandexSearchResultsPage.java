package com.demoqa.pages;
import io.qameta.allure.Step;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class YandexSearchResultsPage {

    private final SelenideElement closebutton = $(".DistributionButtonClose");

        @Step("Закрыть предложение установить браузер на странице результатов поиска Яндекса")
        public YandexSearchResultsPage closeWindowsChangeBrowser () {

            closebutton.click();

            return this;

        }
        @Step("Открыть сайт {websiteName} из результатов поиска Яндекса")
        public YandexSearchResultsPage selectSuitableSite (String websiteName) {

            $(byText(websiteName)).click();

            return this;

        }
    }
