package Sprint21;

import java.util.Set;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1546 {

	@Test
	public void CLOP_1546_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		
		// Navigate to the CLO Parallax Design Tagging Tool page and validate the Summary link in the CLO TOC.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2cb/29c60bdeb1184aa5a23e4c090f77a358");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(10000);
		Assert.assertEquals("  Summary Page", Chrome_Driver.findElement(By.xpath("//*[@id='to_summary']")).getText());
	
		// Click on the Summary Page link, validate that it navigates to the Summary Page.
		Chrome_Driver.findElement(By.xpath("//*[@id='to_summary']")).click();
		Thread.sleep(7000);
		Assert.assertEquals("Content Learning Object | Metadata Dashboard", Chrome_Driver.getTitle());
		
		// Click on the Tagging Tool link and validate that it goes back to the Tagging Tool page, and logout when finished.
		String winHandleBefore = Chrome_Driver.getWindowHandle();
		Chrome_Driver.findElement(By.xpath("//*[@id='taggingtoollink']/img")).click();
		Thread.sleep(10000);
		Set<String> listofWindows=Chrome_Driver.getWindowHandles();
		for  (String PopupPage : listofWindows) {
		    if  (!winHandleBefore.equals(PopupPage)) {
		        Chrome_Driver.switchTo().window(PopupPage);
		        Assert.assertEquals("TaggingTool | Metadata Dashboard", Chrome_Driver.getTitle());
		        mddbPage.logout();
		    }
		}
		
	}

}