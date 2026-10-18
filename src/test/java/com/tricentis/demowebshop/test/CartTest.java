package com.tricentis.demowebshop.test;

import com.tricentis.demowebshop.steps.AuthSteps;
import com.tricentis.demowebshop.pages.WelcomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.tricentis.demowebshop.config.TestConfig.SHOP_URL;

public class CartTest {
    private final AuthSteps authSteps = new AuthSteps();

    @BeforeEach
    void beforeEach() {
        authSteps.registerNewUser();
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }

    @ParameterizedTest(name = "Add product with processor index {0}")
    @ValueSource(ints = {0, 1, 2})
    void addItemCardTest(int processorIndex) {
        open(SHOP_URL, WelcomePage.class)
                .openDesktops()
                .openProduct(0)
                .selectProcessor(processorIndex)
                .enterQuantity(2)
                .addToCart()
                .openCart()
                .checkProductName()
                .checkProcessor()
                .checkQuantity()
                .checkUnitPrice()
                .checkSubtotal();
    }
}
