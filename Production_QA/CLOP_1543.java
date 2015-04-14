package Production_QA;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1543 {

	@Test
	public void CLOP_1543_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		String CHM = "Copyright Holder Missing";
		String CHLN = "Copyright Holder Last Name";
		String CHFN = "Copyright Holder First Name";
		String CHO = "Copyright Organization";
		Chrome_Driver.manage().window().maximize();

		// Navigate to the Math K-5: Count page.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(10000);

		// Navigate to a Concept Image page.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(3000);
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[2]/a")).click();
		Thread.sleep(3000);
	
		// Expand the Warnings list and validate the presence of a "Copyright Holder Missing" message.
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(3000);
		String warning = null;
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[@class='list-group-item']/div/h4"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			if(sValue.equalsIgnoreCase(CHM)) {
				Assert.assertEquals("Copyright Holder Missing", eachResult.getText());
				warning = "true";
				break;
	        }
		}
		Assert.assertEquals("true", warning);
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(2000);
		
		// Add a Copyright Holder > Given Name and Family Name value.
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Copyright Holder");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
		Chrome_Driver.findElement(By.xpath("//*[@name='copyrightholderfirstname']")).sendKeys("CLOP-1543-First");
		Chrome_Driver.findElement(By.xpath("//*[@name='copyrightholderlastname']")).sendKeys("CLOP-1543-Last");
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(5000);
		
		// Expand the Warning list and validate that the "Copyright Holder Missing" message has been removed.				
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(3000);	
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[@class='list-group-item']/div/h4"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase(CHM));
		}
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(3000);
		
		// Validate that the new First Name and Last Name value appears in the Metadata table, and remove both Metadata values. 
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		String Fname = null;
		String Lname = null;
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase(CHFN)){
				Assert.assertEquals("CLOP-1543-First", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(3000);
				Fname = "true";
				break;
	        }
		}
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase(CHLN)){
				Assert.assertEquals("CLOP-1543-Last", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(3000);
				Lname = "true";
				break;
	        }
		}
		Assert.assertEquals("true", Fname);
		Assert.assertEquals("true", Lname);
		
		// Expand the Warnings list and validate the presence of a "Copyright Holder Missing" message has been restored again.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(3000);
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[2]/a")).click();
		Thread.sleep(3000);
		warning = null;
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(3000);
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[@class='list-group-item']/div/h4"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			if(sValue.equalsIgnoreCase(CHM)) {
				Assert.assertEquals("Copyright Holder Missing", eachResult.getText());
				warning = "true";
				break;
	        }
		}
		Assert.assertEquals("true", warning);
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(2000);
		
		// Add a Copyright Holder -> Organization value.
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Copyright Holder");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
		Chrome_Driver.findElement(By.xpath("//*[@name='copyrightholderorganization']")).sendKeys("CLOP-1543-Organization");
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(5000);
		
		// Expand the Warning list and validate that the "Copyright Holder Missing" message has been removed.				
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(3000);	
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[@class='list-group-item']/div/h4"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase(CHM));
		}
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(3000);
		
		// Validate that the new Copyright Organization value appears in the Metadata table, then remove both Metadata value. 
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		String Orgname = null;
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase(CHO)){
				Assert.assertEquals("CLOP-1543-Organization", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(3000);
				Orgname = "true";
				break;
	        }
		}
		Assert.assertEquals("true", Orgname);
		
		// Expand the Warnings list and validate the presence of a "Copyright Holder Missing" message has been restored again.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(3000);
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[2]/a")).click();
		Thread.sleep(3000);
		warning = null;
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(3000);
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[@class='list-group-item']/div/h4"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			if(sValue.equalsIgnoreCase(CHM)) {
				Assert.assertEquals("Copyright Holder Missing", eachResult.getText());
				warning = "true";
				break;
	        }
		}
		Assert.assertEquals("true", warning);
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(2000);
		
		// Logout and exit
		mddbPage.logout();
		
	}

}