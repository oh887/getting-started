package com.tricentis.demowebshop.test;

import com.tricentis.demowebshop.pages.RegistrationPage;
import com.tricentis.demowebshop.pages.WelcomePage;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import io.qameta.allure.*;

import static com.codeborne.selenide.Selenide.*;
import static com.tricentis.demowebshop.config.TestConfig.*;


@Epic("Интернет-магазин")
@Feature("Авторизация")
@Story("Вход зарегистрированного пользователя")
public class LoginTest {
    private static final Faker faker = new Faker();
    private String email;
    private String password;

    @BeforeEach
    void beforeEach() {
        password = faker.ghostbusters().character() + faker.number().positive();
        email = faker.internet().emailAddress();

        open(REGISTER_SHOP_URL, RegistrationPage.class)
                .register(faker.name().firstName(),
                        faker.name().lastName(),
                        email,
                        password)
                .checkEmailIsShown(email);

        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Вход зарегистрированного пользователя")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("oh887")
    @Link(name = "Страница входа Demo Web Shop", url = SHOP_URL + "/login")
    void successLoginTest() {
        open(SHOP_URL, WelcomePage.class)
                .openLogin()
                .verifyLoginPage()
                .enterEmail(email)
                .enterPassword(password)
                .selectRememberMe()
                .pressLoginButton()
                .checkEmailIsShown(email);

    }
}
