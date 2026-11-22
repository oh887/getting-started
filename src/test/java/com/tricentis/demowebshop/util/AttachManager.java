package com.tricentis.demowebshop.util;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import java.util.stream.Collectors;
import static com.codeborne.selenide.Selenide.sessionId;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public final class AttachManager {
    private AttachManager() {
    }

    @Attachment(value = "Скриншот", type = "image/png", fileExtension = ".png")
    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "HTML страницы", type = "text/html", fileExtension = ".html")
    public static String pageSource() {
        return getWebDriver().getPageSource();
    }

    @Attachment(value = "Логи консоли браузера", type = "text/plain", fileExtension = ".txt")
    public static String browserConsoleLogs() {
        String logs = getWebDriver().manage().logs().get(LogType.BROWSER).getAll().stream()
                .map(LogEntry::toString)
                .collect(Collectors.joining(System.lineSeparator()));
        return logs.isEmpty() ? "Консоль браузера пуста." : logs;
    }

    @Attachment(value = "Видео", type = "text/html", fileExtension = ".html")
    public static String addVideo() {
        String videoUrl = "https://selenoid.qa.guru/video/" + sessionId() + ".mp4";
        return """
                <!doctype html>
                <html lang="ru">
                <head><meta charset="UTF-8"><title>Видео теста</title></head>
                <body>
                    <video style="max-width: 100%%" controls preload="metadata">
                        <source src="%1$s" type="video/mp4">
                    </video>
                    <p><a href="%1$s" target="_blank" rel="noopener noreferrer">Открыть MP4</a></p>
                </body>
                </html>
                """.formatted(videoUrl);
    }
}
