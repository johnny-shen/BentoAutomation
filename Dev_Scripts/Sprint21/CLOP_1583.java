package Sprint21;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1583 {

	@Test
	public void CLOP_1583_Test() throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		String contentbrowser = "Content Browser";
		String taggingtool = "Tagging Tool";

		// Validate that in the Metadata Dashboard the "Tagging Tool" tile no longer appears and "Content Browser" appears instead.
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@class='application-tool-links']/div/h4"));	
		String cbtile = null; 
		for  (WebElement eachResult : AllSearchResults) {
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase(taggingtool));
			if (sValue.equalsIgnoreCase(contentbrowser)) {
				cbtile = "true";
			}		
		}
		Assert.assertEquals("true", cbtile);
		
		// Expand the main drop down bar and validate that the "Tagging Tool" tile no longer appears and "Content Browser" appears instead.
		Chrome_Driver.findElement(By.xpath("//*[@id='hamburger-nav']")).click();
		Thread.sleep(1000);
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='hamburger-menu']/ul/li/a/p"));	
		cbtile = null; 
		for  (WebElement eachResult : AllSearchResults) {
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase(taggingtool));
			if (sValue.equalsIgnoreCase(contentbrowser)) {				
				cbtile = "true";
			}		
		}
		Assert.assertEquals("true", cbtile);
		
		// Logout and exit.
		mddbPage.logout();
		
	}

}