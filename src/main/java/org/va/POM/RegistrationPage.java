package org.va.POM;

import java.util.UUID;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public void confirmPassword(String confirmPassword) {
        typeTextIn(regConfirmPasswordInputField, confirmPassword);
    }

    public void enterPublicInfo(String publicInfo) {
        typeTextIn(publicInfoTextArea, publicInfo);
    }

    public void submitRegistration() {
        clickOn(registrationFormSubmitButton);
    }

    public String generateTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
    }

    public String generateDemoUsername() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        return "DemoUser_" + uuid;
    }

    public String generateEmail() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        return "user_" + uuid + "@testmail.com";
    }
    public String getRegistrationErrorText() {
        try {
            wait.until(ExpectedConditions.visibilityOf(registrationError));
            return registrationError.getText();
        } catch (TimeoutException e) {
            return null;
        }
    }
}
