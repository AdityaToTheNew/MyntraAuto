package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    // Method to select the first product and switch to the new tab
    public void selectFirstProduct() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click on the first product
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[contains(@class,'product-base')]")));
        firstProduct.click();

        // Switch to the new tab
        switchToNewTab();
    }

    // Method to switch to the new tab
    private void switchToNewTab() {
        String currentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String handle : windowHandles) {
            if (!handle.equals(currentWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    // Method to close extra tabs except the main one
    public void closeExtraTabs() {
        String mainTab = driver.getWindowHandles().iterator().next();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainTab)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(mainTab);  // Switch back to the main tab
    }
}
