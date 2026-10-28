package com.tricentis.demowebshop.pages;
import io.qameta.allure.Step;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.exactText;

public class CatalogPage extends BasePage {
    private final ElementsCollection productLinks = $$(".product-grid .product-title a");

    @Step("Открыть карточку товара «{name}» в каталоге")
    public ProductPage openProduct(String name) {
        productLinks.findBy(exactText(name)).click();
        return new ProductPage();
    }
}
