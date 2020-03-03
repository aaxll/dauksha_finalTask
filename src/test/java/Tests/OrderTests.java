package Tests;

import Fixtures.OrderTestsFixture;
import PageObjects.OrderPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic(value = "Order")
@Feature(value = "Order product features")
public class OrderTests extends OrderTestsFixture {

    @Test
    @DisplayName(value = "[order-1] Order product with login")
    @Description(value = "Order product with authorised user")
    @Severity(SeverityLevel.NORMAL)
    public void orderProductWithLogin(){
        loginPage.goToLoginPage();
        loginPage.loginUser("fakeuser@gmail.com", "Userpass");
        productPage.addCustomProductToCart("/?id_product=2&controller=product#/size-s/color-black");
        OrderPage orderPage = cartPage.clickCheckoutBtn();
        orderPage.checkAddressStep();
        orderPage.clickProceedToCheckoutBtn();
        orderPage.checkShippingStep();
        orderPage.clickProceedToCheckoutBtn();
        orderPage.checkPaymentStep();
        orderPage.selectPaymentMethod();
        orderPage.clickProceedToCheckoutBtn();
        orderPage.checkOrderConfirmation();
    }

    @Test
    @DisplayName(value = "[order-1] Order product with no login")
    @Description(value = "Order product with not authorised user")
    @Severity(SeverityLevel.NORMAL)
    public void orderProductWithNoLogin(){
        productPage.addCustomProductToCart("/?id_product=2&controller=product#/size-s/color-black");
        OrderPage orderPage = cartPage.clickCheckoutBtn();
        orderPage.checkSignInStep();
        loginPage.loginUser("fakeuser@gmail.com", "Userpass");
        orderPage.checkAddressStep();
        orderPage.clickProceedToCheckoutBtn();
        orderPage.checkShippingStep();
        orderPage.clickProceedToCheckoutBtn();
        orderPage.checkPaymentStep();
        orderPage.selectPaymentMethod();
        orderPage.clickProceedToCheckoutBtn();
        orderPage.checkOrderConfirmation();
    }
}
