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

public class CLOP_2190 {

	@Test
	public void CLOP_2190_Test() throws Exception {
		
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
		
		// Navigate to the Settings page and validate the Role column header.
		Chrome_Driver.findElement(By.xpath("//*[@id='nav-icons']/ul/li[2]/a/img")).click();
		Chrome_Driver.findElement(By.xpath("//*[@href='/users']")).click();
		Thread.sleep(3000);	
		Assert.assertEquals("Role", Chrome_Driver.findElement(By.xpath("//*[@href='/users/index/sort:group_id/direction:asc']")).getText());
		
		// Navigate to the Roles page and view the Admin Role Properties page.
		Chrome_Driver.findElement(By.xpath("//*[@class='btn btn-sm btn-primary dropdown-toggle']")).click();
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@class='dropdown-menu pull-right']/li/a"));
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
		int rowCount=Chrome_Driver.findElements(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String match = "Admin";
			String value = null;
			value = Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[2]")).getText();
			value = value.trim();
		    if (value.equals(match)) {       
		    	Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[6]/a[1]")).click();
		    	Thread.sleep(3000);	
		    	break ;
		    }
		}
		Assert.assertEquals("Role Properties", Chrome_Driver.findElement(By.xpath("//*[@class='nomargin']")).getText());
		Assert.assertEquals("Role Id", Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[3]/div/div/table/tbody/tr[1]/th[3]")).getText());
		
		// Add a new role for deletion.
		String testrole = "Test Role";
		Chrome_Driver.findElement(By.xpath("//*[@class='btn-group pull-right group-actions']/button")).click();
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@href='/groups/add']"));
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
		Chrome_Driver.findElement(By.xpath("//*[@value='Submit']")).click();
		Thread.sleep(2000);	
		
		// Delete the role.
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
		
		// Navigate to the home page and validate that the Alert message is displayed.
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(3000);	
		Assert.assertEquals("The role has been deleted.", Chrome_Driver.findElement(By.xpath("//*[@id='flashMessage']")).getText());
		
		// Logout and exit.
		mddbPage.logout();	
		
	}

}