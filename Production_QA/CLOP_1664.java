package Production_QA;

import org.junit.Assert;

import pages.MetadataDashboardPage;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CLOP_1664 {

	@Test
	public void CLOP_1664_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		
		// Test the presence of a loading message and image.
		WebDriverWait wait = new WebDriverWait(Chrome_Driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='pleasewait']")));
		Assert.assertEquals("https://metadatadev.cloqa.edmesh.com/img/preloader_ani4.gif", Chrome_Driver.findElement(By.xpath("//*[@id='loading']/div/img")).getAttribute("src"));
		Assert.assertEquals("Please Wait...", Chrome_Driver.findElement(By.xpath("//*[@id='pleasewait']")).getAttribute("innerText"));
				
		// Navigate to the CLO Parallax Project and test the presence of a loading message and image.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_2cb/29c60bdeb1184aa5a23e4c090f77a358");
		wait = new WebDriverWait(Chrome_Driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='loading-message']")));
		Assert.assertEquals("https://metadatadev.cloqa.edmesh.com/img/preloader_ani4.gif", Chrome_Driver.findElement(By.xpath("//*[@id='waiting']")).getAttribute("src"));
		Assert.assertEquals("Please Wait...", Chrome_Driver.findElement(By.xpath("//*[@id='pleasewait']")).getAttribute("innerText"));
		Assert.assertEquals("Retrieving Metadata.", Chrome_Driver.findElement(By.xpath("//*[@id='loading-message']")).getAttribute("innerText"));
		Thread.sleep(10000);
		
		// Logout and exit.
		mddbPage.logout();
		
	}

}