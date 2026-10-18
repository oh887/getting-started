package com.tricentis.demowebshop.steps;

import com.tricentis.demowebshop.pages.RegistrationPage;
import net.datafaker.Faker;

import static com.codeborne.selenide.Selenide.open;
import static com.tricentis.demowebshop.config.TestConfig.REGISTER_SHOP_URL;

public class AuthSteps {
    private static final Faker faker = new Faker();

    public void registerNewUser() {
        open(REGISTER_SHOP_URL, RegistrationPage.class)
                .register
                        (faker.name().firstName(),
                                faker.name().firstName(),
                                faker.internet().emailAddress(),
                                faker.ghostbusters().character() + faker.number().positive());

    }
}
