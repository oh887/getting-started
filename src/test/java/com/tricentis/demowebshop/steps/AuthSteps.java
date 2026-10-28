package com.tricentis.demowebshop.steps;
import io.qameta.allure.Step;

import com.tricentis.demowebshop.pages.RegistrationPage;
import net.datafaker.Faker;

import static com.codeborne.selenide.Selenide.open;
import static com.tricentis.demowebshop.config.TestConfig.REGISTER_SHOP_URL;

public class AuthSteps {
    private static final Faker faker = new Faker();

    @Step("Зарегистрировать нового пользователя интернет-магазина для покупки товаров")
    public void registerNewUser() {
        open(REGISTER_SHOP_URL, RegistrationPage.class)
                .register(faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        faker.ghostbusters().character() + faker.number().positive());
    }
}
