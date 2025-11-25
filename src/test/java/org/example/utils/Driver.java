package org.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

public class Driver {

    static public WebDriver getAutoLocalDriver() {
        WebDriverManager.edgedriver().setup();

        EdgeOptions options = new EdgeOptions();

        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--remote-debugging-port=9222");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-infobars");
        }

        options.addArguments("--remote-allow-origins=*");

        return new EdgeDriver(options);
    }

    static public WebDriver getLocalDriver() {
        System.setProperty("webdriver.edge.driver", "C:\\webdrivers\\msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        return new EdgeDriver(options);
    }

    public static RemoteWebDriver getRemoteDriver() {
        EdgeOptions options = new EdgeOptions();
        options.setCapability("browserVersion", "128.0");
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("name", "Test badge...");
            put("sessionTimeout", "15m");
            put("env", new ArrayList<String>() {{
                add("TZ=UTC");
            }});
            put("enableVideo", true);
            put("enableVNC", true);
            put("enableLog", true);
            put("noSandbox", true);
            put("headless", true);
        }});

        try {
            return new RemoteWebDriver(URI.create("http://127.0.0.1:4444/wd/hub").toURL(), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}