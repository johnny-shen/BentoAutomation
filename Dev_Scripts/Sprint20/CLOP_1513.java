package Sprint20;

import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.MetadataDashboardPage;

public class CLOP_1513 {

	@Test
	public void CLOP_1513_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "src\\pages\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
		
		// Launch the Metadata Dashboard and Login.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(3000);
		
		// Validating that the Engrade tile appears in the Metadata Dashboard and when clicked it takes the user to the Engrade Login page.
		String Engrade = null;
		String winHandleBefore = Chrome_Driver.getWindowHandle();
		
		// Expand the main drop down bar and validate that the Engrade selection appears..
		Chrome_Driver.findElement(By.xpath("//*[@id='hamburger-nav']")).click();
		Thread.sleep(1000);
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='hamburger-menu']/ul/li/a/p"));		
		for  (WebElement eachResult : AllSearchResults) {
			String sValue = null;
			sValue = eachResult.getText();			
			if (sValue.equalsIgnoreCase("Engrade")) {
				Engrade = "true";
				eachResult.click();
				Thread.sleep(5000);
				break;
			}		
		}
		Assert.assertEquals("true", Engrade);

		// Quick validation of the contents of the Engrade page.
		Set<String> listofWindows=Chrome_Driver.getWindowHandles();
		for  (String PopupPurchase : listofWindows) {
		    if  (!winHandleBefore.equals(PopupPurchase)) {
		        Chrome_Driver.switchTo().window(PopupPurchase);
		        Assert.assertEquals("Engrade - Login", Chrome_Driver.getTitle());
		    }
		}
		Chrome_Driver.close();
		Chrome_Driver.switchTo().window(winHandleBefore);
		
		// Logout, and exit.
		mddbPage.logout();
	
	}

}