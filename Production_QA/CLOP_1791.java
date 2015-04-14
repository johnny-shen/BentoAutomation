package Production_QA;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.MetadataDashboardPage;

public class CLOP_1791 {

	@Test
	public void CLOP_1791_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard .
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		
		// Navigate to the Grace CLO QA page and go to the Concept card.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_5353/137c0031451f49399700a2236792ce07");
		Thread.sleep(10000);
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(2000);
		
		// Test a Paragraph asset and ensure that the preview matches the Universal form
		WebElement menu = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[1]/a"));
		Actions builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		WebDriverWait wait = new  WebDriverWait(Chrome_Driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[1]/a"))); 
		String paragraphtitle = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[1]/a")).getAttribute("data-type");
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[1]/a")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[1]/div/div[2]/p"))); 
		String paragraphtip = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[1]/div/div[2]/p")).getText();
		Assert.assertEquals(paragraphtitle, Chrome_Driver.findElement(By.xpath("//*[@id='content-title-area']")).getText());
		Assert.assertEquals(paragraphtip, Chrome_Driver.findElement(By.xpath("//*[@id='content-preview']/div")).getText());
		
		// Test a Math asset and ensure that the preview matches the Universal form
		menu = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[2]/a"));
		builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		wait = new  WebDriverWait(Chrome_Driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[2]/div"))); 
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[2]/a"))); 
		Thread.sleep(5000);
		String mathtitle = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[2]/a")).getAttribute("data-type");		
		Assert.assertEquals("MathJax-Element-1", Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[2]/div/div[2]/script")).getAttribute("id"));
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[2]/a")).click();
		Assert.assertEquals(mathtitle, Chrome_Driver.findElement(By.xpath("//*[@id='content-title-area']")).getText());
	
		// Test an Image asset and ensure that the preview matches the Universal form
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[2]/a")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[2]/ul/li[4]/a")).click();
		menu = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[2]/ul/li[4]/a"));
		builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		wait = new  WebDriverWait(Chrome_Driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[2]/ul/li[4]/div/div[2]/img"))); 
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[2]/ul/li[4]/a"))); 
		Thread.sleep(3000);
		String imagetitle = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[2]/ul/li[4]/a")).getAttribute("data-type");
		String imagetip = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[2]/ul/li[4]/div/div[2]/img")).getAttribute("src");
		Assert.assertEquals(imagetitle, Chrome_Driver.findElement(By.xpath("//*[@id='content-title-area']")).getText());
		Assert.assertEquals(imagetip, Chrome_Driver.findElement(By.xpath("//*[@id='content-preview']/img")).getAttribute("src"));
		Assert.assertEquals("width: auto; max-height: 200px; max-width: 100%;", Chrome_Driver.findElement(By.xpath("//*[@id='content-preview']/img")).getAttribute("style"));
		
		// Test a Widget asset and ensure that the preview shows the desired functions on the Universal form.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[2]/a")).click();
		Thread.sleep(5000);
		menu = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[2]/ul/li[3]/a"));
		builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		wait = new  WebDriverWait(Chrome_Driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[2]/ul/li[3]/a"))); 
		Thread.sleep(3000);
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[2]/ul/li[3]/a")).click();
		Thread.sleep(3000);
		String widgettitle = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[2]/ul/li[3]/a")).getAttribute("data-type");
		String widgettip = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[2]/ul/li[3]/a")).getAttribute("data-widgetsource");
		Assert.assertEquals(widgettitle, Chrome_Driver.findElement(By.xpath("//*[@id='content-title-area']")).getText());
		Assert.assertEquals(widgettip, Chrome_Driver.findElement(By.xpath("//*[@id='content-preview']//iframe")).getAttribute("src"));
		Assert.assertEquals("widget-action-icon", Chrome_Driver.findElement(By.xpath("//*[@id='content-title-area']/div")).getAttribute("class"));
		Chrome_Driver.findElement(By.xpath("//*[@id='content-title-area']/div")).click();
		Thread.sleep(1000);
		Assert.assertEquals("widget-action-icon close", Chrome_Driver.findElement(By.xpath("//*[@id='content-title-area']/div")).getAttribute("class"));
		Chrome_Driver.findElement(By.xpath("//*[@id='content-title-area']/div")).click();
		Thread.sleep(1000);
		Assert.assertEquals("widget-action-icon", Chrome_Driver.findElement(By.xpath("//*[@id='content-title-area']/div")).getAttribute("class"));
	
		// Logout and exit.
		mddbPage.logout();	
		
	}

}