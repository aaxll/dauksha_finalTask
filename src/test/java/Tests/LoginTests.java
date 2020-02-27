package Tests;

import Fixtures.LoginTestsFixture;
import PageObjects.MyAccountPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.junit5.TextReportExtension;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@Epic(value = "Login")
@Feature(value = "Basic user sign in functionality")
public class LoginTests extends LoginTestsFixture {

    @Test
    @DisplayName(value = "[login-1] Login user with correct credentials")
    @Description(value = "Login user with correct credentials")
    @Severity(SeverityLevel.BLOCKER)
    public void loginUserWithCorrectCredentials(){
        MyAccountPage myAccountPage = loginPage.loginUser("fakeuser@gmail.com", "Userpass");
        myAccountPage.successfulLoginMessageShouldBeVisible();
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @DisplayName(value = "[login-2] Login user with invalid credentials:")
    @Description(value = "Login user with invalid credentials: invalid email, invalid pass, both")
    @Severity(SeverityLevel.BLOCKER)
    @CsvSource({
            "fakeuser1@gmail.com,   Userpass",
            "fakeuser@gmail.com,    Userpass1",
            "fakeuser1@gmail.com,   Userpass1"
    })
    public void loginUserWithInvalidCredentials(String login, String pass){
        loginPage.loginUser(login, pass);
        loginPage.loginErrorMessageShouldHaveText("Authentication failed");
    }

    @Test
    @DisplayName(value = "[login-3] Login user with no email")
    @Description(value = "Email field is empty")
    @Severity(SeverityLevel.CRITICAL)
    public void loginUserWithNoEmail(){
        loginPage.loginUser("", "userpass");
        loginPage.loginErrorMessageShouldHaveText("An email address required");
    }

    @Test
    @DisplayName(value = "[login-4] Login user with no password")
    @Description(value = "Password field is empty")
    @Severity(SeverityLevel.CRITICAL)
    public void loginUserWithNoPassword(){
        loginPage.loginUser("fakeuser@gmail.com", "");
        loginPage.loginErrorMessageShouldHaveText("Password is required");
    }

    @Test
    @DisplayName(value = "[login-5] Login user with no credentials")
    @Description(value = "Email and password fields are empty")
    @Severity(SeverityLevel.CRITICAL)
    public void loginUserWithNoCredentials(){
        loginPage.loginUser("", "");
        loginPage.loginErrorMessageShouldHaveText("An email address required");
    }

    @ParameterizedTest(name = "{displayName}: {arguments}")
    @DisplayName(value = "[login-6] Login user with invalid email format")
    @Description(value = "Try to login user with invalid email format")
    @Severity(SeverityLevel.NORMAL)
    @CsvSource({
            "fakeusergmail.com,   Userpass",
            "fakeuser@gmailcom,    Userpass"
    })
    public void loginUserWithInvalidEmailFormat(String login, String pass){
        loginPage.loginUser(login, pass);
        loginPage.loginErrorMessageShouldHaveText("Invalid email address");
    }

    @Test
    @DisplayName(value = "[login-7] Login user with invalid password format")
    @Description(value = "Try to login user with invalid password format: short password")
    @Severity(SeverityLevel.NORMAL)
    public void loginUserWithInvalidPasswordFormat(){
        loginPage.loginUser("fakeuser@gmail.com", "user");
        loginPage.loginErrorMessageShouldHaveText("Invalid password");
    }

    @Test
    @DisplayName(value = "[login-9] Email field highlight")
    @Description(value = "Check email field highlight in different conditions")
    @Severity(SeverityLevel.MINOR)
    public void emailFieldHighlight(){
        loginPage.checkEmailHighlightAfterPageLoad();
        loginPage.checkEmailHighlightAfterFocusOut();
        loginPage.checkEmailHighlightWidthValidEmail();
        loginPage.checkEmailHighlightWidthInvalidEmail();
    }

    @Test
    @DisplayName(value = "[login-10] Logout user")
    @Description(value = "User logout feature")
    @Severity(SeverityLevel.CRITICAL)
    public void logoutUser(){
        MyAccountPage myAccountPage = loginPage.loginUser("fakeuser@gmail.com", "Userpass");
        myAccountPage.userLogOut();
    }
}
