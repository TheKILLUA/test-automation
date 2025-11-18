package org.example.tests;

import org.example.pom.FormPom;
import org.testng.annotations.Test;

public class FormTest extends Basic {
    public static final String URL = "https://demoqa.com/automation-practice-form";
    public static final String FIRST_NAME = "Dan";
    public static final String LAST_NAME = "Molache";
    public static final String USER_EMAIL = "test@ceiti.md";
    public static final String USER_GENDER = "Male";
    public static final String USER_MOBILE_NUMBER = "0612345678";
    public static final String USER_BIRTH_DATE = "15 Mar 2006";
    public static final String[] USER_SUBJECTS = {"Hindi", "Social Studies", "Physics", "Maths"};
    public static final String[] USER_HOBBIES = {"Sports", "Music"};
    public static final String USER_PICTURE = "C:\\Users\\IT\\IdeaProjects\\maven-project-first\\src\\test\\resources\\test.txt";
    public static final String USER_STATE = "Rajasthan";
    public static final String USER_CITY = "Jaiselmer";
    public static final String USER_ADDRESS = "VIVAN PALACE, AND, near HOTEL, near PETROL PUMP, Gandhi Colony, Gandhi Nagar, Jaiselmer, Rajasthan, India";

    @Test
    public void formTest() {
        driver.get(URL);
        FormPom form = new FormPom(driver);

        form.setFirstName(FIRST_NAME);
        form.setLastName(LAST_NAME);
        form.setUserEmail(USER_EMAIL);
        form.setUserEmail(USER_EMAIL);
        form.setUserGender(USER_GENDER);
        form.setUserMobileNumber(USER_MOBILE_NUMBER);
        form.setUserBirthDate(USER_BIRTH_DATE);
        form.setUserSubjects(USER_SUBJECTS);
        form.setUserHobbies(USER_HOBBIES);
        form.setUserPicture(USER_PICTURE);
        form.setUserAddress(USER_ADDRESS);
        form.setUserState(USER_STATE);
        form.setUserCity(USER_CITY);

        form.submitForm();
    }
}
