package Sprint22;

import org.openqa.selenium.WebElement;
import pages.MetadataDashboardPage;
import org.openqa.selenium.interactions.Actions;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CLOP_1639 {

	@Test
	public void CLOP_1639_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
				
		// Navigate to the CLO Parallax Project Concept page and test the Preview assets on the left side bar on the universal form.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2cb/29c60bdeb1184aa5a23e4c090f77a358");
		Thread.sleep(10000);
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(5000);

		// Validate a tool tip appears for Image.
		WebElement menu = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[1]/a"));
		Actions builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		Thread.sleep(2000);
		Assert.assertEquals("Image", Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[1]/a")).getAttribute("data-type"));
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[1]/div/div[2]/img")).getAttribute("src").contains("https://partner.inkling.com/rawfiles/sn_2cb/head/img/chapter02/clobackgroun.jpeg?access_token"));		
		
		// Validate a tool tip appears for Paragraph.
		menu = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[2]/a"));
		builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		Thread.sleep(2000);
		Assert.assertEquals("Paragraph", Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[2]/a")).getAttribute("data-type"));
		Assert.assertEquals("This goes with the entire page", Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[2]/a")).getAttribute("data-paragraph"));

		// Validate a tool tip appears for Table.
		menu = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[4]/a"));
		builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		Thread.sleep(2000);
		Assert.assertEquals("Table", Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[4]/a")).getAttribute("data-type"));
		
		// Validate for Widgets a Widget Preview appears.
		menu = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[12]/a"));
		builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		WebDriverWait wait = new  WebDriverWait(Chrome_Driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='widget-content-preview']")));
		Thread.sleep(2000);
		Assert.assertEquals("Widget", Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[12]/a")).getAttribute("data-type"));
		Assert.assertEquals("Preview", Chrome_Driver.findElement(By.xpath("//*[@id='widget-content-preview']")).getText());

		// Logout and exit.
		mddbPage.logout();	
		
	}

}