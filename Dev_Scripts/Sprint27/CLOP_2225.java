package Sprint27;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.MetadataDashboardPage;

public class CLOP_2225 {

	@Test
	public void CLOP_2225_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "src\\pages\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and validate the MY MOST RECENT CLOS header.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(5000);
		Assert.assertEquals("MY MOST RECENT CLOS more...", Chrome_Driver.findElement(By.xpath("//*[@class='application-report-header']")).getText());
		
		// Navigate to the Math Intervention Sandbox page.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2cb/3c895e745ed6455bbc51accde0bc58dd");
		Thread.sleep(10000);
		
		// Navigate back to the home page by clicking on the MHE logo and validate the new MY MOST RECENT CLOS list.
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(5000);
		Assert.assertEquals("Math Intervention Sandbox", Chrome_Driver.findElement(By.xpath("//*[@id='application-clo-container']/ol/li[1]/a")).getText());
		
		// Click on the first item on the list and validate that it takes the user to the Summary page of that CLO.
		Chrome_Driver.findElement(By.xpath("//*[@id='application-clo-container']/ol/li[1]/a")).click();
		Thread.sleep(5000);
		Assert.assertEquals("Math Intervention Sandbox", Chrome_Driver.findElement(By.xpath("//*[@id='title-holder']/h2")).getText());
		Assert.assertEquals("Metadata", Chrome_Driver.findElement(By.xpath("//*[@id='metadata-header']")).getText());
		
		// Navigate to another CLO and then back to the home page and validate that the MY MOST RECENT CLOS list is updated.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2cb/ccdd91014b3c4b2a845d3529acfb4ed3");
		Thread.sleep(10000);
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(5000);
		Assert.assertEquals("Specific Heat", Chrome_Driver.findElement(By.xpath("//*[@id='application-clo-container']/ol/li[1]/a")).getText());
		Assert.assertEquals("Math Intervention Sandbox", Chrome_Driver.findElement(By.xpath("//*[@id='application-clo-container']/ol/li[2]/a")).getText());
		Assert.assertEquals("https://metadatadev.clo.edmesh.com/img/content-browser/cloitem.jpg", Chrome_Driver.findElement(By.xpath("//*[@id='application-clo-container']/ol/li[1]/img")).getAttribute("src"));
		
		// Logout, and exit.
		mddbPage.logout();	
		
	}

}