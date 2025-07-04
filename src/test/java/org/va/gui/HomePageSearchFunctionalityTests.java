package org.va.gui;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.va.POM.HomePage;
import org.va.POM.LoginPage;
import org.va.base.BaseTest;


    public class HomePageSearchFunctionalityTests extends BaseTest {

        @Test(priority = 1)
        public void testSearchReturnsResultsForValidUser() {
            log.info("==== TEST CASE NAME: testSearchReturnsResultsForValidUser ====");

            log.info("STEP 1: Navigate to Home Page and login.");
            HomePage homePage = new HomePage(driver, log);
            homePage.openHomePage();

            LoginPage loginPage = new LoginPage(driver, log);
            loginPage.login("venetaQA2025", "Veneta123!");

            log.info("STEP 2: Enter valid search query.");
            String validUser = "TestUserUser";  // или "1313", или друг от видимите потребители
            homePage.enterSearchQuery(validUser);

            log.info("ASSERT: Search results should be visible.");
            Assert.assertTrue(homePage.areUserSearchResultsDisplayed(),
                    "Expected search results to be displayed for valid user: " + validUser);
        }

        @Test(priority = 2)
        public void testSearchReturnsNoResultsForInvalidUser() {
            log.info("STEP 1: Navigate to Home Page and login.");
            HomePage homePage = new HomePage(driver, log);
            homePage.openHomePage();

            LoginPage loginPage = new LoginPage(driver, log);
            loginPage.login(USERNAME, PASSWORD);

            log.info("STEP 2: Enter invalid search query.");
            String invalidSearchTerm = "xyz123NonexistentUser";
            homePage.enterSearchQuery(invalidSearchTerm);

            log.info("ASSERT: No search results should be displayed.");
            Assert.assertFalse(homePage.areSearchResultsDisplayed(),
                    "Expected no search results to be displayed for invalid query: " + invalidSearchTerm);
        }
    }

