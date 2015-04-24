package Sprint27;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_2299 {

	@Test
	public void CLOP_2299_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "src\\pages\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and validate and record the number of Published, Updated, and Private are displayed.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(5000);
		
		// Click and validate the contents of the Published link.
		Chrome_Driver.findElement(By.xpath("//*[@href='/users/clo?status=Publish']")).click();
		Thread.sleep(3000);	
		Assert.assertEquals("CLO STATUS: PUBLISHED", Chrome_Driver.findElement(By.xpath("//*[@class='container']/h2")).getText());
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-item']/div/div[2]/p")).getText().contains("Current Status: published"));
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-item']/div/div[2]/p")).getText().contains("Last Updated"));
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-item']/div/div[2]/p")).getText().contains("Last Accessed"));
		Assert.assertEquals("https://metadatadev.clo.edmesh.com/img/habitat.png", Chrome_Driver.findElement(By.xpath("//*[@id='habitatlink']/img")).getAttribute("src"));
		Assert.assertEquals("https://metadatadev.clo.edmesh.com/img/tagging-tool.png", Chrome_Driver.findElement(By.xpath("//*[@id='taggingtoollink']/img")).getAttribute("src"));
		Assert.assertEquals("https://metadatadev.clo.edmesh.com/img/player.png", Chrome_Driver.findElement(By.xpath("//*[@id='playerlink']/img")).getAttribute("src"));
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-launch']/div/div[1]/div/a")).getAttribute("href").contains("https://habitat.inkling.com/project/"));
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-launch']/div/div[2]/div/a")).getAttribute("href").contains("https://metadatadev.clo.edmesh.com/taggingtool/chapter"));
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-launch']/div/div[3]/div/a")).getAttribute("href").contains("https://player.clo.edmesh.com/reader/"));
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(5000);
		
		// Click and validate the contents of the Updated link.
		Chrome_Driver.findElement(By.xpath("//*[@href='/users/clo?status=Update']")).click();
		Thread.sleep(3000);	
		Assert.assertEquals("CLO STATUS: UPDATED", Chrome_Driver.findElement(By.xpath("//*[@class='container']/h2")).getText());
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-item']/div/div[2]/p")).getText().contains("Current Status: updated"));
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-item']/div/div[2]/p")).getText().contains("Last Updated"));
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-item']/div/div[2]/p")).getText().contains("Last Accessed"));
		Assert.assertEquals("https://metadatadev.clo.edmesh.com/img/habitat.png", Chrome_Driver.findElement(By.xpath("//*[@id='habitatlink']/img")).getAttribute("src"));
		Assert.assertEquals("https://metadatadev.clo.edmesh.com/img/tagging-tool.png", Chrome_Driver.findElement(By.xpath("//*[@id='taggingtoollink']/img")).getAttribute("src"));
		Assert.assertEquals("https://metadatadev.clo.edmesh.com/img/player.png", Chrome_Driver.findElement(By.xpath("//*[@id='playerlink']/img")).getAttribute("src"));
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-launch']/div/div[1]/div/a")).getAttribute("href").contains("https://habitat.inkling.com/project/"));
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-launch']/div/div[2]/div/a")).getAttribute("href").contains("https://metadatadev.clo.edmesh.com/taggingtool/chapter"));
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-launch']/div/div[3]/div/a")).getAttribute("href").contains("https://player.clo.edmesh.com/reader/"));
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(5000);
		
		// Click and validate the contents of the Private link.
		Chrome_Driver.findElement(By.xpath("//*[@href='/users/clo?status=Private']")).click();
		Thread.sleep(3000);	
		Assert.assertEquals("CLO STATUS: PRIVATE", Chrome_Driver.findElement(By.xpath("//*[@class='container']/h2")).getText());
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-item']/div/div[2]/p")).getText().contains("Current Status: private"));
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-item']/div/div[2]/p")).getText().contains("Last Updated"));
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-item']/div/div[2]/p")).getText().contains("Last Accessed"));
		Assert.assertEquals("https://metadatadev.clo.edmesh.com/img/habitat.png", Chrome_Driver.findElement(By.xpath("//*[@id='habitatlink']/img")).getAttribute("src"));
		Assert.assertEquals("https://metadatadev.clo.edmesh.com/img/tagging-tool.png", Chrome_Driver.findElement(By.xpath("//*[@id='taggingtoollink']/img")).getAttribute("src"));
		Assert.assertEquals("https://metadatadev.clo.edmesh.com/img/player.png", Chrome_Driver.findElement(By.xpath("//*[@id='playerlink']/img")).getAttribute("src"));
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-launch']/div/div[1]/div/a")).getAttribute("href").contains("https://habitat.inkling.com/project/"));
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-launch']/div/div[2]/div/a")).getAttribute("href").contains("https://metadatadev.clo.edmesh.com/taggingtool/chapter"));
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-launch']/div/div[3]/div/a")).getAttribute("href").contains("https://player.clo.edmesh.com/reader/"));
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(5000);
		
		// Logout, and exit.
		mddbPage.logout();	
		
	}

}