package Fixtures;

import PageObjects.CartPage;
import PageObjects.ProductPage;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class ProductTestFixture extends TestBase{

    public ProductPage productPage;

    @BeforeEach
    public void cartTestsFixture(){
        productPage = open("/?id_product=2&controller=product", ProductPage.class);
    }
}
