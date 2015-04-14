package Sprint24;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor; 

import pages.MetadataDashboardPage;

public class CLOP_1990 {

	@Test
	public void CLOP_1990_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		JavascriptExecutor js = (JavascriptExecutor)Chrome_Driver;
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		String concept = "Concept Terms";
		String LO = "Learning Objective";
		
		// Navigate to the Math K-5: Count On page that does not have a Learning Objective.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
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
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("RM LO for standards");
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		
		// Validate that in the Overview page, Concept Terms is disabled and that Concept Terms do not appear in the Metadata Table.
		Thread.sleep(2000);
		Assert.assertEquals("true", Chrome_Driver.findElement(By.xpath("//*[@input-type='conceptterms']")).getAttribute("disabled"));
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr/td[1]"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase(concept));
		}
		
		// Go to the Concept page and validate that Concept Terms do appear in the Metadata Table.
		Chrome_Driver.findElement(By.xpath("//*[@data-name='Concept']")).click();
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
		
		// Record a selected Concept Term, and de-select that concept term.
		js.executeScript("scroll(0, -10000)");
		Thread.sleep(5000);
		String removedconcept = Chrome_Driver.findElement(By.xpath("//*[@id='concepttermscontainer']/div[1]/label/p")).getText();
		Chrome_Driver.findElement(By.xpath("//*[@id='concepttermscontainer']/div[1]/label/input")).click();
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);
		
		// Validate that the removed Concept Term does not appear anymore.
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr/td[2]"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase(removedconcept));
		}
		
		// Go back to the Edit Concept Term page and re-add the removed concept term.
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase(concept)){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'editbutton')]")).click();
				Thread.sleep(4000);
				break;
	        }
		}	
		
		js.executeScript("scroll(0, -10000)");
		Thread.sleep(5000);
		Chrome_Driver.findElement(By.xpath("//*[@id='concepttermscontainer']/div[1]/label/input")).click();
		Chrome_Driver.findElement(By.xpath("//*[@id='editsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);
		
		// Validate that the re-added Concept Term appears again.
		String addedconceptterm = null;
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr/td[2]"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			if (sValue.equalsIgnoreCase(removedconcept)) {	
				addedconceptterm = "true";
				break;
			}
			
		}
		Assert.assertEquals("true", addedconceptterm);
		
		// Navigate back to the Overview page and remove the Learning Objective, logout and exit.
		Chrome_Driver.findElement(By.xpath("//*[@data-name='Overview']")).click();
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
		mddbPage.logout();
		
	}

}