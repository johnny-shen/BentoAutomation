package Sprint22;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1660 {

	@Test
	public void CLOP_1660_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		
		// Validate the existence of an Advanced Search link and after clicking it an Advanced Search page is displayed.
		Assert.assertEquals("Advanced Search", Chrome_Driver.findElement(By.xpath("//*[@id='advancedsearchlink']")).getText());
		Chrome_Driver.findElement(By.xpath("//*[@id='advancedsearchlink']")).click();
		Assert.assertEquals("Advanced Search | Metadata Dashboard", Chrome_Driver.getTitle());
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(2000);
		
		// Validate the Advanced Search link appears in the Content Browser, Learning Objective, and Metadata Import pages in the header as well.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/learning-objectives/");
		Assert.assertEquals("Advanced Search", Chrome_Driver.findElement(By.xpath("//*[@id='advancedsearchlink']")).getText());
		Thread.sleep(3000);
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(2000);
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/");
		Assert.assertEquals("Advanced Search", Chrome_Driver.findElement(By.xpath("//*[@id='advancedsearchlink']")).getText());
		Thread.sleep(3000);
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(2000);
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/metadata-import/");
		Assert.assertEquals("Advanced Search", Chrome_Driver.findElement(By.xpath("//*[@id='advancedsearchlink']")).getText());
		Thread.sleep(3000);

		// Logout and exit.
		mddbPage.logout();
		
	}

}