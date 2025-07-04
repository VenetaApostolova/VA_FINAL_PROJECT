package org.va.gui;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.va.POM.*;
import org.va.base.BaseTest;


public class NewPostCreateNegativeTests extends BaseTest {


    @Test(priority = 1)
    public void testUserCanLikeLastCreatedPost() {
        log.info("STEP 1: Logging in and navigating to profile.");
        HomePage homePage = new HomePage(driver, log);
        LoginPage loginPage = new LoginPage(driver, log);
        ProfilePage profilePage = new ProfilePage(driver, log);
        PostPage postPage = new PostPage(driver, log);

        homePage.openHomePage();
        loginPage.loginWithTestUser();
        homePage.clickOnProfileNavBar();

        log.info("STEP 2: Clicking on the last created post.");
        profilePage.clickOnLastPost();

        log.info("STEP 3: Liking the post.");
        postPage.clickLikeButton();

        log.info("STEP 4: Verifying the post is liked.");
        boolean isLiked = postPage.isLikeButtonActive();
        Assert.assertTrue(isLiked, "Post was not liked successfully.");
    }
    @Test(priority = 2)
    public void testUserCanDislikeLastCreatedPost() {
        log.info("STEP 1: Logging in and navigating to profile.");
        HomePage homePage = new HomePage(driver, log);
        LoginPage loginPage = new LoginPage(driver, log);
        ProfilePage profilePage = new ProfilePage(driver, log);
        PostPage postPage = new PostPage(driver, log);

        homePage.openHomePage();
        loginPage.loginWithTestUser();
        homePage.clickOnProfileNavBar();

        log.info("STEP 2: Clicking on the last created post.");
        profilePage.clickOnLastPost();

        log.info("STEP 3: Disliking the post.");
        postPage.clickDislikeButton();

        log.info("STEP 4: Verifying the post is disliked.");
        boolean isDisliked = postPage.isDislikeButtonActive();
        Assert.assertTrue(isDisliked, "Post was not disliked successfully.");
    }

    @Test(priority = 3)
    public void testUserCanDeleteLastCreatedPost() {
        log.info("STEP 1: Logging in and navigating to profile.");
        HomePage homePage = new HomePage(driver, log);
        LoginPage loginPage = new LoginPage(driver, log);
        ProfilePage profilePage = new ProfilePage(driver, log);
        PostPage postPage = new PostPage(driver, log);

        homePage.openHomePage();
        loginPage.loginWithTestUser();
        homePage.clickOnProfileNavBar();

        log.info("STEP 2: Clicking on the last created post.");
        profilePage.clickOnLastPost();

        log.info("STEP 3: Deleting the post.");
        postPage.clickDeleteButton();
        postPage.clickConfirmDeleteButton();

        log.info("STEP 4: Verifying deletion message appears.");
        boolean isDeleted = profilePage.isDeletedMessageVisible();
        Assert.assertTrue(isDeleted, "Post was not deleted successfully.");
    }

}


