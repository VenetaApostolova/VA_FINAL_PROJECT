package org.va.gui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.va.POM.*;
import org.va.base.BaseTest;

import java.util.List;


public class ProfilePositiveTests extends BaseTest {

    @Test(priority = 1)
    public void confirmProfileUsernameIsDisplayedCorrectly() {
        log.info("STEP 1: Open Home Page and navigate to Login.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();
        homePage.clickOnLoginNavBar();

        log.info("STEP 2: Login with valid credentials.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.loginWithTestUser();

        log.info("STEP 3: Navigate to Profile Page.");
        homePage.clickOnProfileNavBar();
        ProfilePage profilePage = new ProfilePage(driver, log);

        log.info("STEP 4: Get displayed username and validate it.");
        String actualUsername = profilePage.getUsername();
        String expectedUsername = "venetaQA2025";

        Assert.assertEquals(actualUsername, expectedUsername,
                "Displayed username does not match expected.");

        log.info("CONFIRM: Username is displayed correctly on the Profile Page.");
    }

    @Test(priority = 2)
    public void confirmFilterButtonsAreDisplayedAndClickable() {
        log.info("STEP 1: Navigating to Home Page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();
        homePage.clickOnLoginNavBar();

        log.info("STEP 2: Logging in.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.loginWithTestUser();

        log.info("STEP 3: Navigating to Profile Page.");
        homePage.clickOnProfileNavBar();

        ProfilePage profilePage = new ProfilePage(driver, log);
        Assert.assertTrue(profilePage.areFilterButtonsVisible(), "One or more filter buttons are not visible.");

        log.info("STEP 4: Clicking on filter buttons.");
        profilePage.clickAllButton();
        profilePage.clickPublicButton();
        profilePage.clickPrivateButton();

        log.info("CONFIRM: All filter buttons are clickable and visible.");
    }

    @Test(priority = 3)
    public void confirmNewPostPageLoadsCorrectly() {
        log.info("STEP 1: Navigate to Home Page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Login.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login("venetaQA2025", "Veneta123!");

        log.info("STEP 3: Click on New Post button.");
        homePage.clickOnNewPostNavBar();

        log.info("STEP 4: Verify if the Create Post page is loaded.");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/posts/create"),
                "ERROR: Create Post page did not load. Current URL is: " + currentUrl);
    }

    @Test(priority = 4)
    public void confirmUserCanHoverOverProfilePicture() {
        log.info("STEP 1: Logging in and navigating to Profile Page.");

        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login("venetaQA2025", "Veneta123!");

        homePage.clickProfileNavBar();

        ProfilePage profilePage = new ProfilePage(driver, log);

        log.info("STEP 2: Hovering over the profile picture.");
        profilePage.hoverOverProfilePicture();

        log.info("STEP 3: Verifying that hover effect is visible.");
        Assert.assertTrue(profilePage.isHoverOverlayVisible(), "Hover overlay should be visible.");
    }

    @Test(priority = 5)
    public void confirmPostImagesAreLoadedSuccessfully() {
        log.info("STEP 1: Logging in and navigating to Profile Page.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.loginWithTestUser();

        HomePage homePage = new HomePage(driver, log);
        homePage.clickOnProfileNavBar();

        ProfilePage profilePage = new ProfilePage(driver, log);
        int postCount = profilePage.getPostCount();
        log.info("Number of posts found: " + postCount);
        Assert.assertTrue(postCount > 0, "There should be at least one post.");

        log.info("STEP 2: Verifying that each post image has a valid 'src' attribute.");
        List<WebElement> images = driver.findElements(By.xpath("//app-profile-post//img"));

        for (int i = 0; i < images.size(); i++) {
            String src = images.get(i).getAttribute("src");
            log.info("Image[" + i + "] src: " + src);
            Assert.assertNotNull(src, "Image src should not be null.");
            Assert.assertFalse(src.isEmpty(), "Image src should not be empty.");
            Assert.assertFalse(src.contains("404"), "Image src should not lead to a 404 error.");
        }
    }
    @Test(priority = 6)
    public void confirmDisplayedPostCountIsCorrect() {
        log.info("STEP 1: Login and open profile page.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.loginWithTestUser();

        HomePage homePage = new HomePage(driver, log);
        homePage.clickOnProfileNavBar();

        ProfilePage profilePage = new ProfilePage(driver, log);

        log.info("STEP 2: Click on 'All' filter to show all posts.");
        profilePage.clickAllButton();

        log.info("STEP 3: Get actual and displayed post count.");
        int actualPostCount = profilePage.getPostCount();
        int displayedPostCount = profilePage.getDisplayedPostCountFromText();

        log.info("Actual posts: " + actualPostCount + " | Displayed in text: " + displayedPostCount);
        Assert.assertEquals(actualPostCount, displayedPostCount,
                "Displayed post count text does not match actual number of posts.");
    }

}



























