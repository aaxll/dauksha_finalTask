package Fixtures;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

@Execution(ExecutionMode.CONCURRENT)
public class TestBase {

    @BeforeAll
    public static void setUp(){
        Configuration.remote = "http://192.168.100.100:4444/wd/hub";
        Configuration.browser = "firefox";
        Configuration.headless = true;
        Configuration.browserSize = "1920x1080";
        Configuration.fastSetValue = true;
        Configuration.baseUrl = "http://automationpractice.com/index.php";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(false));

    }
    @AfterEach
    public void tearDown(){
        WebDriverRunner.closeWebDriver();
    }

}
