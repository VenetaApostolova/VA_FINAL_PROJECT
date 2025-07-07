package org.va.POM;

import java.util.UUID;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BasePage {

    public static final String REG_PAGE_SUFFIX = "/users/register";

    // ========== LOCATORS ==========
    @FindBy(xpath = "//input[contains(@name, 'username')]")
    private WebElement regUsernameInputField;

    @FindBy(xpath = "//input[contains(@type, 'email')]")
    private WebElement regEmailInputField;

    @FindBy(xpath = "//input[contains(@formcontrolname, 'birthDate')]")
    private WebElement regBirthDateInputField;

    @FindBy(id = "defaultRegisterFormPassword")
    private WebElement regPasswordInputField;

    @FindBy(id = "defaultRegisterPhonePassword")
    private WebElement regConfirmPasswordInputField;

    @FindBy(xpath = "//textarea")
    private WebElement publicInfoTextArea;

    @FindBy(id = "sign-in-button")
    private WebElement registrationFormSubmitButton;

    @FindBy(xpath = "//small[contains(@class, 'invalid-feedback') or contains(@class,'text-danger')]")
    private WebElement registrationError;

    @FindBy(xpath = "//*[contains(text(), 'Registration failed')]")
    private WebElement registrationToastError;

    @FindBy(linkText = "Register")
    private WebElement registerLink;


    // ========== CONSTRUCTOR ==========

    public RegistrationPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    // ========== NAVIGATION ==========
    public void openRegistrationPage() {
        navigateTo(REG_PAGE_SUFFIX);
        log.info("Navigated to Registration Page.");
    }

    // ========== ACTION METHODS ==========
    public void enterUsername(String username) {
        typeTextIn(regUsernameInputField, username);
    }

    public void enterEmail(String email) {
        typeTextIn(regEmailInputField, email);
    }

    public void enterBirthDate(String birthDate) {
        typeTextIn(regBirthDateInputField, birthDate);
    }

    public void enterPassword(String password) {
        typeTextIn(regPasswordInputField, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        typeTextIn(regConfirmPasswordInputField, confirmPassword);
    }

    public void enterAboutField(String about) {
        typeTextIn(publicInfoTextArea, about);
    }

    public void submitRegistration() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(registrationFormSubmitButton));
            registrationFormSubmitButton.click();
            log.info("Clicked on Register button.");
        } catch (TimeoutException e) {
            log.error("Register button was not clickable. Possibly due to validation errors.");
        }
    }

    public void fillRegistrationForm(String username, String email, String birthDate,
                                     String password, String confirmPassword, String about) {
        enterUsername(username);
        enterEmail(email);
        enterBirthDate(birthDate);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        enterAboutField(about);
    }

    // ========== GENERATORS ==========
    public String generateTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
    }

    public String generateDemoUsername() {
        return "DemoUser_" + UUID.randomUUID().toString().substring(0, 8);
    }

    public String generateEmail() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8) + "@testmail.com";
    }

    // ========== ASSERTIONS ==========

    public String getRegistrationErrorText() {
        By errorLocator = By.xpath("//*[contains(@class, 'invalid-feedback') or contains(@class,'text-danger')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));
        return driver.findElement(errorLocator).getText();
    }

    public boolean isToastErrorVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(registrationToastError));
            return registrationToastError.isDisplayed();
        } catch (TimeoutException e) {
            log.error("Toast message 'Registration failed' was not visible.");
            return false;
        }
    }

    public boolean isEmailErrorVisible() {
        try {
            WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(@class, 'invalid-feedback') and contains(text(), 'This field is required')]")
            ));
            return emailError.isDisplayed();
        } catch (TimeoutException e) {
            log.error("Email error message did not appear.");
            return false;
        }
    }
}
