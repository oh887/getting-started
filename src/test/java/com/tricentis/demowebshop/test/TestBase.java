package com.tricentis.demowebshop.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.tricentis.demowebshop.util.AttachManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;
import static com.tricentis.demowebshop.config.TestConfig.*;

public abstract class TestBase {
    private static final boolean REMOTE_RUN = "remote".equals(System.getProperty("run"));

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.browserSize = config.browserSize();
        Configuration.browser = config.browser();

        if (REMOTE_RUN) {
            Configuration.remote =
                    "https://" + config.selenoidUser() + ":" + config.selenoidPassword() +
                            "@" + config.selenoidUrl();
            Configuration.browserCapabilities = getSelenoidBrowserOptions();
        }
    }

    @AfterEach
    void after() {
        if (!hasWebDriverStarted()) {
            return;
        }

        try {
            clearBrowserCookies();
            clearBrowserLocalStorage();

            AttachManager.takeScreenshot();
            AttachManager.pageSource();
            AttachManager.browserConsoleLogs();
            if (REMOTE_RUN) {
                AttachManager.addVideo();
            }
        } finally {
            closeWebDriver();
        }
    }
}
