package org.va.gui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.va.POM.HomePage;
import org.va.POM.LoginPage;
import org.va.base.BaseTest;

import java.time.Duration;
import java.util.List;

public class HomePageElementsVisibilityTests extends BaseTest {

    @Test(priority = 1)
    public void confirmUserListIsDisplayedCorrectly() {
        log.info("STEP 1: Navigate to Home Page and login.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login("venetaQA2025", "Veneta123!");

        log.info("STEP 2: Check if user list is displayed.");
        List<WebElement> userList = homePage.getVisibleUserList();
        Assert.assertTrue(userList.size() > 0, "Expected user list to be visible and non-empty.");
    }

    @Test(priority = 2)
    public void confirmHomePageHeaderIsVisible() {
        log.info("STEP 1: Navigate to Home Page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Log in with valid credentials.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login(USERNAME, PASSWORD);

        log.info("ASSERT: Home page header (logo) should be visible.");
        Assert.assertTrue(homePage.isLogoVisible(), "Logo (Home Page Header) is not visible.");
    }

    @Test(priority = 3)
    public void confirmFollowButtonIsVisibleAndClickable() {
        log.info("STEP 1: Navigate to Home Page and login.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login(USERNAME, PASSWORD);

        log.info("STEP 2: Locate the first visible Follow button.");
        List<WebElement> followButtons = driver.findElements(By.xpath("//button[contains(text(),'Follow')]"));
        Assert.assertFalse(followButtons.isEmpty(), "No Follow buttons found on the page.");

        WebElement firstFollowButton = followButtons.get(0);
        Assert.assertTrue(firstFollowButton.isDisplayed() && firstFollowButton.isEnabled(),
                "Follow button is not visible or not clickable.");
    }

    @Test(priority = 4)
    public void confirmFollowButtonChangesToUnfollowAfterClick() {
        log.info("STEP 1: Navigate to Home Page and login.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login(USERNAME, PASSWORD);

        log.info("STEP 2: Locate the first visible Follow button.");
        List<WebElement> followButtons = driver.findElements(By.xpath("//button[text()='Follow']"));
        Assert.assertFalse(followButtons.isEmpty(), "No Follow buttons found.");

        WebElement followButton = followButtons.get(0);
        Assert.assertTrue(followButton.isDisplayed() && followButton.isEnabled(),
                "Follow button is not visible or not clickable.");

        log.info("STEP 3: Clicking the Follow button. Initial text: " + followButton.getText());
        followButton.click();

        log.info("STEP 4: Wait for button text to change to 'Unfollow'.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement updatedButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[text()='Unfollow']")));

        Assert.assertEquals(updatedButton.getText(), "Unfollow",
                "Button text did not change to 'Unfollow' after clicking.");
    }

    @Test(priority = 5)
    public void confirmUnfollowButtonChangesToFollowAfterClick() {
        log.info("STEP 1: Navigate to Home Page and login.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login("venetaQA2025", "Veneta123!");

        log.info("STEP 2: Locate the first visible Unfollow button.");
        WebElement unfollowButton = homePage.waitForVisibleUnfollowButton();

        log.info("STEP 3: Click the Unfollow button.");
        unfollowButton.click();

        log.info("STEP 4: Wait for the Follow button to become visible.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement followButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[normalize-space()='Follow']")
        ));

        log.info("STEP 5: Confirm the button text is 'Follow'.");
        Assert.assertEquals(followButton.getText().trim(), "Follow");
    }
}
