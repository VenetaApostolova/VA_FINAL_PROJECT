package org.va.gui;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.va.POM.*;
import org.va.base.BaseTest;


public class RegistrationNegativeTests extends BaseTest {

    @Test(priority = 1)
    public void unsuccessfulRegistrationWhenPasswordsDoNotMatch() {
        log.info("STEP 1: Navigate to Home Page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();
        homePage.clickOnLoginNavBar();

        log.info("STEP 2: Navigate to Registration Page.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.clickOnRegisterLink();

        log.info("STEP 3: Fill in mismatched passwords.");
        RegistrationPage registrationPage = new RegistrationPage(driver, log);
        String username = registrationPage.generateDemoUsername();
        String email = registrationPage.generateEmail();

        registrationPage.fillRegistrationForm(
                username,
                email,
                "22022022",
                "Password123!",
                "Password321!",
                "Testing mismatched passwords"
        );

        log.info("STEP 4: Submit registration form.");
        registrationPage.submitRegistration();

        log.info("STEP 5: Assert that error message is shown.");
        String error = registrationPage.getRegistrationErrorText();
        Assert.assertNotNull(error, "Expected an error message to appear, but none was found.");
        Assert.assertTrue(error.contains("Passwords do not match!"),
                "Expected error message about mismatched passwords, but got: " + error);
    }

    @Test(priority = 2)
    public void unsuccessfulRegistrationWithEmptyEmailField() {
        log.info("STEP 1: Navigate to Home Page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();
        homePage.clickOnLoginNavBar();

        log.info("STEP 2: Navigate to Registration Page.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.clickOnRegisterLink();

        log.info("STEP 3: Fill registration form with EMPTY email.");
        RegistrationPage registrationPage = new RegistrationPage(driver, log);
        String username = registrationPage.generateDemoUsername();

        registrationPage.fillRegistrationForm(
                username,
                "", // ← празно имейл поле
                "22022022",
                "Password123!",
                "Password123!",
                "Testing empty email field"
        );

        log.info("STEP 4: Submit registration form.");
        registrationPage.submitRegistration();

        log.info("STEP 5: Assert that email error is visible.");
        Assert.assertTrue(registrationPage.isEmailErrorVisible(),
                "Expected 'This field is required' error for empty email field, but it was not visible.");
    }

    @Test(priority = 3)
    public void unsuccessfulRegistrationWithExistingUsername() {
        log.info("STEP 1: Navigate to Home page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Navigate to Registration Page.");
        RegistrationPage registrationPage = new RegistrationPage(driver, log);
        registrationPage.openRegistrationPage();

        log.info("STEP 3: Fill registration form with EXISTING username and unique email.");
        String existingUsername = "testingDemos";
        String uniqueEmail = registrationPage.generateEmail();

        registrationPage.fillRegistrationForm(
                existingUsername,
                uniqueEmail,
                "22022003",
                "Password123!",
                "Password123!",
                "Testing existing username"
        );

        log.info("STEP 4: Submit registration form.");
        registrationPage.submitRegistration();

        log.info("STEP 5: Assert that toast error message 'Registration failed!' is visible.");
        Assert.assertTrue(registrationPage.isToastErrorVisible(), "Toast error 'Registration failed!' was not visible.");
    }
}
