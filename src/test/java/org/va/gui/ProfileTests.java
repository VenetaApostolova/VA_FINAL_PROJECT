package org.va.gui;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.va.POM.*;
import org.va.base.BaseTest;


public class ProfileTests extends BaseTest {

    @Test (priority = 1)
    public void updateProfileInfo() {
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithTestUser();
        // Предстои да се имплементира логика за update
        log.info("Login successful. Update logic can be added here.");
    }

    @Test (priority = 2)
    public void testUserCanHoverOverProfilePicture() {
        log.info("STEP 1: Navigating to Home Page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();
        homePage.clickOnLoginNavBar();

        log.info("STEP 2: Logging in with test user.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.loginWithTestUser();

        log.info("STEP 3: Navigating to Profile Page.");
        homePage.clickOnProfileNavBar();

        ProfilePage profilePage = new ProfilePage(driver, log);
        log.info("STEP 4: Hovering over profile picture.");
        profilePage.HoverOverProfilePicture();

        // Примерна проверка (ако имаш метод за това):
        // Assert.assertTrue(profilePage.isUploadButtonVisible(), "Upload button not visible after hover.");

        log.info("CONFIRM: Hover action performed without errors.");
    }

    @Test(priority = 3)
    public void testUserCanLikePost() {
        log.info("STEP 1: Logging in and navigating to profile.");
        HomePage homePage = new HomePage(driver, log);
        LoginPage loginPage = new LoginPage(driver, log);
        ProfilePage profilePage = new ProfilePage(driver, log);

        homePage.openHomePage();
        loginPage.loginWithTestUser();
        homePage.clickOnProfileNavBar();

        log.info("STEP 2: Clicking on the first available post.");
        profilePage.clickPost(0);

        log.info("STEP 3: Clicking like button.");
        profilePage.ClickOnLikeButton();

        log.info("ASSERT: Checking if like message is visible.");
        Assert.assertTrue(profilePage.isLikeMessageVisible(), "Like confirmation message not visible.");
    }

    @Test(priority = 4)
    public void testUserCanDislikePost() {
        log.info("STEP 1: Logging in and navigating to profile.");
        HomePage homePage = new HomePage(driver, log);
        LoginPage loginPage = new LoginPage(driver, log);
        ProfilePage profilePage = new ProfilePage(driver, log);

        homePage.openHomePage();
        loginPage.loginWithTestUser();
        homePage.clickOnProfileNavBar();

        log.info("STEP 2: Clicking on the first available post.");
        profilePage.clickPost(0);

        log.info("STEP 3: Clicking dislike button.");
        profilePage.ClickOnDisikeButton();

        log.info("ASSERT: Checking if dislike message is visible.");
        Assert.assertTrue(profilePage.isDislikeMessageVisible(), "Dislike confirmation message not visible.");
    }

    @Test(priority = 5)
    public void testUserCanDeletePost() {
        log.info("STEP 1: Logging in and navigating to profile.");
        HomePage homePage = new HomePage(driver, log);
        LoginPage loginPage = new LoginPage(driver, log);
        ProfilePage profilePage = new ProfilePage(driver, log);

        homePage.openHomePage();
        loginPage.loginWithTestUser();
        homePage.clickOnProfileNavBar();

        log.info("STEP 2: Clicking on the last created post.");
        profilePage.clickOnLastPost();

        log.info("STEP 3: Clicking delete button.");
        profilePage.ClickOnDeleteButton();

        log.info("STEP 4: Confirming deletion.");
        profilePage.ClickOnYesButton();

        log.info("ASSERT: Checking if deletion confirmation message is visible.");
        Assert.assertTrue(profilePage.isDeletedMessageVisible(), "Post was not deleted successfully.");
    }
}

