package Production_QA;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_2185 {

	@Test
	public void CLOP_2185_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("daviracvant", "abc123");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(3000);
		
		// Navigate to the Settings page and add a New Role
		String testrole = "Test Role";
		Chrome_Driver.findElement(By.xpath("//*[@id='nav-icons']/ul/li[2]/a/img")).click();
		Chrome_Driver.findElement(By.xpath("//*[@href='/users']")).click();
		Thread.sleep(3000);	
		Chrome_Driver.findElement(By.xpath("//*[@class='btn btn-sm btn-primary dropdown-toggle']")).click();
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@class='dropdown-menu pull-right']/li/a"));
		for  (WebElement eachResult : AllSearchResults) {
		    String match = "New Role";
		    String value = eachResult.getText();
			value = value.trim();
		    if (value.equals(match)) {       
		    	eachResult.click();
		    	break ;
		    }
		}
		Thread.sleep(2000);	
		
		// Enter a new Test Role.
		Chrome_Driver.findElement(By.xpath("//*[@id='GroupName']")).sendKeys(testrole);
		Chrome_Driver.findElement(By.xpath("//*[@value='Submit']")).click();
		
		// Validate the existence of the test role
		String testroleexist = null;
		int rowCount=Chrome_Driver.findElements(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String value = null;
			value = Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[2]")).getText();
			value = value.trim();
		    if (value.equals(testrole)) {       
		    	testroleexist = "true";
		    	Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[6]/a[2]")).click();
		    	Thread.sleep(3000);	
		    	break ;
		    }
		}
		Assert.assertEquals("true", testroleexist);
		
		// Edit the Test Role account.
		Chrome_Driver.findElement(By.xpath("//*[@id='GroupName']")).clear();
		Chrome_Driver.findElement(By.xpath("//*[@id='GroupName']")).sendKeys("Test Role1");
		Chrome_Driver.findElement(By.xpath("//*[@value='Save Changes']")).click();
		
		// Validate the existence of the edited test role then delete the account.
		testroleexist = null;
		rowCount=Chrome_Driver.findElements(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String value = null;
			value = Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[2]")).getText();
			value = value.trim();
		    if (value.equals("Test Role1")) {       
		    	testroleexist = "true";
		    	Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[6]/a[3]")).click();
		    	break ;
		    }
		}
		WebDriverWait wait = new  WebDriverWait(Chrome_Driver, 2);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = Chrome_Driver.switchTo().alert();
		alert.accept();
		Assert.assertEquals("true", testroleexist);		
		
		// Validate that all roles can be viewed, edited, and deleted.
    	Thread.sleep(3000);	
		AllSearchResults=Chrome_Driver.findElements(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr/td[6]"));
		for  (WebElement eachResult : AllSearchResults) {
		    String value = eachResult.getText();
		    Assert.assertEquals("View | Edit | Delete", value);
		}
		
		// Logout and exit.
		mddbPage.logout();	
		
	}

}