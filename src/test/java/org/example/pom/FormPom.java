package org.example.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

public class FormPom extends Basic {
    private static final Logger logger = LoggerFactory.getLogger(FormPom.class);

    @FindBy(xpath = "//input[@id=\"firstName\"]")
    WebElement firstName;

    @FindBy(xpath = "//input[@id=\"lastName\"]")
    WebElement lastName;

    @FindBy(xpath = "//input[@id=\"userEmail\"]")
    WebElement userEmail;

    @FindBy(xpath = "//div[@id=\"genterWrapper\"]//input[@name=\"gender\"]")
    List<WebElement> genderOptions;

    @FindBy(xpath = "//input[@id=\"userNumber\"]")
    WebElement mobileNumber;

    @FindBy(xpath = "//input[@id=\"dateOfBirthInput\"]")
    WebElement dateOfBirth;

    @FindBy(xpath = "//input[@id=\"subjectsInput\"]")
    WebElement subjects;

    @FindBy(xpath = "//input[@id=\"uploadPicture\"]")
    WebElement picture;

    @FindBy(xpath = "//textarea[@id=\"currentAddress\"]")
    WebElement address;

    @FindBy(xpath = "//div[@id=\"stateCity-wrapper\"]//input[@id=\"react-select-3-input\"]")
    WebElement state;

    @FindBy(xpath = "//div[@id=\"stateCity-wrapper\"]//input[@id=\"react-select-4-input\"]")
    WebElement city;

    @FindBy(xpath = "//button[@id=\"submit\"]")
    WebElement submit;

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
                        clickLabelByInput(gender);
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
                clickLabelByInput(fallback);
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
        dateOfBirth.sendKeys(Keys.ENTER);
    }

    private boolean isValidBirthDate(String birthDateParam) {
        if  (birthDateParam.isEmpty()) {
            return false;
        }

        return false;
    }

    private void clickLabelByInput(WebElement el) {
        final String inputId = el.getAttribute("id");

        if (inputId != null) {
            WebElement label = el.findElement(org.openqa.selenium.By.xpath("//label[@for='" + inputId + "']"));

            if (!label.isSelected()) {
                label.click();
            }
        }
    }

    public void setUserSubjects(String[] subs) {
        subjects.clear();

        for (String sub : subs) {
            subjects.sendKeys(sub);
            subjects.sendKeys(Keys.ENTER);
        }
    }

    public void setUserHobbies(String[] hobbies) {
        for (String hobby : hobbies) {
            String escapedHobby = hobby.replace("'", "\\'").trim();
            By hobbyLocator = By.xpath("//div[@id='hobbiesWrapper']//label[normalize-space(.)='" + escapedHobby + "']");

            WebElement label = driver.findElement(hobbyLocator);

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", label);
            js.executeScript("arguments[0].click();", label);

            logger.info("Selected hobby: {}", hobby);
        }
    }

    public void setUserPicture(String userPicture) {
        picture.sendKeys(userPicture);
    }

    public void setUserAddress(String userAddress) {
        address.clear();
        address.sendKeys(userAddress);
    }

    public void setUserState(String userState) {
        state.clear();
        state.sendKeys(userState);
        state.sendKeys(Keys.ENTER);
    }

    public void setUserCity(String userCity) {
        city.clear();
        city.sendKeys(userCity);
        city.sendKeys(Keys.ENTER);
    }

    public void submitForm() {
        clickWithJs(submit);
    }
}
