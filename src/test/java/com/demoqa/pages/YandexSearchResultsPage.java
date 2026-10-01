package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class YandexSearchResultsPage {

    private final SelenideElement closebutton = $(".DistributionButtonClose");

        public YandexSearchResultsPage closeWindowsChangeBrowser () {

            closebutton.click();

            return this;

        }
        public YandexSearchResultsPage selectSuitableSite (String websiteName) {

            $(byText(websiteName)).click();

            return this;

        }
    }
