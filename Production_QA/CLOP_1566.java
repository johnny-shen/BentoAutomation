package Production_QA;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import pages.MetadataDashboardPage;

public class CLOP_1566 {

	@Test
	public void CLOP_1566_Test() throws Exception {

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
		String adaptationrightswarning = "Adaptation Rights Missing";
		String cloadaptationrights = "CLO adaptation rights missing";
		String cloadaptationrightsmsg = "The CLO needs an adaptation rights.Resolve";
		String conceptaptationrights = "Concept card adaptation rights missing";
		String conceptaptationrightsmsg = "The Concept card needs an adaptation rights.Resolve";
		String cloadaptationrightsval = "CLO adaptation rights is included";
		String conceptadaptationrightsval = "Concept card adaptation rights is included";
		String passed = "Requirement verified";

		// Navigate to the Math K-5: Count On page that does not have a Learning Objective
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(2000);
		
		// Validate that there is a Warning message about Adaptation Rights Missing.
		String adaptationrightsmessage = null;
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[@class='list-group-item']/div/h4"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			if(sValue.equalsIgnoreCase(adaptationrightswarning)) {
				adaptationrightsmessage = "true";
	        }
		}
		Assert.assertEquals("true", adaptationrightsmessage);
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(2000);
		
		// Click on the Summary Page link and validate the Warning publication entry for Adaptation Rights for Overview and Concept.
		Chrome_Driver.findElement(By.xpath("//*[@id='to_summary']")).click();
		Thread.sleep(3000);		
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[2]/div/div[2]/a")).click();
		Thread.sleep(1000);
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='warnings-table']/tbody/tr")).size();
		String chapterrights = null;
		String conceptrights = null;
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='warnings-table']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase(cloadaptationrights)){
				Assert.assertEquals(cloadaptationrightsmsg, Chrome_Driver.findElement(By.xpath("//*[@id='warnings-table']/tbody/tr["+i+"]/td[2]")).getText());
				chapterrights = "true";
	        }
			if(sValue.equalsIgnoreCase(conceptaptationrights)){
				Assert.assertEquals(conceptaptationrightsmsg, Chrome_Driver.findElement(By.xpath("//*[@id='warnings-table']/tbody/tr["+i+"]/td[2]")).getText());
				conceptrights = "true";
	        }
		}
		Assert.assertEquals("true", chapterrights);
		Assert.assertEquals("true", conceptrights);

		// Navigate back to the Tagging Tool and add an Adaptation Rights in Overview and Concept and validate that Warning message has been removed.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Adaptation Rights");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		Select mydrpdwn = new  Select(Chrome_Driver.findElement(By.xpath("//*[@name='boolean']")));
		mydrpdwn.selectByVisibleText("Yes");
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
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Adaptation Rights");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		mydrpdwn = new  Select(Chrome_Driver.findElement(By.xpath("//*[@name='boolean']")));
		mydrpdwn.selectByVisibleText("Yes");
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
			Assert.assertFalse(sValue.equalsIgnoreCase(adaptationrightswarning));
		}
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(2000);
		
		// Navigate back to the Summary page and validate that the Adaptation Rights warning table for Overview and Concept entries has been removed and appear in the Passes table.
		Chrome_Driver.findElement(By.xpath("//*[@id='to_summary']")).click();
		Thread.sleep(3000);		
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[2]/div/div[2]/a")).click();
		Thread.sleep(1000);
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[3]/div/div[2]/a")).click();
		Thread.sleep(1000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='warnings-table']/tbody/tr")).size();
		chapterrights = null;
		conceptrights = null;
		
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='warnings-table']/tbody/tr/td[1]"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase(cloadaptationrights));
			Assert.assertFalse(sValue.equalsIgnoreCase(conceptaptationrights));
		}
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='verified-table']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[1]")).getText();						
			if(sValue.equalsIgnoreCase(cloadaptationrightsval)){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				chapterrights = "true";
	        }
			if(sValue.equalsIgnoreCase(conceptadaptationrightsval)){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				conceptrights = "true";
	        }
		}
		Assert.assertEquals("true", chapterrights);
		Assert.assertEquals("true", conceptrights);
		
		// Navigate back to the Tagging Tool Overview, remove both Adaptation Rights, and logout and exit.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Adaptation Rights")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(2000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Adaptation Rights")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}

		mddbPage.logout();	
	
	}
	
}