package org.va.gui;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.va.POM.HomePage;
import org.va.POM.LoginPage;
import org.va.base.BaseTest;

public class HomePageNavigationTests extends BaseTest {

    @Test(priority = 1)
    public void testClickOnHomeNavBarRedirectsToHomePage() {
        log.info("STEP 1: Navigate to Home Page");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Click on 'Home' navigation link");
        homePage.clickOnHomeNavBar();

        log.info("STEP 3: Verify that current URL contains '/posts/all'");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/posts/all"),
                "Expected to be on the home page, but was: " + currentUrl);
    }
    @Test(priority = 2)
    public void testClickOnProfileNavBarRedirectsToProfilePage() {
        log.info("STEP 1: Navigate to Home page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Logging in with valid user.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login("venetaQA2025", "Veneta123!");

        log.info("STEP 3: Clicking on Profile navigation link.");
        homePage.clickOnProfileNavBar();

        log.info("ASSERT: Confirm that the user is redirected to profile page.");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/users/"), "Expected URL to contain '/users/', but got: " + currentUrl);
    }
    @Test(priority = 3)
    public void testClickOnNewPostNavBarRedirectsToNewPostPage() {
        log.info("STEP 1: Navigate to Home page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Logging in with valid user.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login("venetaQA2025", "Veneta123!");

        log.info("STEP 3: Clicking on 'New Post' navigation link.");
        homePage.clickOnNewPostNavBar();

        log.info("ASSERT: Confirm that the user is redirected to the New Post page.");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/posts/create"),
                "Expected URL to contain '/posts/create', but got: " + currentUrl);
    }

    @Test(priority = 4)
    public void testClickOnLogoutRedirectsToLoginPage() {
        log.info("STEP 1: Login with valid credentials.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login("venetaQA2025", "Veneta123!");

        log.info("STEP 2: Click on Logout button.");
        HomePage homePage = new HomePage(driver, log);
        homePage.clickOnLogOutButton();

        log.info("ASSERT: URL should be login page after logout.");
        String expectedUrl = "http://training.skillo-bg.com:4300/users/login";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualUrl, expectedUrl, "User was not redirected to login page after logout.");
    }

        @Test(priority = 5)
        public void testSearchFunctionalityWorksCorrectly() {
            log.info("STEP 1: Navigate to Home Page and login.");
            HomePage homePage = new HomePage(driver, log);
            homePage.openHomePage();

            LoginPage loginPage = new LoginPage(driver, log);
            loginPage.login(USERNAME, PASSWORD);

            log.info("STEP 2: Perform search.");
            String searchTerm = "TestUserUser";
            homePage.enterSearchQuery(searchTerm);

            log.info("STEP 3: Validate search results appear.");
            Assert.assertTrue(homePage.areSearchResultsDisplayed(),
                    "Expected search results to be displayed for query: " + searchTerm);
        }

    @Test(priority = 6)
    public void testClickingOnUserAvatarRedirectsToProfile() {
        log.info("STEP 1: Navigate to Home Page and login.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login("venetaQA2025", "Veneta123!");

        log.info("STEP 2: Perform search for another user.");
        homePage.enterSearchQuery("TestUserUser"); // използвай съществуващо име
        Assert.assertTrue(homePage.areUserSearchResultsDisplayed(), "Search results should be displayed.");

        log.info("STEP 3: Click on user's avatar.");
        homePage.clickOnUserAvatarFromSearchResults();

        log.info("STEP 4: Validate redirection to user profile.");
        Assert.assertTrue(homePage.isOnUserProfilePage(), "User should be redirected to profile page.");
    }

}


