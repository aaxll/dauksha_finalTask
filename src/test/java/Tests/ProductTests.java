package Tests;

import Fixtures.ProductTestFixture;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

@Epic(value = "Product")
@Feature(value = "Product page features")
public class ProductTests extends ProductTestFixture {

    @Test
    @DisplayName(value = "[pp-1] Product page URL get parameters")
    @Description(value = "Check get parameters in product URL")
    @Severity(SeverityLevel.CRITICAL)
    public void getParametersInUrl(){
        productPage.selectSize("L");
        productPage.selectColor("White");
    }

    @Test
    @DisplayName(value = "[pp-2] Product Qty changing")
    @Description(value = "Product Qty must change after pressing -/+ icons")
    @Severity(SeverityLevel.CRITICAL)
    public void productQtyChanging(){
        productPage.increaseProductQty();
        productPage.decreaseProductQty();
    }

    @Test
    @DisplayName(value = "[pp-3] Qty input highlight")
    @Description(value = "Qty input should have red highlight if product Qty=0")
    @Severity(SeverityLevel.MINOR)
    public void productQtyHighlight(){
        productPage.setProductQty("0");
        productPage.checkInputHighlight();
    }

    @Test
    @DisplayName(value = "[pp-4] Add zero product qty to cart")
    @Description(value = "When you try to add a product with zero qty, an error message appears.")
    @Severity(SeverityLevel.MINOR)
    public void productZeroQtyToCart(){
        productPage.setProductQty("0");
        productPage.clickAddToCartBtn();
        productPage.checkErrorMessage();
        cartPage.goToCartPage();
        cartPage.isProductExistInCart(false);
    }

    @Test
    @DisplayName(value = "[pp-5] Show all pictures anchor on product page")
    @Description(value = "All pictures from the product description are displayed by clicking “Display all pictures”")
    @Severity(SeverityLevel.MINOR)
    public void displayAllPictures(){
        productPage.clickDisplayAllPictures();
        productPage.checkPicturesVisibility();
    }

    @Test
    @DisplayName(value = "[pp-6] Add to wishlist feature")
    @Description(value = "The product must be added to the user's wish list")
    @Severity(SeverityLevel.NORMAL)
    public void addToWishList(){
        productPage.clickAddToWishlist();
        productPage.checkWishlistMessage();
        wishlistPage.goWishlistPage();
        wishlistPage.isProductExistInWishlist();

    }
}
