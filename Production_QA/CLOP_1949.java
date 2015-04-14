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

public class CLOP_1949 {

	@Test
	public void CLOP_1949_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login as an Super User.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("daviracvant", "abc123");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(3000);
		
		// Extract the Settings page and validate the List Group table values.
		Chrome_Driver.findElement(By.xpath("//*[@id='nav-icons']/ul/li[2]/a/img")).click();
		Chrome_Driver.findElement(By.xpath("//*[@href='/users']")).click();
		Thread.sleep(3000);	
		Chrome_Driver.findElement(By.xpath("//*[@class='btn btn-sm btn-primary dropdown-toggle']")).click();
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@class='dropdown-menu pull-right']/li/a"));
		for  (WebElement eachResult : AllSearchResults) {
		    String match = "List Roles";
		    String value = eachResult.getText();
		    if (value.equals(match)) {       
		        eachResult.click();
		        break ;
		    }
		}
		Thread.sleep(2000);	
		int rowCount=Chrome_Driver.findElements(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String User = null;
			String Actions = null;
			User = Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[3]")).getText();
			Actions = Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[6]")).getText();
			if (!User.equals("Super Users")) {
				Assert.assertEquals("View | Edit | Delete", Actions);
			}
			if (User.equals("Super Users")) {
				Assert.assertEquals("View | Edit", Actions);
			}
		}
		
		// Add a new Group, validate it, edit it, and remove it.
		Chrome_Driver.findElement(By.xpath("//*[@class='btn btn-sm btn-primary pull-right group-actions']")).click();
		Chrome_Driver.findElement(By.xpath("//*[@id='GroupName']")).sendKeys("CLOP-1949 Test");
		Chrome_Driver.findElement(By.xpath("//*[@value='Submit']")).click();
		Thread.sleep(3000);
		String group1 = null;
		String group2 = null;
		rowCount=Chrome_Driver.findElements(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[2]")).getText();	
			sValue = sValue.trim();
			if(sValue.equalsIgnoreCase("CLOP-1949 Test")){
				group1 = "true";
				Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[6]/a[2]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		Chrome_Driver.findElement(By.xpath("//*[@id='GroupName']")).clear();
		Chrome_Driver.findElement(By.xpath("//*[@id='GroupName']")).sendKeys("New CLOP-1949 Test");
		Chrome_Driver.findElement(By.xpath("//*[@value='Save Changes']")).click();
		Thread.sleep(2000);
		rowCount=Chrome_Driver.findElements(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[2]")).getText();	
			sValue = sValue.trim();
			if(sValue.equalsIgnoreCase("New CLOP-1949 Test")){
				group2 = "true";
				Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[6]/a[3]")).click();
				break;
	        }
		}
		WebDriverWait wait = new WebDriverWait(Chrome_Driver, 2);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = Chrome_Driver.switchTo().alert();
		alert.accept();
		Thread.sleep(3000);	
		Assert.assertEquals("true", group1);
		Assert.assertEquals("true", group2);
		
		// Extract the Settings page and validate the List Users table values.
		Chrome_Driver.findElement(By.xpath("//*[@class='btn btn-sm btn-primary dropdown-toggle']")).click();
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@class='dropdown-menu pull-right']/li/a"));
		for  (WebElement eachResult : AllSearchResults) {
		    String match = "List Users";
		    String value = eachResult.getText();
		    if (value.equals(match)) {       
		        eachResult.click();
		        break ;
		    }
		}
		Thread.sleep(2000);
		rowCount=Chrome_Driver.findElements(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String User = null;
			String Actions = null;
			User = Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[3]")).getText();
			Actions = Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/tbody/tr["+i+"]/td[6]")).getText();
			if (!User.equals("Super Users")) {
				Assert.assertEquals("View | Edit | Delete", Actions);
			}
			if (User.equals("Super Users")) {
				Assert.assertEquals("View | Edit", Actions);
			}
		}
		Chrome_Driver.findElement(By.xpath("//*[@href='/users/add']")).click();
		Thread.sleep(2000);	
		Assert.assertEquals("Add User\nList Users\nRole\nToggle Dropdown", Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[1]/div/h2")).getText());
		Assert.assertEquals("Submit", Chrome_Driver.findElement(By.xpath("//*[@value='Submit']")).getAttribute("value"));
		
		// Navigate to the Learning Statements page and validate that certain features exist as an Admin.
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(2000);	
		Chrome_Driver.findElement(By.xpath("//*[@href='learning-objectives/']")).click();
		Thread.sleep(3000);
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/ul/li[2]/a")).click();
		Thread.sleep(3000);	
		Chrome_Driver.findElement(By.xpath("//*[@id='show-add-button']")).click();
		Thread.sleep(2000);	
		Assert.assertEquals("New Learning Statement", Chrome_Driver.findElement(By.xpath("//*[@id='add-learning-statement']/div/div/div/h4")).getText());
		Thread.sleep(2000);	
		Chrome_Driver.findElement(By.xpath("//*[@id='add-learning-statement']/div/div/form/div[2]/button[1]")).click();
		Thread.sleep(2000);	
		Chrome_Driver.findElement(By.xpath("//*[@id='statement-search-value']")).sendKeys("SG test learning statement - 1");
		Thread.sleep(2000);	
		Chrome_Driver.findElement(By.xpath("//*[@id='statement-search-button']")).click();
		Thread.sleep(2000);	
		Assert.assertEquals("edit", Chrome_Driver.findElement(By.xpath("//*[@id='search-results-learning-statements']/li/div/a[1]")).getAttribute("innerText"));
		Assert.assertEquals("delete", Chrome_Driver.findElement(By.xpath("//*[@id='search-results-learning-statements']/li/div/a[2]")).getAttribute("innerText"));
		Chrome_Driver.findElement(By.xpath("//*[@id='pendingapproval']/a")).click();
		Thread.sleep(3000);	
		Assert.assertEquals("Reject", Chrome_Driver.findElement(By.xpath("//*[@id='approval-content']/div[3]/button[1]")).getText());
		Assert.assertEquals("Accept", Chrome_Driver.findElement(By.xpath("//*[@id='approval-content']/div[3]/button[2]")).getText());
		
		// Navigate to a Summary Page and validate that the Publish selection is available and is not disabled.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/clo/sn_2cb/5c4b38804700499c8638916ebf4adeca/");
		Thread.sleep(5000);
		Assert.assertEquals(null, Chrome_Driver.findElement(By.xpath("//*[@id='action']/option[1]")).getAttribute("disabled"));
			
		// Logout and exit.
		mddbPage.logout();	
		
	}

}