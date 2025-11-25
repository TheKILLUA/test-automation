package org.example.tests;

import org.example.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Basic {
    WebDriver driver;

    @BeforeClass
    public void beforeTest() {
        driver = Driver.getRemoteDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void formTest() {}

    @AfterClass
    public void afterTest() {
        driver.quit();
    }
}
