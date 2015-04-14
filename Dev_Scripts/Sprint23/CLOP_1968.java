package Sprint23;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1968 {

	@Test
	public void CLOP_1968_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();

		// Launch the Metadata Dashboard and Login as a Program Manager.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(3000);
		
		// Enter some text in the Simple Search.
		Chrome_Driver.findElement(By.xpath("//*[@id='search-term']")).sendKeys("test");
		Chrome_Driver.findElement(By.xpath("//*[@id='search-term']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		// Validate that the word test is highlighted and the Search Terms show: CLO Title: test
		Assert.assertEquals("test", Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/h4/a/strong")).getText());
		Assert.assertEquals("×\nClose\nCLO Title: test", Chrome_Driver.findElement(By.xpath("//*[@id='search_parameters']/li/a")).getText());
		
		// Search for an author and validate that no search results are found.
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Chrome_Driver.findElement(By.xpath("//*[@id='search-term']")).sendKeys("Johnny Shen");
		Chrome_Driver.findElement(By.xpath("//*[@id='search-term']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		Assert.assertEquals("Sorry, there weren't any results that matched \"Johnny Shen\".", Chrome_Driver.findElement(By.xpath("//*[@id='no-results']/h4")).getText());
		
		// Logout and exit.
		mddbPage.logout();	
		
	}

}