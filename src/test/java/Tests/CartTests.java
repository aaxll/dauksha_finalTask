package Tests;

import Fixtures.CartTestsFixture;
import com.codeborne.selenide.junit5.TextReportExtension;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

@Epic(value = "Cart")
@Feature(value = "Basic cart functionality")
@ExtendWith(TextReportExtension.class)
public class CartTests extends CartTestsFixture {

    @Test
    @DisplayName(value = "[cart-1] Add product to cart from product page")
    @Description(value = "Check adding product to cart from product page feature")
    @Severity(SeverityLevel.BLOCKER)
    public void addProductFromProductPage(){
        cartPage.isProductExistInCart(true);
        sleep(10000);
        productPage.addCustomProductToCart("/?id_product=2&controller=product#/size-s/color-black");
        cartPage.checkPrices();

    }

    @Test
    @DisplayName(value = "[cart-2] Delete product from cart")
    @Description(value = "Delete product from cart by trash button")
    @Severity(SeverityLevel.BLOCKER)
    public void deleteProduct(){
        cartPage.isProductExistInCart(true);
        cartPage.deleteProductFromCart();
        cartPage.isProductExistInCart(false);
    }
}
