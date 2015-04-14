package Production_QA;

import org.junit.Assert;

import pages.MetadataDashboardPage;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CLOP_1718 {

	@Test
	public void CLOP_1718_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		
		// Navigate to the Show Pieces project and validate that a complete LCO image is pink and an incomplete CLO is blue.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapters/sn_2cb");
		Thread.sleep(10000);
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='project-overview']/div[1]/a")).size();		
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='project-overview']/div[1]/a["+i+"]/p")).getText();
			if(sValue.equalsIgnoreCase("Photosynthesis 2")){
				Assert.assertEquals("https://metadatadev.cloqa.edmesh.com/img/content-browser/cloitem.jpg", Chrome_Driver.findElement(By.xpath("//*[@id='project-overview']/div[1]/a["+i+"]/div/img")).getAttribute("src"));
	        }
			if(sValue.equalsIgnoreCase("Blink")){
				Assert.assertEquals("https://metadatadev.cloqa.edmesh.com/img/content-browser/contentitem.jpg", Chrome_Driver.findElement(By.xpath("//*[@id='project-overview']/div[1]/a["+i+"]/div/img")).getAttribute("src"));
	        }
		}
		
		// Navigate to the Math K-5: Count On To Add CLO and validate the images including the mouse over tool tip for the Overview and CMPA links.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		WebElement menu = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/a"));
		Actions builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		WebDriverWait wait = new  WebDriverWait(Chrome_Driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='chapterHierarchy']/ul/li/a"))); 
		Assert.assertEquals("/img/content-browser/cloitem.jpg", Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/a")).getAttribute("data-image"));
		
		menu = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[2]/a"));
		builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		Thread.sleep(2000);
			
		menu = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a"));
		builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		wait = new  WebDriverWait(Chrome_Driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/div/img"))); 
		Assert.assertEquals("https://metadatadev.cloqa.edmesh.com/img/content-browser/concept.png", Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/div/img")).getAttribute("src"));

		menu = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[2]/a"));
		builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		wait = new  WebDriverWait(Chrome_Driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[2]/div/img"))); 
		Assert.assertEquals("https://metadatadev.cloqa.edmesh.com/img/content-browser/model.png", Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[2]/div/img")).getAttribute("src"));

		menu = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[3]/a"));
		builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		wait = new  WebDriverWait(Chrome_Driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[3]/div/img"))); 
		Assert.assertEquals("https://metadatadev.cloqa.edmesh.com/img/content-browser/practice.png", Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[3]/div/img")).getAttribute("src"));
		
		menu = Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[6]/a"));
		builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		wait = new  WebDriverWait(Chrome_Driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[6]/div/img"))); 
		Assert.assertEquals("https://metadatadev.cloqa.edmesh.com/img/content-browser/assessment.png", Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[6]/div/img")).getAttribute("src"));

		// Logout and exit.
		mddbPage.logout();	
		
	}

}