package Sprint27;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_2302 {

	@Test
	public void CLOP_2302_Test() throws Exception {
		
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
				
		// Click and validate the contents of the MY MOST RECENT CLOS more... link.
		Chrome_Driver.findElement(By.xpath("//*[@href='/users/clo?status=recent']")).click();
		Thread.sleep(3000);	
		Assert.assertEquals("MY MOST RECENT CLO", Chrome_Driver.findElement(By.xpath("//*[@class='container']/h2")).getText());
		List<WebElement> clolist=Chrome_Driver.findElements(By.xpath("//*[@class='application-clo-listing']/div"));
		int clolistsize = clolist.size();          
		if (clolistsize >= 20) {
			Assert.assertEquals(20, clolistsize);
		}
		List<WebElement> clos=Chrome_Driver.findElements(By.xpath("//*[@class='application-clo-listing']/div/div[2]/p"));
		for  (WebElement eachResult : clos) {
		    String value = eachResult.getText();
		    Assert.assertTrue(value.contains("Current Status:"));
			Assert.assertTrue(value.contains("Last Accessed"));
		}
		List<WebElement> habitatlink=Chrome_Driver.findElements(By.xpath("//*[@class='application-clo-launch']/div/div[1]/div/a"));
		for  (WebElement eachResult : habitatlink) {
		    String value = eachResult.getAttribute("href");
		    Assert.assertTrue(value.contains("https://habitat.inkling.com/project/"));
		}
		List<WebElement> ttlink=Chrome_Driver.findElements(By.xpath("//*[@class='application-clo-launch']/div/div[2]/div/a"));
		for  (WebElement eachResult : ttlink) {
		    String value = eachResult.getAttribute("href");
			Assert.assertTrue(value.contains("https://metadatadev.clo.edmesh.com/taggingtool/chapter"));
		}
	
		List<WebElement> playerlink=Chrome_Driver.findElements(By.xpath("//*[@class='application-clo-launch']/div/div[3]/div/a"));
		for  (WebElement eachResult : playerlink) {
		    String value = eachResult.getAttribute("href");
			Assert.assertTrue(value.contains("https://player.clo.edmesh.com/reader/"));
		}
	
		// Click and validate the contents of the List view.
		Chrome_Driver.findElement(By.xpath("//*[@data-viewtype='list']")).click();
		Thread.sleep(3000);	
		Assert.assertEquals("Title", Chrome_Driver.findElement(By.xpath("//*[@class='table']/thead/tr/th[1]")).getText());
		Assert.assertEquals("Current Status", Chrome_Driver.findElement(By.xpath("//*[@class='table']/thead/tr/th[2]")).getText());
		Assert.assertEquals("Last Updated", Chrome_Driver.findElement(By.xpath("//*[@class='table']/thead/tr/th[3]")).getText());
		Assert.assertEquals("Last Accessed", Chrome_Driver.findElement(By.xpath("//*[@class='table']/thead/tr/th[4]")).getText());
		Assert.assertEquals("Launch In", Chrome_Driver.findElement(By.xpath("//*[@class='table']/thead/tr/th[5]")).getText());
		
		habitatlink=Chrome_Driver.findElements(By.xpath("//*[@role='group']/a[1]"));
		for  (WebElement eachResult : habitatlink) {
		    String value = eachResult.getAttribute("href");
		    Assert.assertTrue(value.contains("https://habitat.inkling.com/project/"));
		}
		ttlink=Chrome_Driver.findElements(By.xpath("//*[@role='group']/a[2]"));
		for  (WebElement eachResult : ttlink) {
		    String value = eachResult.getAttribute("href");
			Assert.assertTrue(value.contains("https://metadatadev.clo.edmesh.com/taggingtool/chapter"));
		}
	
		playerlink=Chrome_Driver.findElements(By.xpath("//*[@role='group']/a[3]"));
		for  (WebElement eachResult : playerlink) {
		    String value = eachResult.getAttribute("href");
			Assert.assertTrue(value.contains("https://player.clo.edmesh.com/reader/"));
		}
		
		// Logout, and exit.
		mddbPage.logout();	
		
	}

}