package org.va.gui;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.va.POM.*;
import org.va.base.BaseTest;

import java.io.File;

public class NewPostCreateNegativeTests extends BaseTest {

    @Test(priority = 1)
    public void unsuccessfulPostCreationWithoutImage() {
        log.info("STEP 1: Navigate to Home page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Login with valid credentials.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login("venetaQA2025", "Veneta123!");

        log.info("STEP 3: Navigate to New Post Page.");
        homePage.clickOnNewPostNavBar();

        log.info("STEP 4: Provide only caption.");
        NewPostPage postPage = new NewPostPage(driver, log);
        postPage.providePostCaption("Caption without image");

        log.info("STEP 5: Click on 'Create Post' button.");
        postPage.clickCreatePostButton();

        log.info("STEP 6: Assert error message is 'Please upload an image!'");
        Assert.assertTrue(postPage.isPostErrorMessageVisible(), "Error message not visible.");
        Assert.assertEquals(postPage.getPostCreationErrorMessage(), "Please upload an image!");
    }

    @Test(priority = 2)
    public void unsuccessfulPostCreationWithoutCaption() {
        log.info("STEP 1: Navigate to Home page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Login with valid credentials.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login("venetaQA2025", "Veneta123!");

        log.info("STEP 3: Navigate to New Post Page.");
        homePage.clickOnNewPostNavBar();

        log.info("STEP 4: Upload image only.");
        NewPostPage postPage = new NewPostPage(driver, log);
        File imageFile = new File("src/test/resources/upload/testUpload1.jpg");
        postPage.uploadPicture(imageFile);

        log.info("STEP 5: Click on 'Create Post' button.");
        postPage.clickCreatePostButton();

        log.info("STEP 6: Assert error message is 'Please enter caption!'");
        Assert.assertTrue(postPage.isPostErrorMessageVisible(), "Error message not visible.");
        Assert.assertEquals(postPage.getPostCreationErrorMessage(), "Please enter caption!");
    }

    @Test(priority = 3)
    public void unsuccessfulPostCreationWithoutImageAndCaption() {
        log.info("STEP 1: Navigate to Home page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Login with valid credentials.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login("venetaQA2025", "Veneta123!");

        log.info("STEP 3: Navigate to New Post Page.");
        homePage.clickOnNewPostNavBar();

        log.info("STEP 4: Do not upload anything.");
        NewPostPage postPage = new NewPostPage(driver, log);

        log.info("STEP 5: Click on 'Create Post' button.");
        postPage.clickCreatePostButton();

        log.info("STEP 6: Assert error message is 'Please upload an image!'");
        Assert.assertTrue(postPage.isPostErrorMessageVisible(), "Error message not visible.");
        Assert.assertEquals(postPage.getPostCreationErrorMessage(), "Please upload an image!");
    }
}
