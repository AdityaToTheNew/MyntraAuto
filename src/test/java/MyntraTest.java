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

            // Search for the product
            homePage.searchProduct(product);

            // Select the first product
            searchPage.selectFirstProduct();

            // Verify the product page opens in a new tab
            Assert.assertTrue(driver.getWindowHandles().size() > 1, "Product page did not open in a new tab.");

            // Select size and add the product to the cart
            productPage.selectSize();
            productPage.addToCart();

            // Close extra tabs and navigate back to the home page
            searchPage.closeExtraTabs();
            driver.navigate().to("https://www.myntra.com/");
        }


        homePage.clickBagIcon();


        Assert.assertTrue(cartPage.isProductInCart(), "One or more products are missing from the cart.");


        cartPage.placeOrderAndVerifyLoginRedirect();


        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Failed to navigate to the login page.");
    }
}
