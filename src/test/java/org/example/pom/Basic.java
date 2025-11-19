package org.example.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class Basic {
    protected WebDriver driver;
    protected JavascriptExecutor js;
    protected WebDriverWait wait;

    public Basic() {}

    public void closeAds() {
        String[] scripts = {
                "var fixedban = document.getElementById('fixedban'); if (fixedban) fixedban.remove();",

                "var footer = document.querySelector('footer'); if (footer) footer.remove();",

                "document.querySelectorAll('.fc-consent-root, .fc-dialog, .modal, .popup, [class*=\"overlay\"]').forEach(el => el.remove());",

                "document.querySelectorAll('button[aria-label=\"close\"], button.close, div.close').forEach(el => el.click());"
        };

        for (String script : scripts) {
            try {
                js.executeScript(script);
            } catch (Exception ignored) {}
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
    }

    protected void clickWithJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        js.executeScript("arguments[0].click();", element);
    }
}