package org.example.tests;

import org.example.pom.LoginPom;
import org.testng.annotations.Test;

public class LoginTest extends Basic {
    public static final String URL = "https://demoqa.com/login";
    public static final String LOGIN = "admin";
    public static final String PASSWORD = "Adminovich69!";

    @Test
    public void formTest() {
        driver.get(URL);
        LoginPom form = new LoginPom(driver);

        form.setLogin(LOGIN);
        form.setPassword(PASSWORD);

        form.submitForm();
    }
}
