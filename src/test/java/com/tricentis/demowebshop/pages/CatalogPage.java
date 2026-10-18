package com.tricentis.demowebshop.pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class CatalogPage {
    private final ElementsCollection productLinks = $$(".product-grid .product-title a");

    public ProductPage openProduct(int index) {
        productLinks.get(index).click();
        return new ProductPage();
    }
}
