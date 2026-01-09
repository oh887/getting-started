import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudyTest {

  @Test
  void studyPriceShouldBe47000Test() {
    Configuration.browser = "chrome"; // выбор браузера
    Configuration.pageLoadTimeout = 10000; // таймаут прогрузки страницы

    open("https://ya.ru/");
    $("textarea.search3__input").setValue("bulgakov qa");
    $("button.search3__button").click();
    $(".DistributionButtonClose").click();
    $(byText("ivanbulgakovqa.ru")).click();
    switchTo().window(1);
    $$(".t-menu__list li").last().click();
    $x("/html/body/div[1]/div[42]/div/div/div[32]/div/a/div/span").shouldHave(text("Хочу вкатиться в QA")).click();
    $(byText("Бегу оплачивать")).click();

    switchTo().window(2);
    $("span.ant-select-selection-wrap").click();
    $(byText("RUB")).click();
    $("[data-sentry-component='ManyPrices']").$("span h2").shouldHave(text("₽ 47 000 "));
  }
}
