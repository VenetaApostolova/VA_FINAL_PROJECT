package org.va.login;

import org.va.POM.HomePage;
import org.va.POM.LoginPage;
import org.va.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginHappyPathTests extends BaseTest {
    private static final String HOMEPAGE_TITLE = "ISkillo";
    private static final String LOGIN_FORM_TITLE = "Sign in";
    public static final String LOGIN_SUCCESSFUL_MSG = "Successful login!";
    public static final String LOGIN_NOT_SUCCESSFUL_MSG = "Wrong username or password!";
    public static final String USER = "userValid";
    public static final String PASS = "123456V";

    @Test
    public void verifyTheUserCannotLoginWithInvalidCredentials() {

        HomePage homePage = new HomePage(super.driver, log);
        log.info("STEP 1: Not logged in user has opened the Skillo HomePage.");
        homePage.openHomePage();

        log.info("STEP 1.1.Verify the user is on the home page ");
        String actualHomePageTitle = driver.getTitle();
        Assert.assertEquals(actualHomePageTitle,HOMEPAGE_TITLE);

        log.info("STEP 1.2. Verify that the login link is presented ");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 2: The use is navigating to the login page via click on navigation bar login link");
        homePage.clickOnLoginNavBar();

        log.info("STEP 2.1.: The user is successfully on the login page");
        LoginPage loginPage = new LoginPage(super.driver,log);

        String actualLoginFormHeaderText = loginPage.getLoginFormHeaderText();
        Assert.assertEquals(actualLoginFormHeaderText,LOGIN_FORM_TITLE);

        log.info("STEP 3. Provide username");
        loginPage.provideUser(USER);

        log.info("STEP 4. Provide password");
        loginPage.providePass(PASS);

        log.info("STEP 5. Click on loginButton");
        loginPage.clickOnLoginFormSubmitButton();

        log.info("STEP 6. Verify the user successfully login in ISkillo");
        String actualLoginSuccessfullMsg =  loginPage.getLoginPageToastSuccessfullMsg();
        log.info("STEP 6.1. Verify login successful message is presented to the user");
        Assert.assertEquals(actualLoginSuccessfullMsg,LOGIN_SUCCESSFUL_MSG);
        log.info("STEP 6.2. Verify for an already logged-in user a logout button is shown");
        boolean isLogOutButtonpresented = homePage.isLogOutButtonShown();
        Assert.assertTrue(isLogOutButtonpresented);
        log.info("STEP 6.3. Verify for an already logged-in user a nav bar profile link is shwon");
        boolean isNavBarProfileShown = homePage.isNavBarProfileLinkShown();
        Assert.assertTrue(isNavBarProfileShown);
    }

    @Test(priority = 99)
    public void verifyLoginPageLayout(){
        HomePage homePage = new HomePage(super.driver, log);
        log.info("STEP 1: Not logged in user has opened the Skillo HomePage.");
        homePage.openHomePage();

        log.info("STEP 1.1.Verify the user is on the home page ");
        String actualHomePageTitle = driver.getTitle();
        Assert.assertEquals(actualHomePageTitle,HOMEPAGE_TITLE);

        log.info("STEP 1.2. Verify that the login link is presented ");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 2: The use is navigating to the login page via click on navigation bar login link");
        homePage.clickOnLoginNavBar();

        // =====  LOGIN PAGE LAYOUT
        LoginPage loginPage = new LoginPage(super.driver,log);

        // header text form
        String actualLoginFormHeaderText = loginPage.getLoginFormHeaderText();
        // user place holder
        String actualUsernameInputHeaderText = loginPage.getUsernamePlaceHolderText();

        // pass place holder

        // Remember me - text


        // Siting Btn - label text
        String actualLoginButtonText = loginPage.getLoginFormSubmitButtonlabel();

        System.out.println("The end !");

    }

}
