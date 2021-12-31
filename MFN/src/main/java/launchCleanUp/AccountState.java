package launchCleanUp;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import launchCleanUp.FetchExcelData;

public class AccountState {
	@DataProvider(name="AccountData")
	public Object[][] fetchData() throws InvalidFormatException, IOException{
	String[][] data = FetchExcelData.FdExcelData("Account details");
	return data;
	} 

	@Test(dataProvider="AccountData")
	public void accstate(String Username,String Password) throws InvalidFormatException, IOException, InterruptedException {
			
		//open chrome driver with seller central url
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://sellercentral.amazon.eg/home?");
		driver.manage().window().maximize();

		//enter email id
		driver.findElement(By.id("ap_email")).sendKeys(Username);

		//Enter password
		driver.findElement(By.id("ap_password")).sendKeys(Password);

		//click sign in button
		driver.findElement(By.id("signInSubmit")).click();
		
		//if()
		
		//Hover on settings
		WebElement settings = driver.findElement(By.id("sc-quicklink-settings"));
		Actions Builder = new Actions(driver);
		Builder.moveToElement(settings).build().perform();
		
		//Click Account info
		driver.findElement(By.xpath("//li[@class='sc-acctinfo-quicklink']")).click();
		
		//click Go on the vacation
		driver.findElement(By.id("headerurl-ListingStatus")).click();
		
		//verify the accountstate is active or inactive
		WebElement radioActive = driver.findElement(By.xpath("//input[@value='true']"));
		boolean select = radioActive.isSelected();
		
		// performing click operation if element is not already selected
		if (select == true) {
			driver.findElement(By.xpath("//input[@value='false']")).click();
			driver.findElement(By.xpath("//input[@class='a-button-input']")).click();
			System.out.println("The account state has made inactive");
		}
		else {
			WebElement radioInactive = driver.findElement(By.xpath("//input[@value='false']"));
			boolean choose = radioInactive.isSelected();
			System.out.println("The account state is inactive ");
			driver.close();
		}
		}
		
	}
	



