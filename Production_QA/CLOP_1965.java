package Production_QA;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1965 {

	@Test
	public void CLOP_1965_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();

		// Launch the Metadata Dashboard and Login as a Program Manager.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(3000);
		
		// Navigate to the Advanced Search page.
		Chrome_Driver.findElement(By.xpath("//*[@id='advancedsearchlink']")).click();
		Thread.sleep(3000);
		
		// Test the Habitat Thumbnail that uses a default image.
		Chrome_Driver.findElement(By.xpath("//*[@id='clo_title']")).sendKeys("Test CLO 3");
		Chrome_Driver.findElement(By.xpath("//*[@type='submit']")).click();
		Assert.assertEquals("https://metadatadev.cloqa.edmesh.com/img/content-browser/cloitem.jpg", Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/a/img")).getAttribute("src"));
		Chrome_Driver.findElement(By.xpath("//*[@href='/advancedsearch']")).click();
		
		// Test the Habitat Thumbnail that has already been set by the cloqa.
		Chrome_Driver.findElement(By.xpath("//*[@id='clo_title']")).sendKeys("Demo Test CLO");
		Chrome_Driver.findElement(By.xpath("//*[@type='submit']")).click();
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/a/img")).getAttribute("src").contains("https://partner.inkling.com/rawfiles/sn_5353/img/clo04/red_flower.svg?access_token"));
		Chrome_Driver.findElement(By.xpath("//*[@href='/advancedsearch']")).click();
		
		// Test the Habitat Thumbnail for a CLO in which the User does not have access.
		Chrome_Driver.findElement(By.xpath("//*[@id='clo_title']")).sendKeys("Test Drive the Features");
		Chrome_Driver.findElement(By.xpath("//*[@type='submit']")).click();
		Assert.assertEquals("https://metadatadev.cloqa.edmesh.com/img/content-browser/permission-denied.png", Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/a/img")).getAttribute("src"));
		Chrome_Driver.findElement(By.xpath("//*[@href='/advancedsearch']")).click();
		
		// Logout and exit.
		mddbPage.logout();	
		
	}

}