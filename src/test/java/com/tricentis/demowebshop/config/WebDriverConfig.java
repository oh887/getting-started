package com.tricentis.demowebshop.config;
import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/${run}.properties"
})
public interface WebDriverConfig extends Config {

    @DefaultValue("local")
    String run();

    String browser();

    String browserVersion();

    String browserSize();

    String selenoidUrl();

    String selenoidUser();

    String selenoidPassword();

    String name();

    String sessionTimeout();

    String screenResolution();

    String timeZone();

    boolean enableVideo();

    boolean enableVNC();

    boolean enableHAR();

    boolean enableLog();
}
