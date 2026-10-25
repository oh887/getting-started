package com.tricentis.demowebshop.test;

import com.tricentis.demowebshop.steps.AuthSteps;
import com.tricentis.demowebshop.pages.WelcomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import io.qameta.allure.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.tricentis.demowebshop.config.TestConfig.SHOP_URL;

@Epic("Интернет-магазин")
@Feature("Корзина")
@Story("Покупка компьютера с выбранным процессором")
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

    @DisplayName("Добавление двух компьютеров в корзину с проверкой комплектации и стоимости")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("oh887")
    @Link(name = "Корзина Demo Web Shop", url = SHOP_URL + "/cart")
    @ParameterizedTest(name = "Добавление компьютера с процессором: индекс {0}")
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
