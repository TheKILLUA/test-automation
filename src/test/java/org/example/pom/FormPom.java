package org.example.pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

public class FormPom {
    WebDriver driver;
    JavascriptExecutor js;
    private static final Logger logger = LoggerFactory.getLogger(FormPom.class);

    @FindBy(xpath = "//input[@id=\"firstName\"]")
    WebElement firstName;

    @FindBy(xpath = "//input[@id=\"lastName\"]")
    WebElement lastName;

    @FindBy(xpath = "//input[@id=\"userEmail\"]")
    WebElement userEmail;

    @FindBy(xpath = "//input[@name=\"gender\"]")
    List<WebElement> genderOptions;

    @FindBy(xpath = "//input[@id=\"userNumber\"]")
    WebElement mobileNumber;

    @FindBy(xpath = "//input[@id=\"dateOfBirthInput\"]")
    WebElement dateOfBirth;

    @FindBy(xpath = "//input[@id=\"subjectsInput\"]")
    WebElement subjects;

    public FormPom(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        closeAds();
        PageFactory.initElements(driver, this);
    }

    public void setFirstName(String firstNameParam) {
        firstName.clear();
        firstName.sendKeys(firstNameParam);
    }

    public void setLastName(String lastNameParam) {
        lastName.clear();
        lastName.sendKeys(lastNameParam);
    }

    public void setUserEmail(String userEmailParam) {
        userEmail.clear();
        userEmail.sendKeys(userEmailParam);
    }

    public void setUserGender(String genderParam) {
        WebElement fallback = null;
        boolean fallbackFlag = false;
        final String fallbackValue = "Other";

        for (WebElement gender : genderOptions) {
            String genderValue = Objects.requireNonNull(gender.getAttribute("value"));
            if (genderValue.equalsIgnoreCase(genderParam)) {
                if (!gender.isSelected()) {
                    clickLabelByGender(gender);
                }

                fallbackFlag = false;

                break;
            }

            if (genderValue.equalsIgnoreCase(fallbackValue)) {
                fallback = gender;
                fallbackFlag = true;
            }
        }

        if (fallbackFlag && !fallback.isSelected()) {
            clickLabelByGender(fallback);
            logger.warn("Fallback option selected");
        }
    }

    public void setUserMobileNumber(String mobileNumberParam) {
        final String fallbackValue = "0000000000";

        if (mobileNumberParam.length() > 10) {
            mobileNumberParam = fallbackValue;
            logger.warn("Mobile number is too long. Using fallback value: " + fallbackValue);
        }

        mobileNumber.clear();
        mobileNumber.sendKeys(mobileNumberParam);
    }

    public void setUserBirthDate(String birthDateParam) {
        final String fallbackValue = "02 Feb 2022";

        if (!isValidBirthDate(birthDateParam)) {
            birthDateParam = fallbackValue;
            logger.warn("Birth Date is invalid. Using fallback value: " + fallbackValue);
        }

        dateOfBirth.sendKeys(Keys.CONTROL + "a");
        dateOfBirth.sendKeys(birthDateParam);
    }

    private boolean isValidBirthDate(String birthDateParam) {
        if  (birthDateParam.isEmpty()) {
            return false;
        }

        return false;
    }

    private void clickLabelByGender(WebElement gender) {
        final String inputId = gender.getAttribute("id");

        if (inputId != null) {
            WebElement label = gender.findElement(org.openqa.selenium.By.xpath("//label[@for='" + inputId + "']"));

            if (!label.isSelected()) {
                label.click();
            }
        }
    }

    private void setUserSubjects(String[] subs) {
        subjects.sendKeys();
        subjects.sendKeys(Keys.ENTER);
    }

    public void closeAds() {
        try {
            js.executeScript("var elem = document.evaluate(\"//*[@id='adplus-anchor']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {}

        try {
            js.executeScript("var elem = document.evaluate(\"//footer\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {}
    }
}
