package org.example.pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPom extends Basic {

    @FindBy(id = "userName")
    private WebElement login;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login")
    private WebElement submit;

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
        clickWithJs(submit);
    }
}