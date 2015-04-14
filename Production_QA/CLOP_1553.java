package Production_QA;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1553 {

	@Test
	public void CLOP_1553_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
		
		// Launch the Metadata Dashboard and Login.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Thread.sleep(3000);

		// Validating that the Learning Statements tile in the Metadata Dashboard should no longer appear.
		String LS = null;
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@class='row dashboard-links text-center']/div/a"));
		for  (WebElement eachResult : AllSearchResults) {
			String sValue = null;
			sValue = eachResult.getAttribute("href");	
			if(sValue.equalsIgnoreCase("https://metadatadev.cloqa.edmesh.com/learning-statements/")){
				LS = "true";
				break;
	        }	
		}
		Assert.assertEquals(null, LS);   
		
		// Validating that Learning Objectives tile in the Metadata Dashboard still appears and clicking it will navigate to a Learning Objectives/Statements page.
		String LO = null;
		String LOicon = "https://metadatadev.cloqa.edmesh.com/img/home/nav-icon-lo-ls.png";
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@class='row dashboard-links text-center']/div/a/img"));
		for  (WebElement eachResult : AllSearchResults) {
			String sValue = null;
			sValue = eachResult.getAttribute("src");		
			if (sValue.equalsIgnoreCase(LOicon)) {
				LO = "true";
				eachResult.click();
				Thread.sleep(5000);
				break;
			}		
		}
		Assert.assertEquals("true", LO);   
		
		// Validate that Learning Objectives and Learning Statement tabs appear.
		Assert.assertEquals("Learning Objectives", Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/ul/li[1]/a")).getText());
		Assert.assertEquals("Learning Objectives | Metadata Dashboard", Chrome_Driver.getTitle());
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/ul/li[2]/a")).click();
		Thread.sleep(1000);
		Assert.assertEquals("Learning Statements", Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/ul/li[2]/a")).getText());
		Assert.assertEquals("Learning Statements | Metadata Dashboard", Chrome_Driver.getTitle());
		
		// Logout and exit
		mddbPage.logout();
		
	}

}