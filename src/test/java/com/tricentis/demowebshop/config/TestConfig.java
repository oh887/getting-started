package com.tricentis.demowebshop.config;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

public class TestConfig {
    public static final String SHOP_URL = "https://demowebshop.tricentis.com";
    public static final String REGISTER_SHOP_URL = SHOP_URL + "/register";

    public static final WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class,System.getProperties());

    public static WebDriverConfig getWebDriverConfig(){
        return config;
    }

    public static ChromeOptions getSelenoidBrowserOptions() {

        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserVersion", config.browserVersion());

        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("name", config.name());
        selenoidOptions.put("sessionTimeout", config.sessionTimeout());
        selenoidOptions.put("screenResolution", config.screenResolution());
        selenoidOptions.put("timeZone", config.timeZone());
        selenoidOptions.put("labels", Map.of("manual", "true"));
        selenoidOptions.put("enableVideo", config.enableVideo());
        selenoidOptions.put("enableVNC", config.enableVNC());
        selenoidOptions.put("enableHAR", config.enableHAR());
        selenoidOptions.put("enableLog", config.enableLog());

        options.setCapability("selenoid:options", selenoidOptions);

        return options;
    }

}
