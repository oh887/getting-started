package com.tricentis.demowebshop.test;

import com.tricentis.demowebshop.steps.AuthSteps;
import com.tricentis.demowebshop.pages.WelcomePage;
import com.tricentis.demowebshop.pages.CartPage;
import com.tricentis.demowebshop.pages.ProductPage;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import io.qameta.allure.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.tricentis.demowebshop.config.TestConfig.SHOP_URL;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static io.qameta.allure.Allure.step;

@Epic("Интернет-магазин")
@Feature("Корзина")
@Story("Покупка компьютера с выбранным процессором")
public class CartTest {
    private static final String PRODUCT_NAME = "Build your own cheap computer";
    private static final int QUANTITY = 2;
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
    @ParameterizedTest(name = "Добавление компьютера с процессором {0}")
    @ValueSource(strings = {"Slow", "Medium", "Fast"})
    void addItemCardTest(String processor) {
        ProductPage productPage = open(SHOP_URL, WelcomePage.class)
                .openDesktops()
                .openProduct(PRODUCT_NAME);

        BigDecimal expectedUnitPrice = new BigDecimal(productPage.getProductPrice())
                .add(processorSurcharge(processor));
        BigDecimal expectedSubtotal = expectedUnitPrice.multiply(BigDecimal.valueOf(QUANTITY));

        // Сначала выбираем другой вариант, чтобы проверить клик даже для процессора по умолчанию.
        CartPage cartPage = productPage
                .selectProcessor(processor.equals("Slow") ? "Fast" : "Slow")
                .selectProcessor(processor)
                .enterQuantity(QUANTITY)
                .addToCart()
                .checkAddedToCartNotification()
                .checkCartQuantity(QUANTITY)
                .openCart();

        assertAll("Данные компьютера в корзине",
                () -> step("Проверить название товара: " + PRODUCT_NAME,
                        () -> assertEquals(PRODUCT_NAME, cartPage.getProductName())),
                () -> step("Проверить процессор товара: " + processor, () -> {
                    String attributes = cartPage.getAttributes();
                    assertTrue(attributes.lines().anyMatch(line -> line.equals("Processor: " + processor)
                                    || line.startsWith("Processor: " + processor + " [")),
                            attributes);
                }),
                () -> step("Проверить точное количество товара: " + QUANTITY,
                        () -> assertEquals(String.valueOf(QUANTITY), cartPage.getQuantity())),
                () -> step("Проверить цену единицы товара: " + expectedUnitPrice.toPlainString(),
                        () -> assertEquals(expectedUnitPrice.toPlainString(), cartPage.getUnitPrice())),
                () -> step("Проверить стоимость позиции: " + expectedSubtotal.toPlainString(),
                        () -> assertEquals(expectedSubtotal.toPlainString(), cartPage.getSubtotal()))
        );
    }

    private BigDecimal processorSurcharge(String processor) {
        return switch (processor) {
            case "Slow" -> BigDecimal.ZERO;
            case "Medium" -> new BigDecimal("15");
            case "Fast" -> new BigDecimal("100");
            default -> throw new IllegalArgumentException("Unknown processor: " + processor);
        };
    }
}
