package PageObjects;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProductPage {

    @Step(value = "Add product to cart from its page")
    public void addProductToCartFromProductPage(){
        $("button.exclusive").click();
        $("div.layer_cart_product h2").shouldBe(Condition.visible);
    }

    @Step(value = "Add additional custom product to cart")
    public void addCustomProductToCart(String productURL){
        open(productURL);
        addProductToCartFromProductPage();
        open("/?controller=order");

    }
}
