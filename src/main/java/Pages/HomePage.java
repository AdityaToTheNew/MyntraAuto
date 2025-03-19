package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public void searchProduct(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate and interact with the search box
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@class='desktop-searchBar']")));

        searchBox.clear();
        searchBox.sendKeys(productName);
        searchBox.sendKeys(org.openqa.selenium.Keys.ENTER);  // Trigger search
    }


    public void clickBagIcon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate the Bag icon
        WebElement bagIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(@class, 'myntraweb-sprite') and contains(@class, 'desktop-iconBag')]")));

        bagIcon.click();
        System.out.println("Clicked on the Bag icon.");
    }
}
