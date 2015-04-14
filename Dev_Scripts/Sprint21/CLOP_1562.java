package Sprint21;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1562 {

	@Test
	public void CLOP_1562_Test() throws Exception {
	
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		String MinAge = "Minimum Age Range Missing";
		String MaxAge = "Maximum Age Range Missing";
		
		// Navigate to the Math K-5: Count On page that does not have a Learning Objective.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(3000);
		
		// Validate that in the Warnings list there is no entry about Minimum Age Range Missing or Maximum Age Range Missing.
		String noMinAge = null;
		String noMaxAge = null;
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[@class='list-group-item']/div/h4"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			if(sValue.equalsIgnoreCase(MinAge)) {
				noMinAge = "true";
				break;
	        }	
			if(sValue.equalsIgnoreCase(MaxAge)) {
				noMaxAge = "true";
				break;
			}
			
		}
		Assert.assertEquals(null, noMinAge);
		Assert.assertEquals(null, noMaxAge);
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(2000);	
		
		// Logout and exit
		mddbPage.logout();	
		
	}

}
