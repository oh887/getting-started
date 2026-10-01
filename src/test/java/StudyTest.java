import com.codeborne.selenide.Configuration;
import com.demoqa.pages.MainDemoQaPage;
import com.demoqa.pages.SecondDemoQaPage;
import com.demoqa.pages.YandexSearchPage;
import com.demoqa.pages.YandexSearchResultsPage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudyTest {

  @Test
  void studyPriceShouldBe47000Test() {
    Configuration.browser = "chrome"; // выбор браузера
    Configuration.pageLoadTimeout = 100000; // таймаут прогрузки страницы
    Configuration.timeout = 100000; // таймаут прогрузки элементов

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

  @Test
  // Тест-кейс - проверка формы регистрации
  // 1. Открыть demoqa.com
  // 2. Нажать на кнопку "Forms"
  // 3. Нажать на кнопку "Practice Form"
  // 4. Ввести имя "Ivan"
  // 5. Ввести фамилию "Ivanov"
  // 6. Ввести email "ivanivanov@gmail.com"
  // 7. Выбрать пол Male
  // 8. Ввести номер "7900111223"
  // 9. Нажать на кнопку "Submit"
  void checkPracticeForm() {
    Configuration.browser = "chrome"; // выбор браузера
    Configuration.pageLoadTimeout = 10000; // таймаут прогрузки страницы
    Configuration.timeout = 10000; // таймаут прогрузки элементов
    Configuration.browserSize = "1920x1080";

    YandexSearchPage yaSearchPage = new YandexSearchPage();
    YandexSearchResultsPage yaSearchResultsPage = new YandexSearchResultsPage();
    MainDemoQaPage mainDemoQaPage = new MainDemoQaPage();
    SecondDemoQaPage secondDemoQaPage = new SecondDemoQaPage();

    open("https://ya.ru");
    yaSearchPage.search("demoqa").submit();
    yaSearchResultsPage.selectSuitableSite("demoqa.com");

    switchTo().window(1);
    mainDemoQaPage.selectForms("Forms");
    secondDemoQaPage.selectPracticeForms("Practice Form")
            .inputFirstName("Ivan")
            .inputLastName("Ivanov")
            .inputEmail("ivanivanov@gmail.com")
            .selectGender("Male")
            .inputUserNumber("7900111223")
            .submit()
            .checkResult("Student Name", "Ivan Ivanov")
            .checkResult("Student Email", "ivanivanov@gmail.com")
            .checkResult("Gender", "Male")
            .checkResult("Mobile", "7900111223");
  }
}
