package launchCleanUp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import launchCleanUp.ScanExcelData;

public class BuyerSite {

	@Test
	public void cxbuyer() throws InvalidFormatException, IOException, InterruptedException {
    	TimeUnit.MINUTES.sleep(3);
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		String[] data = ScanExcelData.readMID("Sheet1");
		String baseurl = "https://www.amazon.eg/";
		for(int i=0; i<data.length ; i++) {
			String cxurl = baseurl+"s?me="+data[i]+"&marketplaceID=ARBP9OOSHTCHU";
			driver.get(cxurl);
			try {
			if (driver.findElement(By.xpath("//*[contains(@class,'messages-no-results')]")).isDisplayed()) {
				System.out.println("seller is not displaying in retail site :"+data[i]);
			}}catch(Exception e)
			{
				System.out.println("Seller is displaying in retail site:"+data[i]);
			}
				
		}
		driver.close();
	}
}
