package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // ✅ Navigate to cart
    public void goToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click the bag icon to navigate to the cart page
        WebElement bagIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@class='desktop-cart']")));
        bagIcon.click();
        System.out.println("Navigated to the cart page.");
    }

    // ✅ Verify if the product is in the cart
    public boolean isProductInCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='itemContainer-base-brand']")));
            return cartItem.isDisplayed();
        } catch (Exception e) {
            System.out.println("No product found in the cart: " + e.getMessage());
            return false;
        }
    }

    // ✅ Click "Place Order" and verify navigation to login page
    public void placeOrderAndVerifyLoginRedirect() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // ✅ Wait for "Place Order" button
            WebElement placeOrderButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(text(),'PLACE ORDER')]")));

            // ✅ Click using JavaScript Executor for reliability
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", placeOrderButton);
            System.out.println("Clicked on 'Place Order'.");

            // ✅ Wait for login page redirection
            boolean isOnLoginPage = wait.until(ExpectedConditions.urlContains("login"));

            if (isOnLoginPage) {
                System.out.println("Successfully redirected to the login page.");
            } else {
                System.out.println("Failed to navigate to the login page.");
            }

        } catch (Exception e) {
            System.out.println("Error while placing the order: " + e.getMessage());
        }
    }
}
