package Production_QA;

import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.MetadataDashboardPage;

public class CLOP_1997 {

	@Test
	public void CLOP_1997_Test() throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(3000);
		
		// Prepare for the pop-up window when the various buttons in the Dashboard are pressed.
		String winHandleBefore = Chrome_Driver.getWindowHandle();
	
		// Validating that the Habitat and Academic Benchmarks tiles appear and clicking them takes the user to the correct popup page.
		String Habitattile = null;
		String AcademicBenchmarkstile = null;	
		WebDriver popup = null;
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@class='row dashboard-links text-center']/div/a"));	
		for  (WebElement eachResult : AllSearchResults) {
			String sValue = null;
			sValue = eachResult.getAttribute("href");	
			if(sValue.equalsIgnoreCase("https://habitat.inkling.com/")) {	
				Habitattile = "true";
				eachResult.click();
				Thread.sleep(5000);
				Set<String> listofWindows=Chrome_Driver.getWindowHandles();
				for  (String PopupPage : listofWindows) {
					if  (!winHandleBefore.equals(PopupPage)) {
						Chrome_Driver.switchTo().window(PopupPage);
						popup = Chrome_Driver.switchTo().window(PopupPage);
						Assert.assertEquals("Login - Inkling Habitat", popup.getTitle());
						Chrome_Driver.close();
			    	}
				Chrome_Driver.switchTo().window(winHandleBefore);
				}
			}
			if (sValue.equalsIgnoreCase("http://academicbenchmarks.com/")) {
				AcademicBenchmarkstile = "true";
				eachResult.click();
				Thread.sleep(5000);
				Set<String> listofWindows=Chrome_Driver.getWindowHandles();
				for  (String PopupPage : listofWindows) {
				    if  (!winHandleBefore.equals(PopupPage)) {
				        Chrome_Driver.switchTo().window(PopupPage);
				        popup = Chrome_Driver.switchTo().window(PopupPage);
						Assert.assertEquals("Academic Benchmarks - Standards and Metadata SolutionsAcademic Benchmarks | Academic Standards and Metadata Management Solutions", Chrome_Driver.getTitle());
				        Chrome_Driver.close();
				    }
				    Chrome_Driver.switchTo().window(winHandleBefore);
				}
			}   
		}
		Assert.assertEquals("true", Habitattile);
		Assert.assertEquals("true", AcademicBenchmarkstile);	
		
		// Return to the Metadata Dashboard page, logout, and exit.
		mddbPage.logout();	
		
	}
}