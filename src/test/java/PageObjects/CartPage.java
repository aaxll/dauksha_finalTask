package PageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {

    @Step(value = "Check product availability in cart")
    public void isProductExistInCart(boolean condition){
        if (condition)
        {
            $("td.cart_product").shouldBe(Condition.visible);
        }
        else if(!condition)
        {
            $("td.cart_product").shouldNotBe(Condition.exist);
        }
    }

    @Step(value = "Delete product from cart by trash button")
    public void deleteProductFromCart(){
        $("i.icon-trash").click();
    }

    @Step(value = "Check product prices logic")
    public void checkPrices(){
        SelenideElement oneProduct;
        String oneProductPriceStr;
        float totalShippingPrice;
        float totalTaxPrice;
        float totalPriceWithShipping = 0;
        float totalPriceWithShippingAndTax = 0;
        float oneProductPrice;
        float productQty;
        float oneProductTotalPrice;
        float totalPriceWithoutShippingAndTax = 0;

        totalShippingPrice = priceToFloat($("#total_shipping"));
        totalTaxPrice = priceToFloat($("#total_tax"));

        ElementsCollection allProductsInCart = $$("tr.cart_item");
        for (int i = 0; i < allProductsInCart.size(); i++) {
            oneProduct = allProductsInCart.get(i);
            oneProductPrice = priceToFloat(oneProduct.$("td.cart_unit>span.price>span.price"));
            System.out.println(oneProduct.$("input.cart_quantity_input").getText());
            productQty = Float.parseFloat(oneProduct.$("td.cart_quantity>input[type=\"hidden\"]").getAttribute("value"));
            oneProductTotalPrice = oneProductPrice*productQty;
            step("Check total price for each product in cart (productPrice*Qty)");
            oneProduct.$("span.price").shouldBe(Condition.text("$"+oneProductTotalPrice));
            totalPriceWithoutShippingAndTax = totalPriceWithoutShippingAndTax + oneProductTotalPrice;
            totalPriceWithShipping = totalPriceWithoutShippingAndTax + totalShippingPrice;
            totalPriceWithShippingAndTax = totalPriceWithShipping + totalTaxPrice;
        }
        step("Check total price for all products in cart without shipping and tax");
        $("#total_product").shouldBe(Condition.text("$"+ roundPrice(totalPriceWithoutShippingAndTax)));
        step("Check total price for all products in cart with shipping but tax");
        $("#total_price_without_tax").shouldBe(Condition.text("$"+ roundPrice(totalPriceWithShipping)));
        step("Check total price for all products in cart with shipping and tax");
        $("#total_price").shouldBe(Condition.text("$"+ roundPrice(totalPriceWithShippingAndTax)));
    }

    public float roundPrice(float price){
        return (float) (Math.round(price * 100.0) / 100.0);
    }

    public float priceToFloat(SelenideElement selenideElement){
       String str = selenideElement.getText().replaceAll("[^0-9\\.]", "");
       return Float.parseFloat(str);
    }

    @Step("{stepName}")
    public void step(String stepName)
    {
    }
}
