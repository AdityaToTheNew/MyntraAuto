import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.*;
import utils.JsonReader;

import java.util.List;

public class MyntraTest extends BaseTest {

    @Test
    public void testSearchMultipleProducts() throws InterruptedException {
        // Read product names from the JSON file
        String filePath = "src/main/resources/products.json";
        List<String> products = JsonReader.getProductsFromJson(filePath);

        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        for (String product : products) {
            System.out.println("Searching for: " + product);


            homePage.searchProduct(product);


            searchPage.selectFirstProduct();


            Assert.assertTrue(driver.getWindowHandles().size() > 1, "Product page did not open in a new tab.");


            productPage.selectSize();
            productPage.addToCart();


            cartPage.goToCart();
            Assert.assertTrue(cartPage.isProductInCart(), "Product not found in cart");


            searchPage.closeExtraTabs();
            driver.navigate().to("https://www.myntra.com/");
        }
    }
}
