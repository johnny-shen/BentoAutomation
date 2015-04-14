package Sprint23;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.MetadataDashboardPage;

public class CLOP_1947 {

	@Test
	public void CLOP_1947_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the QA Metadata Dashboard and Login as a Program Manager.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(3000);
		
		// Navigate to a Summary Page for a CLO that has been published on Dev but not on QA. Validate that the History table is empty.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/clo/sn_5353/9ac201ec75f54f27a6d30b8cab1faccb/");
		Thread.sleep(3000);
		Assert.assertEquals("There are no logged events to display at this time.", Chrome_Driver.findElement(By.xpath("//*[@id='history-table']/tbody/tr/td/p")).getText());
		
		// Logout and exit.
		mddbPage.logout();	
		
	}

}