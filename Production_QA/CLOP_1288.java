package Production_QA;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1288 {

	@Test
	public void CLOP_1288_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		String concept = "Concept Terms";
		String LO = "Learning Objective";
		
		// Navigate to the Math K-5: Count On page that does not have a Learning Objective.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		
		// Add a Learning Objective that has some concepts.
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Learning Objective");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);	
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_learningobjectiveselector']/a/span[2]/b")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("RM LO fix");
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		
		// Validate that in the Overview page, Concept Terms is disabled and that Concept Terms do not appear in the Metadata Table.
		Thread.sleep(2000);
		Assert.assertEquals("true", Chrome_Driver.findElement(By.xpath("//*[@id='metadatakey']/optgroup[4]/option[3]")).getAttribute("disabled"));
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr/td[1]"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase(concept));
		}
		
		// Go to the Concept Image page and validate that Concept Terms do not appear in the Metadata Table.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[2]/a")).click();
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr/td[1]"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase(concept));
		}
		
		// Go to the Concept page and validate that Concept Terms do appear in the Metadata Table.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(3000);
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		String conceptterm = null;
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if (sValue.equalsIgnoreCase(concept)) {
				mddbPage.inherittest(i); 
				conceptterm= "true";
				break;
			}
	    }
		Assert.assertEquals("true", conceptterm);
		
		// Select the Concept Terms page.
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Concept Terms");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);	
		Thread.sleep(3000);
		
		// Record a selected Concept Term, and deselect that concept term.
		String removedconcept = Chrome_Driver.findElement(By.xpath("//*[@id='concepttermscontainer']/div[1]/label/p")).getText();
		Chrome_Driver.findElement(By.xpath("//*[@id='concepttermscontainer']/div[1]/label/input")).click();
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);
		
		// Validate that the removed Concept Term does not appear anymore
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr/td[2]"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase(removedconcept));
		}
		
		// Navigate back to the Overview page and remove the Learning Objective
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/a")).click();
		Thread.sleep(2000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {	
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if (sValue.equalsIgnoreCase(LO)) {	
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(5000);
				break;
			}
		}
		
		// Navigate to the Concept page and validate that the Concept Terms have been removed, then logout and exit.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(2000);
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr/td[1]"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase(concept));
		}

		mddbPage.logout();
		
	}

}