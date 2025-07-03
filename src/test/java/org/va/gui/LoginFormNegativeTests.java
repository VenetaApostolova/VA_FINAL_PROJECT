package org.va.gui;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.va.POM.LoginPage;
import org.va.base.BaseTest;


public class LoginFormNegativeTests extends BaseTest {

    @Test(priority = 1)
    public void UnsuccessfulLoginWhenUsernameIsInvalid() {
        log.info("STEP 1: Navigate to Login Page");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.navigateToLoginPage();

        log.info("STEP 2: Enter invalid username and valid password");
        loginPage.enterUsername("invalidUser123");
        loginPage.enterPassword("Veneta123!");

        log.info("STEP 3: Click on the Sign in button");
        loginPage.clickOnLoginFormSubmitButton();

        log.info("STEP 4: Verify error message is shown and user remains on login page");
        Assert.assertTrue(loginPage.isLoginErrorVisible(), "Expected error message not displayed.");
        Assert.assertTrue(driver.getCurrentUrl().contains("/users/login"),
                "User was redirected away from login page despite invalid credentials.");
    }

    @Test(priority = 2)
    public void UnsuccessfulLoginWithEmptyPasswordField() {
        log.info("STEP 1: Navigate to Login Page");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.navigateToLoginPage();

        log.info("STEP 2: Enter valid username and leave password field empty");
        loginPage.enterUsername("venetaQA2025");
        loginPage.enterPassword(""); // празна парола

        log.info("STEP 3: Click on the Sign in button");
        loginPage.clickOnLoginFormSubmitButton();

        log.info("STEP 4: Verify that user is not redirected (still on login page)");
        Assert.assertTrue(driver.getCurrentUrl().contains("/users/login"),
                "User was redirected even with empty password field.");

        log.info("STEP 5: Verify that toast or validation message is NOT displayed");
        // Вариант: проверка за деактивиран бутон или че нищо не се случва
        Assert.assertFalse(loginPage.isToastMessageVisible(),
                "Toast message should not be visible for empty password field.");
    }

    @Test(priority = 3)
    public void UnsuccessfulLoginWithEmptyCredentials() {
        log.info("STEP 1: Navigate to Login Page");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.navigateToLoginPage();

        log.info("STEP 2: Leave both fields empty and click on Sign in");
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickOnLoginFormSubmitButton();

        log.info("STEP 3: Verify that user is not logged in and stays on login page");
        Assert.assertTrue(driver.getCurrentUrl().contains("/users/login"),
                "User was redirected despite empty credentials.");

        log.info("STEP 4: Confirm toast or inline error is not displayed");
        Assert.assertFalse(loginPage.isToastMessageVisible(),
                "Toast message should not be shown when credentials are empty.");
    }

    @Test(priority = 4)
    public void UnsuccessfulLoginFiveTimesWithWrongPassword() {
        log.info("STEP 1: Navigate to Login Page");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.navigateToLoginPage();

        for (int i = 1; i <= 5; i++) {
            log.info("Attempt #" + i + ": Try login with invalid password");
            loginPage.enterUsername("venetaQA2025"); // валиден username
            loginPage.enterPassword("WrongPass123!"); // грешна парола
            loginPage.clickOnLoginFormSubmitButton();

            // само за малко изчакване
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Assert.assertTrue(loginPage.isLoginErrorVisible(),
                    "Expected login error message not shown on attempt #" + i);
        }

        log.info("RESULT: System allowed multiple failed login attempts.");
    }
}
