package com.tricentis.demowebshop.pages;
import io.qameta.allure.Step;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage extends BasePage {
    private final SelenideElement productPrice = $("[itemprop=price]");
    private final SelenideElement attributes = $(".attributes");
    private final SelenideElement quantityInput = $("input.qty-input");
    private final SelenideElement addToCartButton = $("input.add-to-cart-button");
    private final SelenideElement successNotification = $("div.bar-notification.success");

    @Step("Выбрать процессор компьютера: {processor}")
    public ProductPage selectProcessor(String processor) {
        SelenideElement radio = attributeOptions("Processor")
                .findBy(text(processor))
                .$("input[type=radio]");
        radio.click();
        radio.shouldBe(checked);
        return this;
    }

    @Step("Установить количество компьютеров для покупки: {quantity}")
    public ProductPage enterQuantity(int quantity) {
        quantityInput.setValue(String.valueOf(quantity));
        return this;
    }

    @Step("Добавить компьютер в корзину")
    public ProductPage addToCart() {
        addToCartButton.click();
        return this;
    }

    @Step("Проверить уведомление об успешном добавлении товара в корзину")
    public ProductPage checkAddedToCartNotification() {
        successNotification.shouldBe(visible)
                .shouldHave(text("The product has been added to your shopping cart"));
        return this;
    }

    @Step("Получить базовую цену компьютера из карточки товара")
    public String getProductPrice() {
        return productPrice.shouldBe(visible).getText().trim();
    }

    @Step("Найти варианты комплектации в группе «{groupTitle}»")
    private ElementsCollection attributeOptions(String groupTitle) {
        return attributes.$$("dl dt")
                .findBy(text(groupTitle))
                .sibling(0)
                .$$("li");
    }
}
