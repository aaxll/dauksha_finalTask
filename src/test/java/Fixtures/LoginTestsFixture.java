package Fixtures;

import PageObjects.LoginPage;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class LoginTestsFixture extends TestBase{

    public LoginPage loginPage;

    @BeforeEach
    public void loginTestsFixture(){
        loginPage = open("/?controller=authentication&back=my-account", LoginPage.class);
    }

}
