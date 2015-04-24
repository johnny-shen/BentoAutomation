package Sprint27;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MetadataDashboardPage;

public class CLOP_2306 {

	@Test
	public void CLOP_2306_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "src\\pages\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
		
		// Launch the Metadata Dashboard.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(5000);
				
		// Click on the MY MOST RECENT CLOS more... link and then on the Content Browser.
		Chrome_Driver.findElement(By.xpath("//*[@href='/users/clo?status=recent']")).click();
		Thread.sleep(5000);	
		Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-listing']/div[5]/div/div[2]/div/div/div[2]/div/a/p")).click();
		Thread.sleep(8000);	
		Assert.assertEquals("TaggingTool | Metadata Dashboard", Chrome_Driver.getTitle());
		
		// Navigate back to the homepage and validate that the access CLO appears on the top of the MY MOST RECENT CLOS list.
		String clotitle = Chrome_Driver.findElement(By.xpath("//*[@id='tag-page-name-text']")).getText();
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(3000);
		Assert.assertEquals(clotitle, Chrome_Driver.findElement(By.xpath("//*[@id='application-clo-container']/ol/li[1]/a")).getText());
		
		// Click on the MY MOST RECENT CLOS more... link and validate it appears as the first CLO in both detail and list.
		Chrome_Driver.findElement(By.xpath("//*[@href='/users/clo?status=recent']")).click();
		Thread.sleep(5000);	
		String clotitle1 = Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-listing']/div[1]/div/div[2]/p/span[1]")).getText();
		String clotitle2 = clotitle1.substring(0,8);
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-listing']/div[1]/div/div[2]/p/span[1]")).getText().contains(clotitle2));
		Chrome_Driver.findElement(By.xpath("//*[@data-viewtype='list']")).click();
		Thread.sleep(3000);	
		Assert.assertEquals("CLO: " + clotitle, Chrome_Driver.findElement(By.xpath("//*[@class='table']/tbody/tr[1]/td[1]/div[2]")).getText());
		
		// Validate the tooltips appear after hovering over the three links.		
		WebElement menu = Chrome_Driver.findElement(By.xpath("//*[@data-original-title='Habitat']"));
		Actions builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		WebDriverWait wait = new  WebDriverWait(Chrome_Driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='tooltip-inner']"))); 
		Thread.sleep(2000);	
		Assert.assertEquals("Habitat", Chrome_Driver.findElement(By.xpath("//*[@class='tooltip-inner']")).getText());
		
		menu = Chrome_Driver.findElement(By.xpath("//*[@data-original-title='Content Browser']"));
		builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		wait = new  WebDriverWait(Chrome_Driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='tooltip-inner']"))); 
		Thread.sleep(2000);	
		Assert.assertEquals("Content Browser", Chrome_Driver.findElement(By.xpath("//*[@class='tooltip-inner']")).getText());
		
		menu = Chrome_Driver.findElement(By.xpath("//*[@data-original-title='Player']"));
		builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		wait = new  WebDriverWait(Chrome_Driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='tooltip-inner']")));
		Thread.sleep(2000);	
		Assert.assertEquals("Player", Chrome_Driver.findElement(By.xpath("//*[@class='tooltip-inner']")).getText());
		
		// Validate that the colors for published, updated, and deleted under Current Status are correct.
		List<WebElement> clolist=Chrome_Driver.findElements(By.xpath("//*[@class='table']/tbody/tr/td[2]/span"));
		for  (WebElement eachResult : clolist) {
		    String value = eachResult.getText();
		    String valuecolor = eachResult.getCssValue("color");
			if (value.equalsIgnoreCase("published")) {
				Assert.assertEquals("rgba(0, 128, 0, 1)", valuecolor);
			}
			if (value.equalsIgnoreCase("updated")) {
				Assert.assertEquals("rgba(255, 165, 0, 1)", valuecolor);
			}
			if (value.equalsIgnoreCase("private")) {
				Assert.assertEquals("rgba(212, 28, 28, 1)", valuecolor);
			}
		}
	
		// Logout, and exit.
		mddbPage.logout();	
		
	}

}