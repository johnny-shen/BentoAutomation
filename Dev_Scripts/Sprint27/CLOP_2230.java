package Sprint27;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_2230 {

	@Test
	public void CLOP_2230_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "src\\pages\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and validate the MOST RECENT PROJECTS header.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(5000);
		Assert.assertEquals("MOST RECENT PROJECTS more...", Chrome_Driver.findElement(By.xpath("//*//*[@id='clo-section']/div/div[3]/h3")).getText());
		
		// Navigate to the Content Browser Everything Project project.
		Chrome_Driver.findElement(By.xpath("//*[@href='#clo-section']")).click();
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@class='application-tool-links']/div/a/img"));	
		for  (WebElement eachResult : AllSearchResults) {
			String sValue = null;
			sValue = eachResult.getAttribute("src");	
			if (sValue.equalsIgnoreCase("https://metadatadev.clo.edmesh.com/img/home/nav-icon-content-browser.png")) {
				eachResult.click();
				Thread.sleep(5000);
				break;
			}
		}
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='project-list']/ul/li/div[2]/a"));	
		for  (WebElement eachResult : AllSearchResults) {
			String sValue = null;
			sValue = eachResult.getText();		
			if (sValue.equalsIgnoreCase("Everything Project")) {
				eachResult.click();
				Thread.sleep(5000);
				break;
			}
		}
		
		// Navigate back to the home page by clicking on the MHE logo and validate the new MOST RECENT PROJECTS list.
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(5000);
		Assert.assertEquals("Everything Project", Chrome_Driver.findElement(By.xpath("//*[@id='application-clo-projects']/ol/li[1]/a")).getText());

		// Click on the first item on the list and validate that it takes the user to the Summary page of that Project.
		Chrome_Driver.findElement(By.xpath("//*[@id='application-clo-projects']/ol/li[1]/a")).click();
		Thread.sleep(5000);
		Assert.assertEquals("Everything Project", Chrome_Driver.findElement(By.xpath("//*[@class='title']")).getText());
		
		// Navigate to Content Browser Show Pieces and then back to the home page and validate that the MY MOST RECENT CLOS list is updated.
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(5000);
		Chrome_Driver.findElement(By.xpath("//*[@href='#clo-section']")).click();
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@class='application-tool-links']/div/a/img"));	
		for  (WebElement eachResult : AllSearchResults) {
			String sValue = null;
			sValue = eachResult.getAttribute("src");	
			if (sValue.equalsIgnoreCase("https://metadatadev.clo.edmesh.com/img/home/nav-icon-content-browser.png")) {
				eachResult.click();
				Thread.sleep(5000);
				break;
			}
		} 
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='project-list']/ul/li/div[2]/a"));	
		for  (WebElement eachResult : AllSearchResults) {
			String sValue = null;
			sValue = eachResult.getText();		
			if (sValue.equalsIgnoreCase("Show Pieces")) {
				eachResult.click();
				Thread.sleep(5000);
				break;
			}
		}
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(5000);
		Assert.assertEquals("Show Pieces", Chrome_Driver.findElement(By.xpath("//*[@id='application-clo-projects']/ol/li[1]/a")).getText());
		Assert.assertEquals("Everything Project", Chrome_Driver.findElement(By.xpath("//*[@id='application-clo-projects']/ol/li[2]/a")).getText());
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@id='application-clo-projects']/ol/li[1]/img")).getAttribute("src").contains("https://partner.inkling.com/rawfiles/sn_2cb/img/toc_thumbs/star.png"));
		
		// Logout, and exit.
		mddbPage.logout();	
		
	}

}