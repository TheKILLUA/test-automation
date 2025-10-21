package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

public class EdgeBrowserTest {
    static public WebDriver getAutoLocalDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    static public WebDriver getLocalDriver() {
        System.setProperty("webdriver.edge.driver", "%WEB_DRIVER_PATH%\\msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        return new EdgeDriver(options);
    }

    public static RemoteWebDriver getRemoteDriver() {
        EdgeOptions options = new EdgeOptions();
        options.setCapability("browserVersion", "128.0");
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            /* How to add test badge */
            put("name", "Test badge...");

            /* How to set session timeout */
            put("sessionTimeout", "15m");

            /* How to set timezone */
            put("env", new ArrayList<String>() {{
                add("TZ=UTC");
            }});

            /* How to enable video recording */
            put("enableVideo", true);
            put("enableVNC", true);
            put("enableLog", true);
            put("noSandbox", true);
            put("headless", true);
        }});
        RemoteWebDriver remoteDriver = null;
        try {
            remoteDriver = new RemoteWebDriver(URI.create("http://127.0.0.1:4444/wd/hub").toURL(), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return remoteDriver;
    }
}