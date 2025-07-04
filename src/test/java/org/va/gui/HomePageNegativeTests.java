package org.va.gui;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.va.POM.HomePage;
import org.va.POM.LoginPage;
import org.va.base.BaseTest;

public class HomePageNegativeTests extends BaseTest {

    @Test
    public void testClickingLogoDoesNotRedirectAnywhere() {
        log.info("STEP 1: Login with valid credentials.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login("venetaQA2025", "Veneta123!");

        log.info("STEP 2: Go to Profile page to be away from Home.");
        HomePage homePage = new HomePage(driver, log);
        homePage.clickOnProfileNavBar();

        String beforeClick = driver.getCurrentUrl();

        log.info("STEP 3: Click on logo.");
        homePage.clickOnLogo();

        String afterClick = driver.getCurrentUrl();
        log.info("ASSERT: Logo does not redirect to another page.");
        Assert.assertEquals(afterClick, beforeClick, "Clicking logo should not redirect anywhere.");
    }

}