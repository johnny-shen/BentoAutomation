package Production_QA;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MetadataDashboardPage;

public class CLOP_2187 {

	@Test
	public void CLOP_2187_Test() throws Exception {
		
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
		String testroledesc = "Test Role Description";
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
		Chrome_Driver.findElement(By.xpath("//*[@id='GroupName']")).sendKeys(testrole);
		Chrome_Driver.findElement(By.xpath("//*[@id='GroupDescription']")).sendKeys(testroledesc);
		Chrome_Driver.findElement(By.xpath("//*[@value='Submit']")).click();
		
		// Validate the existence of the test role
		String testroleexist = null;
		String testroledescexist = null;
		int rowCount=Chrome_Driver.findElements(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String value1 = Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[2]")).getText();
			value1 = value1.trim();
			String value2 = Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[3]")).getText();
			value2 = value2.trim();
		    if (value1.equals(testrole)) {       
		    	testroleexist = "true";
		    }
		    if (value2.equals(testroledesc)) {       
		    	testroledescexist = "true";
		    }
		}
		Assert.assertEquals("true", testroleexist);
		Assert.assertEquals("true", testroledescexist);
			
		// Navigate to the home page and validate that the Alert message is displayed.
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(3000);	
		Assert.assertEquals("The role has been saved.", Chrome_Driver.findElement(By.xpath("//*[@id='flashMessage']")).getText());
		
		// Navigate back to the List Role page and delete the test role
		Chrome_Driver.findElement(By.xpath("//*[@id='nav-icons']/ul/li[2]/a/img")).click();
		Chrome_Driver.findElement(By.xpath("//*[@href='/users']")).click();
		Thread.sleep(3000);	
		Chrome_Driver.findElement(By.xpath("//*[@class='btn btn-sm btn-primary dropdown-toggle']")).click();
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@class='dropdown-menu pull-right']/li/a"));
		for  (WebElement eachResult : AllSearchResults) {
		    String match = "List Roles";
		    String value = eachResult.getText();
			value = value.trim();
		    if (value.equals(match)) {       
		    	eachResult.click();
		    	break ;
		    }
		}
		Thread.sleep(2000);	
		rowCount=Chrome_Driver.findElements(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String value = null;
			value = Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[2]")).getText();
			value = value.trim();
		    if (value.equals(testrole)) {       
		    	Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[6]/a[3]")).click();
		    	break ;
		    }
		}
		WebDriverWait wait = new  WebDriverWait(Chrome_Driver, 2);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = Chrome_Driver.switchTo().alert();
		alert.accept();
		Thread.sleep(3000);	
		
		// Logout and exit.
		mddbPage.logout();	
		
		
	}

}
