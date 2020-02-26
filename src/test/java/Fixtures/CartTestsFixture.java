package Fixtures;

import PageObjects.CartPage;
import PageObjects.LoginPage;
import PageObjects.ProductPage;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class CartTestsFixture extends TestBase{

    public CartPage cartPage;
    public ProductPage productPage;

    @BeforeEach
    public void cartTestsFixture(){
        productPage = open("/?id_product=1&controller=product", ProductPage.class);
        productPage.addProductToCartFromProductPage();
        cartPage = open("/?controller=order", CartPage.class);
    }
}
