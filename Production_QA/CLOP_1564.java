package Production_QA;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1564 {

	@Test
	public void CLOP_1564_Test() throws Exception {

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
		String minimumwarning = "Grade Range Minimum Missing";
		String maximumwarning = "Grade Range Maximum Missing";
		String clominimumwarning = "CLO minimum grade level missing";
		String clowmaximumwarning = "CLO maximum grade level missing";
		String clominimumwarningmsg = "The CLO needs a minimum grade level.Resolve";
		String clowmaximumwarningmsg = "The CLO needs a maximum grade level.Resolve";
		String passed = "Requirement verified";

		// Navigate to the Math K-5: Count On page that does not have a Learning Objective
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(2000);
		
		// Validate that there is a Warning message about a Missing Minimum and Maximum Grade Range.
		String minimummessage = null;
		String maximummessage = null;
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[@class='list-group-item']/div/h4"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			if(sValue.equalsIgnoreCase(minimumwarning)) {
				minimummessage = "true";
	        }
			if(sValue.equalsIgnoreCase(maximumwarning)) {
				maximummessage = "true";
	        }
		}
		Assert.assertEquals("true", minimummessage);
		Assert.assertEquals("true", maximummessage);
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(2000);
		
		// Click on the Summary Page link and validate the Warning publication entry for Grade Range.
		Chrome_Driver.findElement(By.xpath("//*[@id='to_summary']")).click();
		Thread.sleep(3000);		
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[2]/div/div[2]/a")).click();
		Thread.sleep(1000);
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='warnings-table']/tbody/tr")).size();
		String chapterminw = null;
		String chaptermaxw = null;
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='warnings-table']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase(clominimumwarning)){
				Assert.assertEquals(clominimumwarningmsg, Chrome_Driver.findElement(By.xpath("//*[@id='warnings-table']/tbody/tr["+i+"]/td[2]")).getText());
				chapterminw = "true";
	        }
			if(sValue.equalsIgnoreCase(clowmaximumwarning)){
				Assert.assertEquals(clowmaximumwarningmsg, Chrome_Driver.findElement(By.xpath("//*[@id='warnings-table']/tbody/tr["+i+"]/td[2]")).getText());
				chaptermaxw = "true";
	        }
		}
		Assert.assertEquals("true", chapterminw);
		Assert.assertEquals("true", chaptermaxw);

		// Navigate back to the Tagging Tool and add a Learning Objective and validate that Warning message has been removed.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(8000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);		
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Learning Objective");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);	
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_learningobjectiveselector']/a/span[2]/b")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("SG Test LO with blooms and webbs");
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(2000);
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[@class='list-group-item']/div/h4"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase(minimumwarning));
			Assert.assertFalse(sValue.equalsIgnoreCase(maximumwarning));
		}
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(2000);
		
		// Navigate back to the Summary page and validate that the Grade Range Warning table entries has been removed and appear in the Passes table.
		Chrome_Driver.findElement(By.xpath("//*[@id='to_summary']")).click();
		Thread.sleep(3000);		
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[2]/div/div[2]/a")).click();
		Thread.sleep(1000);
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[3]/div/div[2]/a")).click();
		Thread.sleep(1000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='warnings-table']/tbody/tr")).size();
		String chaptermin = null;
		String chaptermax = null;
		String conceptmin = null;
		String conceptmax = null;
		
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='warnings-table']/tbody/tr/td[1]"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase(clominimumwarning));
			Assert.assertFalse(sValue.equalsIgnoreCase(clowmaximumwarning));
		}
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='verified-table']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[1]")).getText();						
			if(sValue.equalsIgnoreCase("CLO minimum grade level is included")){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				chaptermin = "true";
	        }
			if(sValue.equalsIgnoreCase("CLO maximum grade level is included")){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				chaptermax = "true";
	        }
			if(sValue.equalsIgnoreCase("Concept card minimum grade level is included")){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				conceptmin = "true";
	        }			
			if(sValue.equalsIgnoreCase("Concept card maximum grade level is included")){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				conceptmax = "true";
	        }

		}
		Assert.assertEquals("true", chaptermin);
		Assert.assertEquals("true", chaptermax);
		Assert.assertEquals("true", conceptmin);
		Assert.assertEquals("true", conceptmax);
		
		// Navigate back to the Tagging Tool Overview, remove the Learning Objective, and logout and exit.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Learning Objective")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}

		mddbPage.logout();	
		
	}

}