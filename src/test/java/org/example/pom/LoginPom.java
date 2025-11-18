package org.example.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPom extends Basic {
    @FindBy(xpath = "//input[@id=\"userName\"]")
    WebElement login;

    @FindBy(xpath = "//input[@id=\"password\"]")
    WebElement password;

    @FindBy(xpath = "//button[@id=\"login\"]")
    WebElement submit;

    public LoginPom(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        closeAds();
        PageFactory.initElements(driver, this);
    }

    public void setLogin(String loginParam) {
        login.clear();
        login.sendKeys(loginParam);
    }

    public void setPassword(String passwordParam) {
        password.clear();
        password.sendKeys(passwordParam);
    }

    public void submitForm() {
        submit.click();
    }
}
