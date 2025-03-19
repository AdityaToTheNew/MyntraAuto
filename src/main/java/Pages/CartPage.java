package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By goToBagButton = By.xpath("//*[@id='mountRoot']/div/div[1]/main/div[2]/div[2]/div[2]/div[2]/div/a");
    private By cartItem = By.xpath("//div[@class='itemContainer-base-brand']");

    // Method to go to the cart
    public void goToCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the "Go to Bag" button and click it
        WebElement bagButton = wait.until(ExpectedConditions.elementToBeClickable(goToBagButton));
        bagButton.click();

        System.out.println("Navigated to cart.");
    }

    // Method to verify if the product is in the cart
    public boolean isProductInCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(cartItem));
            return item.isDisplayed();
        } catch (Exception e) {
            System.out.println("No product found in the cart.");
            return false;
        }
    }
}
