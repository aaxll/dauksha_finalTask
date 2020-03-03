package Fixtures;

import PageObjects.CartPage;
import PageObjects.LoginPage;
import PageObjects.ProductPage;

public class OrderTestsFixture extends TestBase{
    public LoginPage loginPage = new LoginPage();
    public ProductPage productPage = new ProductPage();
    public CartPage cartPage = new CartPage();
}
