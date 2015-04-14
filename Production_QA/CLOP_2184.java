package Production_QA;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_2184 {

	@Test
	public void CLOP_2184_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login as an Admin.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("admin", "abc123");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(3000);
		
		// Validate that only List Roles appear in the Role drop-down list, navigate there, and under the Roles table only View appears in the action. 
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
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@class='actions']/a"));
		for  (WebElement eachResult : AllSearchResults) {
		    String match = "View";
		    String value = eachResult.getText();
		    Assert.assertEquals(match, value);
		}
		
		// Navigate to the Role: Program Manager page and validate that in the Group Properties section the Edit and Delete buttons have been removed and Id equals Role Id.
		int rowCount=Chrome_Driver.findElements(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String match = "Program Manager";
			String value = null;
			value = Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[2]")).getText();
			value = value.trim();
		    if (value.equals(match)) {       
		    	Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[6]/a")).click();
		    	Thread.sleep(3000);	
		    	break ;
		    }
		}
		List<WebElement>hiddentoc = Chrome_Driver.findElements(By.xpath("//*[@class='btn btn-sm btn-default']"));
		assertTrue(hiddentoc.isEmpty());
		hiddentoc = Chrome_Driver.findElements(By.xpath("//*[@class='btn btn-sm btn-danger']"));
		assertTrue(hiddentoc.isEmpty());
		String id = Chrome_Driver.findElement(By.xpath("//*[@class='dl-horizontal']/dd[1]")).getText();		
		id = id.trim();
		rowCount=Chrome_Driver.findElements(By.xpath("html/body/div[1]/div[2]/div[3]/div/div/table/tbody/tr")).size();
		for (int i=2;i<=rowCount;i++) {
			String value = null;
			value = Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[3]/div/div/table/tbody/tr["+i+"]/td[3]")).getText();
			value = value.trim();
			Assert.assertEquals(id, value);			
		}
			
		// Click on Edit for the first user role and validate the permissions and verify that Super Users is disabled in the selections.
		Chrome_Driver.findElement(By.xpath("//*[@class='actions']/a[2]")).click();
		Thread.sleep(1000);	
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='UserGroupId']/option"));
		for  (WebElement eachResult : AllSearchResults) {
		    String match = "Super Users";
		    String value = eachResult.getText();
		    if (value.equals(match)) {       
		    	Assert.assertEquals("true", eachResult.getAttribute("disabled"));	
		    }
		}
		
		// Navigate back to the List Roles page
		Chrome_Driver.findElement(By.xpath("//*[@class='btn-group pull-right group-actions']/button")).click();
		Chrome_Driver.findElement(By.xpath("//*[@href='/groups']")).click();
		Thread.sleep(2000);	
		
		// Navigate to the Role: Super Users page and validate that only View is seen in the Actions column.
		rowCount=Chrome_Driver.findElements(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String match = "Super Users";
			String value = null;
			value = Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[2]")).getText();
			value = value.trim();
		    if (value.equals(match)) {       
		    	Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[6]/a")).click();
		    	Thread.sleep(3000);	
		    	break ;
		    }
		}
		rowCount=Chrome_Driver.findElements(By.xpath("html/body/div[1]/div[2]/div[3]/div/div/table/tbody/tr")).size();
		String match = "View";
		for (int i=2;i<=rowCount;i++) {
			String value = null;
			value = Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[3]/div/div/table/tbody/tr["+i+"]/td[6]")).getText();
			value = value.trim();
			Assert.assertEquals(match, value);			
		}
		
		// Logout and exit.
		mddbPage.logout();			
		
	}

}