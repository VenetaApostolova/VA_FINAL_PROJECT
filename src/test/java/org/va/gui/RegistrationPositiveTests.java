package org.va.gui;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.va.POM.*;
import org.va.POM.HomePage;
import org.va.POM.LoginPage;
import org.va.base.BaseTest;

public class RegistrationPositiveTests extends BaseTest {

       @Test (priority = 1)
        public void testValidRegistrationWithUniqueData() {
            log.info("STEP 1: Navigate to Home Page.");
            HomePage homePage = new HomePage(driver, log);
            homePage.openHomePage();

            log.info("STEP 2: Go to Registration Page via Login.");
            homePage.clickOnLoginNavBar();
            LoginPage loginPage = new LoginPage(driver, log);
            loginPage.clickOnRegisterLink();

            log.info("STEP 3: Generate and fill in unique valid data.");
            RegistrationPage regPage = new RegistrationPage(driver, log);
            String username = regPage.generateDemoUsername();
            String email = regPage.generateEmail();

            regPage.enterUsername(username);
            regPage.enterEmail(email);
            regPage.enterBirthDate("01012000");
            regPage.enterPassword("TestPass123!");
            regPage.confirmPassword("TestPass123!");
            regPage.enterPublicInfo("Auto-generated test profile");

            log.info("STEP 4: Submit registration form.");
            regPage.submitRegistration();

            log.info("STEP 5: Validate successful registration.");
            boolean isRedirected = !driver.getCurrentUrl().contains("/register");
            boolean avatarVisible = homePage.isAvatarVisible();

            Assert.assertTrue(isRedirected, "User was not redirected after registration.");
            Assert.assertTrue(avatarVisible, "User avatar is not visible – registration might have failed.");
        }

        @Test (priority = 2)
        public void testRegisterWithValidDataAndLongPublicInfo() {
            log.info("STEP 1: Open home and go to registration.");
            HomePage homePage = new HomePage(driver, log);
            homePage.openHomePage();
            homePage.clickOnLoginNavBar();
            new LoginPage(driver, log).clickOnRegisterLink();

            RegistrationPage regPage = new RegistrationPage(driver, log);
            String username = regPage.generateDemoUsername();
            String email = regPage.generateEmail();
            String longText = "This is a test profile created automatically. ".repeat(10);

            regPage.enterUsername(username);
            regPage.enterEmail(email);
            regPage.enterBirthDate("01011990");
            regPage.enterPassword("SecurePass123!");
            regPage.confirmPassword("SecurePass123!");
            regPage.enterPublicInfo(longText);
            regPage.submitRegistration();

            Assert.assertFalse(driver.getCurrentUrl().contains("/register"), "User not redirected after registration.");
            Assert.assertTrue(homePage.isAvatarVisible(), "User avatar not visible – login may have failed.");
        }

        @Test (priority = 3)
        public void testRegistrationWithLongUsername() {
            log.info("STEP 1: Open Home Page.");
            HomePage homePage = new HomePage(driver, log);
            homePage.openHomePage();

            log.info("STEP 2: Navigate to Registration page.");
            homePage.clickOnLoginNavBar();
            LoginPage loginPage = new LoginPage(driver, log);
            loginPage.clickOnRegisterLink();

            log.info("STEP 3: Enter long but valid username (30 characters).");
            RegistrationPage regPage = new RegistrationPage(driver, log);
            String longUsername = "DemoUser_" + "abcdefghijklmno1234567890";
            String email = regPage.generateEmail();

            regPage.enterUsername(longUsername);
            regPage.enterEmail(email);
            regPage.enterBirthDate("01012000");
            regPage.enterPassword("StrongPass!1");
            regPage.confirmPassword("StrongPass!1");
            regPage.enterPublicInfo("Testing long username");

            regPage.submitRegistration();

            Assert.assertFalse(driver.getCurrentUrl().contains("/register"), "User was not redirected after registration.");
            Assert.assertTrue(homePage.isAvatarVisible(), "Avatar not visible – registration may have failed.");
        }

        @Test (priority = 4)
        public void testRegistrationWithMinUsernameLength() {
            log.info("STEP 1: Open Home Page.");
            HomePage homePage = new HomePage(driver, log);
            homePage.openHomePage();

            log.info("STEP 2: Go to Registration Page.");
            homePage.clickOnLoginNavBar();
            LoginPage loginPage = new LoginPage(driver, log);
            loginPage.clickOnRegisterLink();

            log.info("STEP 3: Register with 3-char username (minimum). ");
            RegistrationPage regPage = new RegistrationPage(driver, log);
            String minUsername = "abc";
            String email = regPage.generateEmail();

            regPage.enterUsername(minUsername);
            regPage.enterEmail(email);
            regPage.enterBirthDate("01012001");
            regPage.enterPassword("MyPass!23");
            regPage.confirmPassword("MyPass!23");
            regPage.enterPublicInfo("Minimal username test");

            regPage.submitRegistration();

            Assert.assertFalse(driver.getCurrentUrl().contains("/register"), "User was not redirected after registration.");
            Assert.assertTrue(homePage.isAvatarVisible(), "Avatar not shown – registration failed.");
        }

        @Test (priority = 5)
        public void testRegistrationWithSpecialCharactersInPublicInfo() {
            log.info("STEP 1: Navigate to Home Page.");
            HomePage homePage = new HomePage(driver, log);
            homePage.openHomePage();

            log.info("STEP 2: Open Registration page.");
            homePage.clickOnLoginNavBar();
            LoginPage loginPage = new LoginPage(driver, log);
            loginPage.clickOnRegisterLink();

            log.info("STEP 3: Register using emojis and special symbols in Public Info.");
            RegistrationPage regPage = new RegistrationPage(driver, log);
            String username = regPage.generateDemoUsername();
            String email = regPage.generateEmail();

            regPage.enterUsername(username);
            regPage.enterEmail(email);
            regPage.enterBirthDate("01112000");
            regPage.enterPassword("EmojiPass123!");
            regPage.confirmPassword("EmojiPass123!");
            regPage.enterPublicInfo("Hi! This is a test bio with emojis and symbols #QA @TestNG %!$");

            regPage.submitRegistration();

            Assert.assertFalse(driver.getCurrentUrl().contains("/register"), "User did not leave registration page.");
            Assert.assertTrue(homePage.isAvatarVisible(), "Avatar not found – something failed.");
        }
    }
