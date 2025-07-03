package org.va.gui;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.va.POM.*;
import org.va.base.BaseTest;


public class RegistrationNegativeTests extends BaseTest {

    @Test
    public void testRegistrationWithMismatchedPasswords() {
        log.info("STEP 1: Navigating to Home Page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Clicking on Login nav bar.");
        homePage.clickOnLoginNavBar();

        log.info("STEP 3: Clicking on Register link.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.clickOnRegisterLink();

        log.info("STEP 4: Filling in mismatched passwords.");
        RegistrationPage regPage = new RegistrationPage(driver, log);
        String username = regPage.generateDemoUsername();
        String email = regPage.generateEmail();

        regPage.enterUsername(username);
        regPage.enterEmail(email);
        regPage.enterBirthDate("22022022");
        regPage.enterPassword("Test12345");           // Парола
        regPage.confirmPassword("Test12");            // Различна потвърдена парола
        regPage.enterPublicInfo("Testing mismatched passwords");

        log.info("STEP 5: Submitting registration form.");
        regPage.submitRegistration();

        log.info("STEP 6: Checking for error message.");
        String errorMsg = regPage.getRegistrationErrorText();
        log.info("Error message displayed: " + errorMsg);

        Assert.assertNotNull(errorMsg, "Expected error message for mismatched passwords, but none was shown.");
        Assert.assertTrue(errorMsg.toLowerCase().contains("парол"), "Expected error message to mention password mismatch.");
    }

    @Test
    public void testRegistrationWithEmptyEmailField() {
        log.info("STEP 1: Navigating to Home Page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Clicking on Login nav bar.");
        homePage.clickOnLoginNavBar();

        log.info("STEP 3: Clicking on Register link.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.clickOnRegisterLink();

        log.info("STEP 4: Filling in data with EMPTY email.");
        RegistrationPage regPage = new RegistrationPage(driver, log);
        String username = regPage.generateDemoUsername();

        regPage.enterUsername(username);
        regPage.enterEmail("");  // празен имейл
        regPage.enterBirthDate("22022022");
        regPage.enterPassword("Password123!");
        regPage.confirmPassword("Password123!");
        regPage.enterPublicInfo("Testing empty email field");

        log.info("STEP 5: Submitting registration form.");
        regPage.submitRegistration();

        log.info("STEP 6: Checking for error message.");
        String errorMsg = regPage.getRegistrationErrorText();
        log.info("Error message displayed: " + errorMsg);

        Assert.assertNotNull(errorMsg, "Expected error message for empty email, but none was shown.");
        Assert.assertTrue(errorMsg.toLowerCase().contains("email") || errorMsg.toLowerCase().contains("имейл"),
                "Expected error message to mention email problem.");
    }

    @Test
    public void testRegistrationWithExistingUsername() {
        log.info("STEP 1: Navigating to Home Page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Clicking on Login nav bar.");
        homePage.clickOnLoginNavBar();

        log.info("STEP 3: Clicking on Register link.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.clickOnRegisterLink();

        log.info("STEP 4: Filling in registration form with EXISTING username.");
        RegistrationPage regPage = new RegistrationPage(driver, log);

        String existingUsername = "testingDemos"; // съществуващ потребител
        String email = regPage.generateEmail();

        regPage.enterUsername(existingUsername);
        regPage.enterEmail(email);
        regPage.enterBirthDate("22022022");
        regPage.enterPassword("Password123!");
        regPage.confirmPassword("Password123!");
        regPage.enterPublicInfo("Attempt to register with existing username");

        log.info("STEP 5: Submitting registration form.");
        regPage.submitRegistration();

        log.info("STEP 6: Checking for error message.");
        String errorMsg = regPage.getRegistrationErrorText();
        log.info("Error message displayed: " + errorMsg);

        Assert.assertNotNull(errorMsg, "Expected error message for existing username, but none was shown.");
        Assert.assertTrue(errorMsg.toLowerCase().contains("username") || errorMsg.toLowerCase().contains("потребител"),
                "Expected error message to mention existing username.");
    }

}
