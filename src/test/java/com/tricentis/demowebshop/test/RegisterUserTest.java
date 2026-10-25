package com.tricentis.demowebshop.test;

import com.tricentis.demowebshop.pages.WelcomePage;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import io.qameta.allure.*;

import static com.codeborne.selenide.Selenide.*;
import static com.tricentis.demowebshop.config.TestConfig.SHOP_URL;

@Epic("Интернет-магазин")
@Feature("Регистрация")
@Story("Создание учетной записи пользователя")
public class RegisterUserTest {
    private static final Faker faker = new Faker();

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("oh887")
    @Link(name = "Регистрация Demo Web Shop", url = SHOP_URL + "/register")
    void registerUserTest () {
        String password = faker.ghostbusters().character() + faker.number().positive();
        String email = faker.internet().emailAddress();

        open(SHOP_URL, WelcomePage.class)
                .openRegistration()
                .verifyRegistrationPage()
                .selectMailGender()
                .enterFirstName(faker.name().firstName())
                .enterLastName(faker.name().lastName())
                .enterEmail(email)
                .enterPassword(password)
                .confirmPassword(password)
                .pressConfirmButton()
                .checkRegistrationComleted()
                .checkEmailIsSnown(email);
    }
}
