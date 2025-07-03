package org.va.gui;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.va.POM.HomePage;
import org.va.POM.LoginPage;
import org.va.base.BaseTest;

import java.time.Duration;

public class LoginFormPositiveTests extends BaseTest {

    private static final String LOGIN_SUCCESSFUL_MSG = "Successful login!";

    @Test(priority = 1)
    public void loginSuccessfullyWithValidCredentials() {
        log.info("STEP 1: Navigate to Home Page");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        log.info("STEP 1.1: Verify the homepage title is correct");
        Assert.assertEquals(driver.getTitle(), "ISkillo", "Unexpected homepage title");

        log.info("STEP 2: Verify that login link is visible in navigation bar");
        Assert.assertTrue(homePage.isNavLoginShown(), "Login link is not visible");

        log.info("STEP 3: Click on login link to go to Login Page");
        homePage.clickOnLoginNavBar();

        log.info("STEP 4: Perform login with valid credentials");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login("venetaQA2025", "Veneta123!");

        log.info("STEP 5: Verify toast message after successful login");
        String actualToastMsg = loginPage.getLoginPageToastSuccessfullMsg();
        Assert.assertEquals(actualToastMsg, LOGIN_SUCCESSFUL_MSG, "Login toast message mismatch");

        log.info("STEP 6: Verify logout button is shown after login");
        Assert.assertTrue(homePage.isLogOutButtonShown(), "Logout button not visible");

        log.info("STEP 7: Verify profile link is shown in nav bar");
        Assert.assertTrue(homePage.isNavBarProfileLinkShown(), "Profile link is not visible");
    }

    @Test(priority = 2)
    public void loginSuccessfullyWithRememberMeOption() {
        log.info("STEP 1: Navigate to Home Page");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Go to Login Page");
        homePage.clickOnLoginNavBar();

        log.info("STEP 3: Fill login form with valid credentials and select 'Remember me'");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.provideUser("venetaQA2025");
        loginPage.providePass("Veneta123!");

        loginPage.selectRememberMeCheckbox();

        loginPage.clickOnLoginFormSubmitButton();

        log.info("STEP 4: Verify login successful toast");
        String toastMsg = loginPage.getLoginPageToastSuccessfullMsg();
        Assert.assertEquals(toastMsg, "Successful login!", "Login toast mismatch");

        log.info("STEP 5: Refresh and verify user is still logged in");
        driver.navigate().refresh();
        Assert.assertTrue(homePage.isLogOutButtonShown(), "User was logged out after refresh with 'Remember Me' selected");
    }

    @Test(priority = 3)
    public void userIsRedirectedToHomeAfterLogin() {
        log.info("STEP 1: Open Home Page");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        log.info("# CONFIRM: The user has navigated to http://training.skillo-bg.com:4300/posts/all");
        Assert.assertEquals(driver.getCurrentUrl(), "http://training.skillo-bg.com:4300/posts/all", "Home page URL is incorrect");

        log.info("STEP 2: Go to Login Page");
        homePage.clickOnLoginNavBar();

        log.info("STEP 3: Login with valid credentials");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.provideUser("venetaQA2025");
        loginPage.providePass("Veneta123!");
        loginPage.clickOnLoginFormSubmitButton();

        log.info("STEP 4: Verify login toast appears");
        String toastMsg = loginPage.getLoginPageToastSuccessfullMsg();
        Assert.assertEquals(toastMsg, "Successful login!", "Login toast message mismatch");

        log.info("STEP 5: Wait for redirect and verify URL");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/posts/all"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.endsWith("/posts/all"), "Expected to be on Home (/posts/all), but was: " + currentUrl);
    }
}
