package Sprint21;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import pages.MetadataDashboardPage;

public class CLOP_1549 {

	@Test
	public void CLOP_1549_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		String CHM = "CLO copyright holder missing";
		String CECH = "Concept card copyright holder missing";
		String FirstPass = "CLO copyright holder first name is included";
		String LastPass = "CLO copyright holder last name is included";
		String YearPass = "CLO copyright year is included";
		String FirstConceptPass = "Concept card copyright holder first name is included";
		String OrgPass = "CLO copyright organization is included";
		String passed = "Requirement verified";
		Chrome_Driver.manage().window().maximize();

		// Navigate to the Math K-5: Count On page.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		
		// Click on the Summary Page link and validate the Warning publication entry for Copyright Holder for Overview and Concept.
		Chrome_Driver.findElement(By.xpath("//*[@id='to_summary']")).click();
		Thread.sleep(3000);		
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[2]/div/div[2]/a")).click();
		Thread.sleep(1000);
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='warnings-table']/tbody/tr")).size();
		String chaptercopyright = null;
		String conceptcopyright = null;
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='warnings-table']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase(CHM)){
				chaptercopyright = "true";
	        }
			if(sValue.equalsIgnoreCase(CECH)){
				conceptcopyright = "true";
	        }
		}
		Assert.assertEquals("true", chaptercopyright);
		Assert.assertEquals("true", conceptcopyright);

		// Navigate back to the Tagging Tool and add some Copyright Holder values for Overview and Concept.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);	
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Copyright Holder");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
		Chrome_Driver.findElement(By.xpath("//*[@name='copyrightholderfirstname']")).sendKeys("CLOP-1549-First");
		Chrome_Driver.findElement(By.xpath("//*[@name='copyrightholderlastname']")).sendKeys("CLOP-1549-Last");
		Select mydrpdwn = new  Select(Chrome_Driver.findElement(By.xpath("//*[@id='yearselector']")));
		mydrpdwn.selectByVisibleText("2011");
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(5000);
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(2000);	
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Copyright Holder");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
		Chrome_Driver.findElement(By.xpath("//*[@name='copyrightholderfirstname']")).sendKeys("CLOP-1549-First");
		Chrome_Driver.findElement(By.xpath("//*[@name='copyrightholderlastname']")).sendKeys("CLOP-1549-Last");
		mydrpdwn = new  Select(Chrome_Driver.findElement(By.xpath("//*[@id='yearselector']")));
		mydrpdwn.selectByVisibleText("2011");
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(5000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(2000);
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[@class='list-group-item']/div/h4"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase(CHM));
		}
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(2000);
		
		// Navigate back to the Summary page and validate that the Copyright Holder warning table for Overview and Concept entries has been removed and appear in the Passes table.
		Chrome_Driver.findElement(By.xpath("//*[@id='to_summary']")).click();
		Thread.sleep(3000);		
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[2]/div/div[2]/a")).click();
		Thread.sleep(1000);
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[3]/div/div[2]/a")).click();
		Thread.sleep(1000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='warnings-table']/tbody/tr")).size();
		String firstchapter = null;
		String lastchapter = null;
		String yearchapter = null;
		String firstconcept = null;
		
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='warnings-table']/tbody/tr/td[1]"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase(CHM));
			Assert.assertFalse(sValue.equalsIgnoreCase(CECH));
		}
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='verified-table']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[1]")).getText();						
			if(sValue.equalsIgnoreCase(FirstPass)){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				firstchapter = "true";
	        }
			if(sValue.equalsIgnoreCase(LastPass)){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				lastchapter = "true";
	        }
			if(sValue.equalsIgnoreCase(YearPass)){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				yearchapter = "true";
	        }
			if(sValue.equalsIgnoreCase(FirstConceptPass)){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				firstconcept = "true";
	        }
		}
		Assert.assertEquals("true", firstchapter);
		Assert.assertEquals("true", lastchapter);
		Assert.assertEquals("true", yearchapter);
		Assert.assertEquals("true", firstconcept);
		
		// Navigate back to the Tagging Tool and replace the First/Last Copyright Holder with an Organization.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Copyright Holder First Name")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Copyright Holder Last Name")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Copyright Year")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		String copyrightholdermessage = null;
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(1000);
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[@class='list-group-item']/div/h4"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			if(sValue.equalsIgnoreCase("Copyright Holder Missing")) {
				copyrightholdermessage = "true";
	        }
		}
		Assert.assertEquals("true", copyrightholdermessage);
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(1000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);	
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Copyright Holder");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
		Chrome_Driver.findElement(By.xpath("//*[@name='copyrightholderorganization']")).sendKeys("CLOP-1549-Organization");
		mydrpdwn = new  Select(Chrome_Driver.findElement(By.xpath("//*[@id='yearselector']")));
		mydrpdwn.selectByVisibleText("2011");
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(5000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(2000);
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[@class='list-group-item']/div/h4"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase("Copyright Holder Missing"));
		}
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(2000);
		
		// Navigate back to Summary Page, validate that the Warning message does not appear in the table, and Organization appears in the Passed table.
		Chrome_Driver.findElement(By.xpath("//*[@id='to_summary']")).click();
		Thread.sleep(3000);		

		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[2]/div/div[2]/a")).click();
		Thread.sleep(1000);
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[3]/div/div[2]/a")).click();
		Thread.sleep(1000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='warnings-table']/tbody/tr")).size();
		String orgchapter = null;
		
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='warnings-table']/tbody/tr/td[1]"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase(CHM));
			Assert.assertFalse(sValue.equalsIgnoreCase(CECH));
		}
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='verified-table']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[1]")).getText();						
			if(sValue.equalsIgnoreCase(OrgPass)){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				orgchapter = "true";
	        }
		}
		Assert.assertEquals("true", orgchapter);
		
		// Navigate back to Tagging Tool, remove all Copyright Holder entries, and logout and exit.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(5000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Copyright Organization")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(2000);
				break;
	        }
		}
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Copyright Year")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(2000);
				break;
	        }
		}
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(2000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Copyright Holder First Name")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Copyright Holder Last Name")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Copyright Year")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		
		mddbPage.logout();
	
	}
	
}