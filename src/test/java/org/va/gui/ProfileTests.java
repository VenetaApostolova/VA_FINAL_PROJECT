package org.va.gui;

import org.testng.annotations.Test;
import org.va.POM.*;
import org.va.base.BaseTest;


public class ProfileTests extends BaseTest {

    @Test
    public void updateProfileInfo() {
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithTestUser();
        // Предстои да се имплементира логика за update
        log.info("Login successful. Update logic can be added here.");
    }

    @Test
    public void testUserCanHoverOverProfilePicture() {
        log.info("STEP 1: Navigating to Home Page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();
        homePage.clickOnLoginNavBar();

        log.info("STEP 2: Logging in with test user.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.loginWithTestUser();

        log.info("STEP 3: Navigating to Profile Page.");
        homePage.clickOnProfileNavBar();

        ProfilePage profilePage = new ProfilePage(driver, log);
        log.info("STEP 4: Hovering over profile picture.");
        profilePage.HoverOverProfilePicture();

        // Примерна проверка (ако имаш метод за това):
        // Assert.assertTrue(profilePage.isUploadButtonVisible(), "Upload button not visible after hover.");

        log.info("CONFIRM: Hover action performed without errors.");
    }
}
