package Sprint21;

import java.util.List;
import pages.MetadataDashboardPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CLOP_1561 {

	@Test
	public void CLOP_1561_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(3000);
		String titlemissingwarning = "Title Missing";
		String CLOtitlemissing = "CLO title missing";
		String CLOtitlemissingmessage = "The CLO needs a title.Resolve";
		String Concepttitlemissing = "Concept card title missing";
		String Concepttitlemissingmessage = "The Concept card needs a title.Resolve";
		String CLOtitleval = "CLO title is included";
		String Concepttitleval = "Concept card title is included";
		String passed = "Requirement verified";

		// Navigate to a Automation CLO Project page that does not have a Learning Objective
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2643/3eb38a3297ad4dadb7e775af6bfd0c6f");
		Thread.sleep(5000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(2000);
		
		// Validate that there is a Warning message about a Missing Title.
		String titlewarning = null;
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[@class='list-group-item']/div/h4"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			if(sValue.equalsIgnoreCase(titlemissingwarning)) {
				titlewarning = "true";
	        }
		}
		Assert.assertEquals("true", titlewarning);
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(2000);
		
		// Click on the Summary Page link and validate the Warning publication entries for the three date entries.
		Chrome_Driver.findElement(By.xpath("//*[@id='to_summary']")).click();
		Thread.sleep(3000);		
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[2]/div/div[2]/a")).click();
		Thread.sleep(1000);
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='warnings-table']/tbody/tr")).size();
		String chaptertitle = null;
		String concepttitle = null;
		String modeltitle = null;
		String practicetitle = null;
		String assessmenttitle = null;
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='warnings-table']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase(CLOtitlemissing)){
				Assert.assertEquals(CLOtitlemissingmessage, Chrome_Driver.findElement(By.xpath("//*[@id='warnings-table']/tbody/tr["+i+"]/td[2]")).getText());
				chaptertitle = "true";
	        }
			if(sValue.equalsIgnoreCase(Concepttitlemissing)){
				Assert.assertEquals(Concepttitlemissingmessage, Chrome_Driver.findElement(By.xpath("//*[@id='warnings-table']/tbody/tr["+i+"]/td[2]")).getText());
				concepttitle = "true";
	        }
			if(sValue.equalsIgnoreCase("Model card title missing")){
				Assert.assertEquals("The Model card needs a title.Resolve", Chrome_Driver.findElement(By.xpath("//*[@id='warnings-table']/tbody/tr["+i+"]/td[2]")).getText());
				modeltitle = "true";
	        }
			if(sValue.equalsIgnoreCase("Practice card title missing")){
				Assert.assertEquals("The Practice card needs a title.Resolve", Chrome_Driver.findElement(By.xpath("//*[@id='warnings-table']/tbody/tr["+i+"]/td[2]")).getText());
				practicetitle= "true";
	        }
			if(sValue.equalsIgnoreCase("Assessment card title missing")){
				Assert.assertEquals("The Assessment card needs a title.Resolve", Chrome_Driver.findElement(By.xpath("//*[@id='warnings-table']/tbody/tr["+i+"]/td[2]")).getText());
				assessmenttitle = "true";
	        }
		}
		Assert.assertEquals("true", chaptertitle);
		Assert.assertEquals("true", concepttitle);
		Assert.assertEquals("true", modeltitle);		
		Assert.assertEquals("true", practicetitle);
		Assert.assertEquals("true", assessmenttitle);	
		
		// Navigate back to the Tagging Tool and add an Overview and Concept Title.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2643/3eb38a3297ad4dadb7e775af6bfd0c6f");
		Thread.sleep(5000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='select2-drop']/ul/li/ul/li/div"));
		for  (WebElement eachResult : AllSearchResults) {
		    String match = "Title";
		    String value = eachResult.getText();
		    if (value.equals(match)) {       
		        eachResult.click();
		        break ;
		    }
		}
		Chrome_Driver.findElement(By.xpath("//*[@id='metadatavalue']")).sendKeys("Overview Title");
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='select2-drop']/ul/li/ul/li/div"));
		for  (WebElement eachResult : AllSearchResults) {
		    String match = "Title";
		    String value = eachResult.getText();
		    if (value.equals(match)) {       
		        eachResult.click();
		        break ;
		    }
		}
		Chrome_Driver.findElement(By.xpath("//*[@id='metadatavalue']")).sendKeys("Concept Title");
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);
		
		// Navigate back to the Summary page and validate that the Title Warning table entries has been removed and appear in the Passes table.
		Chrome_Driver.findElement(By.xpath("//*[@id='to_summary']")).click();
		Thread.sleep(3000);		
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[2]/div/div[2]/a")).click();
		Thread.sleep(1000);
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[3]/div/div[2]/a")).click();
		Thread.sleep(1000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='warnings-table']/tbody/tr")).size();
		chaptertitle = null;
		concepttitle = null;
		modeltitle = null;
		
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='warnings-table']/tbody/tr/td[1]"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase(CLOtitlemissingmessage));
			Assert.assertFalse(sValue.equalsIgnoreCase(Concepttitlemissing));
		}
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='verified-table']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[1]")).getText();						
			if(sValue.equalsIgnoreCase(CLOtitleval)){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				chaptertitle = "true";
	        }
			if(sValue.equalsIgnoreCase(Concepttitleval)){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				concepttitle = "true";
	        }
			if(sValue.equalsIgnoreCase("Model card title is included")){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				modeltitle = "true";
	        }			
		}
		Assert.assertEquals("true", chaptertitle);
		Assert.assertEquals("true", concepttitle);
		Assert.assertEquals(null, modeltitle);

		// Navigate back to the Tagging Tool Overview and Concept pages, remove all Titles, and logout and exit.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2643/3eb38a3297ad4dadb7e775af6bfd0c6f");
		Thread.sleep(5000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Title")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(1000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Title")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		mddbPage.logout();
			
	}

}