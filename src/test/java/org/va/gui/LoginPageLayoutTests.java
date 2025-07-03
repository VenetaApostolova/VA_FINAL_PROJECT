package org.va.gui;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.va.POM.LoginPage;
import org.va.base.BaseTest;

public class LoginPageLayoutTests extends BaseTest {

    @Test(priority = 1)
    public void confirmLoginPageElementsAreDisplayed() {
        log.info("STEP 1: Navigate to Login Page");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.navigateToLoginPage();

        log.info("STEP 2: Verify login form header is visible");
        Assert.assertTrue(loginPage.isLoginFormHeaderTextShown(), "Login form header is not visible");

        log.info("STEP 3: Verify username input field is visible");
        Assert.assertTrue(loginPage.isElementVisible("username"), "Username field is not visible");

        log.info("STEP 4: Verify password input field is visible");
        Assert.assertTrue(loginPage.isElementVisible("password"), "Password field is not visible");

        log.info("STEP 5: Verify login button is visible");
        Assert.assertTrue(loginPage.isElementVisible("submit"), "Login button is not visible");

        log.info("STEP 6: Verify register link is visible");
        Assert.assertTrue(loginPage.isElementVisible("register"), "Register link is not visible");
    }

    @Test(priority = 2)
    public void confirmLoginPageLayoutIsVisibleCorrectly() {
        log.info("STEP 1: Navigate directly to Login Page");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.navigateToLoginPage();

        log.info("STEP 2: Verify Login form header text");
        Assert.assertEquals(
                loginPage.getLoginFormHeaderText(),
                "Sign in",
                "Login form header text is incorrect"
        );

        log.info("STEP 3: Verify Username field placeholder text");
        Assert.assertEquals(
                loginPage.getUsernamePlaceHolderText(),
                "Username or email",
                "Username placeholder text is incorrect"
        );

        log.info("STEP 4: Verify Password field placeholder text");
        Assert.assertEquals(
                loginPage.getPassPlaceHolderText(),
                "Password",
                "Password placeholder text is incorrect"
        );

        log.info("STEP 5: Verify Login button label");
        Assert.assertEquals(
                loginPage.getLoginFormSubmitButtonlabel(),
                "Sign in",
                "Login button label is incorrect"
        );
    }
    @Test(priority = 3)
    public void confirmLoginPageLogoIsVisible() {
        log.info("==== TEST CASE: Confirm login page logo is visible ====");

        log.info("STEP 1: Navigate to Login Page");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.navigateToLoginPage();

        log.info("STEP 2: Verify that the Skillo logo is visible");
        boolean isLogoVisible = loginPage.isLoginLogoVisible();
        Assert.assertTrue(isLogoVisible, "Login logo is not visible on the Login Page");

        log.info("RESULT: Login page logo is successfully visible.");
    }
}
