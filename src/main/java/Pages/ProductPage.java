package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    // Method to select size and scroll down
    public void selectSize() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> sizeButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("button.size-buttons-size-button.size-buttons-size-button-default.size-buttons-big-size")
        ));

        boolean clicked = false;

        // Try to click the first clickable button
        for (WebElement sizeButton : sizeButtons) {
            try {
                if (sizeButton.isDisplayed() && sizeButton.isEnabled()) {
                    sizeButton.click();
                    System.out.println("Clicked size button: " + sizeButton.getText());
                    clicked = true;
                    break;  // Exit loop after successfully clicking a button
                }
            } catch (Exception e) {
                System.out.println("Failed to click size button: " + e.getMessage());
            }
        }

        if (!clicked) {
            System.out.println("No clickable size button found.");
        }

        // Scroll down slightly after selecting size
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 300);");   // Scrolls down by 300 pixels
    }

    // Method to add product to cart
    public void addToCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the Add to Bag button
        WebElement addToBagButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='mountRoot']/div/div[1]/main/div[2]/div[2]/div[2]/div[2]/div/div[1]")));

        addToBagButton.click();
        System.out.println("Added product to cart.");
    }
}
