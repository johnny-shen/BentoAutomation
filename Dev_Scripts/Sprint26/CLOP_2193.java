package Sprint26;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_2193 {

	@Test
	public void CLOP_2193_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login as an Admin.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("admin", "abc123");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(3000);
		
		// Navigate to the List Roles page and validate the the listed roles are displayed. 
		String adexist = null;
		String paexist = null;
		String uuexist = null;
		String suexist = null;
		String pmexist = null;
		String ad = "Admin";
		String pa = "Program Author";
		String uu = "Unknown Users";
		String su = "Super Users";
		String pm = "Program Manager";
		Chrome_Driver.findElement(By.xpath("//*[@id='nav-icons']/ul/li[2]/a/img")).click();
		Chrome_Driver.findElement(By.xpath("//*[@href='/users']")).click();
		Thread.sleep(3000);	
		Chrome_Driver.findElement(By.xpath("//*[@class='btn btn-sm btn-primary dropdown-toggle']")).click();
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@class='dropdown-menu pull-right']/li/a"));
		for  (WebElement eachResult : AllSearchResults) {
		    String match = "List Roles";
		    String value = eachResult.getText();
		    Assert.assertEquals(match, value);
		    eachResult.click();
		}
		Thread.sleep(2000);	
		
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@class='table table-striped']/tbody/tr/td[2]"));
		for  (WebElement eachResult : AllSearchResults) {
		    String value = eachResult.getText();
		    value = value.trim();
		    if (value.equals(ad)) {       
		    	adexist = "true";
		    }
		    if (value.equals(pa)) {       
		    	paexist = "true";
		    }
		    if (value.equals(uu)) {       
		    	uuexist = "true";
		    }
		    if (value.equals(su)) {       
		    	suexist = "true";
		    }
		    if (value.equals(pm)) {       
		    	pmexist = "true";
		    }
		}
		
		Assert.assertEquals("true", adexist);
		Assert.assertEquals("true", paexist);
		Assert.assertEquals("true", uuexist);
		Assert.assertEquals("true", suexist);
		Assert.assertEquals("true", pmexist);
		
		// Logout and exit.
		mddbPage.logout();			
		
	}

}