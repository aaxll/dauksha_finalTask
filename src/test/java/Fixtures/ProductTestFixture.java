package Fixtures;

import PageObjects.CartPage;
import PageObjects.ProductPage;
import PageObjects.WishlistPage;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class ProductTestFixture extends TestBase{

    public ProductPage productPage;
    public CartPage cartPage = new CartPage();
    public WishlistPage wishlistPage = new WishlistPage();

    @BeforeEach
    public void cartTestsFixture(){
        productPage = open("/?id_product=2&controller=product#/size-s/color-black", ProductPage.class);
    }
}
