package org.va.gui;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.va.POM.*;
import org.va.base.BaseTest;


import java.io.File;

public class PostCreateTests extends BaseTest {


    @Test(priority = 1)
    public void ensureUserCanCreatePost() throws InterruptedException {
        log.info("STEP 1: Navigating to Home page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Logging in with test user.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login("venetaQA2025", "Veneta123!");

        log.info("STEP 3: Clicking on 'Create Post'.");
        homePage.clickOnNewPostNavBar();

        log.info("STEP 4: Creating and submitting a new post.");
        PostPage postPage = new PostPage(driver, log);

        File imageFile = new File("src/test/resources/upload/testUpload1.jpg");
        Assert.assertTrue(imageFile.exists(), "Image file not found: " + imageFile.getAbsolutePath());

        postPage.uploadPicture(imageFile);

        postPage.providePostCaption("AutoTest Photo 001 - Do not delete");

        Thread.sleep(2000);

        postPage.clickCreatePostButton();

        log.info("STEP 5: Navigating to user profile to verify post was created.");
        homePage.clickOnProfileNavBar();

        ProfilePage profilePage = new ProfilePage(driver, log);
        int postCount = profilePage.getPostCount();

        log.info("ASSERT: Checking that post count is more than 0.");
        Assert.assertTrue(postCount > 0, "Post count did not increase – post may not have been created.");
    }

    @Test(priority = 2)
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

    @Test(priority = 3)
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

    @Test(priority = 4)
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

