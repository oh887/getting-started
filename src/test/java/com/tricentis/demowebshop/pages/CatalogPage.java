package com.tricentis.demowebshop.pages;
import io.qameta.allure.Step;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class CatalogPage {
    private final ElementsCollection productLinks = $$(".product-grid .product-title a");

    @Step("Открыть карточку компьютера с индексом {index} в каталоге (нумерация с нуля)")
    public ProductPage openProduct(int index) {
        productLinks.get(index).click();
        return new ProductPage();
    }
}
