package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Offerlisting {
@DataProvider(name="TestData")
public Object[][] fetchData() throws InvalidFormatException, IOException{
String[][] data = RdExcelData.readExcelData("Listing Offer");
return data;
}
	
@Test(dataProvider="TestData")

public void listingOffer(String Product,String SKU,String Price,String Quantity) throws InterruptedException {

//open chrome driver with seller central url
WebDriverManager.chromedriver().setup();
ChromeDriver driver = new ChromeDriver();
driver.get("https://sellercentral.amazon.eg/home?");
driver.manage().window().maximize();

//enter email id
driver.findElement(By.id("ap_email")).sendKeys("Meenasv+selfshipeg@amazon.com");

//Enter password
driver.findElement(By.id("ap_password")).sendKeys("Kalps@123");

//click sign in button
driver.findElement(By.id("signInSubmit")).click();

//click marketplace
driver.findElement(By.className("picker-switch-accounts-button")).click();

//Mouse hover on Inventory
WebElement inventory = driver.findElement(By.xpath("(//a[@class='sc-menu-trigger sc-tab-a'])[2]"));
Actions Builder = new Actions(driver);
Builder.moveToElement(inventory).build().perform();

//Click Add a Product
driver.findElement(By.linkText("Add a Product")).click();

//click search bar in Add a Product page
Thread.sleep(2000);
driver.findElement(By.xpath("//input[@placeholder='Product name, UPC, EAN, ISBN, or ASIN']")).click();

//Enter the value in the search bar
driver.findElement(By.xpath("//input[@placeholder='Product name, UPC, EAN, ISBN, or ASIN']")).sendKeys(Product);

//Click search icon
driver.findElement(By.xpath("//div[@id='product-search-container']/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/kat-input-group[1]/button[1]/kat-icon[1]/i[1]")).click();

//click select a condition
Thread.sleep(5000);
driver.findElement(By.xpath("//div[@class='kat-select-container small']//div")).click();

//Select new in select a condition
driver.findElement(By.xpath("//div[@data-name='New']//div[1]")).click();

//click sell this product button
driver.findElement(By.xpath("//a[@class='copy-kat-button primary']")).click();

//switch to next window
Thread.sleep(5000);
Set<String> nextWindow = driver.getWindowHandles();
List<String> lstWindowHandles = new ArrayList<String>(nextWindow);
driver.switchTo().window(lstWindowHandles.get(1));

//select seller SKU
Thread.sleep(2000);
WebElement SellerSKU = driver.findElement(By.id("item_sku"));
SellerSKU.sendKeys(SKU);
	    
//select price
driver.findElement(By.id("standard_price")).sendKeys(Price);

//Select quantity
driver.findElement(By.id("quantity")).sendKeys(Quantity);

//select condition
WebElement eleSave=driver.findElementByClassName("allow-disabled-clicks");
int Save_Y = eleSave.getLocation().getY();
driver.executeScript("window.scrollTo(0, "+Save_Y+");",eleSave);
driver.findElement(By.id("condition_type")).click();

//Select new in the dropdown
JavascriptExecutor jse = (JavascriptExecutor)driver;
WebElement Condition = (WebElement)jse.executeScript("return document.querySelector(\"#condition_type\").shadowRoot.querySelector(\"div.kat-select-container > div.select-options > div > slot > kat-option:nth-child(2) > div\")");
        
//Click Save and Finish button
driver.findElement(By.className("allow-disabled-clicks")).click();
		
//verify the product has been updated
driver.switchTo().defaultContent();
Thread.sleep(5000);
String productUpdated = driver.findElement(By.xpath("//h4[@class='a-alert-heading']")).getText();
System.out.println(productUpdated);
if (productUpdated.contains("submitted")) 
{
System.out.println("The offer is listed successfully");
}
else
{
System.out.println("The offer is not listed");
}
		
driver.quit();
}
}

