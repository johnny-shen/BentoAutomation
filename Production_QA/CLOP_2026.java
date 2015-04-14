package Production_QA;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import pages.MetadataDashboardPage;

public class CLOP_2026 {

	@Test
	public void CLOP_2026_Test() throws Exception {

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

		// Add a Learning Objective and validate that most Metadata Values now show an Inherited icon.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
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
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();		
			if (sValue.equalsIgnoreCase("Learning Statements")) {
				mddbPage.inherittest(i); 
			}
			if (sValue.equalsIgnoreCase("Bloom's Taxonomy Revised")) {
				mddbPage.inherittest(i); 
			}
			if (sValue.equalsIgnoreCase("Webb's DOK (2002)")) {
				mddbPage.inherittest(i); 
			}
			if (sValue.equalsIgnoreCase("Maximum Grade Level")) {
				mddbPage.inherittest(i); 
			}
			if (sValue.equalsIgnoreCase("Subject")) {
				mddbPage.inherittest(i); 
			}
	    }
		
		// Validate that the CLO titles are displayed properly and a mouseover shows a tool tip.
		WebElement menu = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr[1]/td[3]/span"));
		Actions builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		Thread.sleep(2000);
		Assert.assertEquals("Inherited Metadata", Chrome_Driver.findElement(By.xpath("//*[@class='popover-title']")).getText());
		Assert.assertEquals("This metadata has been inherited either from it's parent or other associated metadata.", Chrome_Driver.findElement(By.xpath("//*[@class='popover-content']")).getText());

		// Go to the Concept page and validate that the Inherited icon appears, including for Metadata Learning Objective.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(3000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();		
			if (sValue.equalsIgnoreCase("Learning Objective")) {
				mddbPage.inherittest(i); 
			}
			if (sValue.equalsIgnoreCase("Learning Statements")) {
				mddbPage.inherittest(i); 
			}
			if (sValue.equalsIgnoreCase("Bloom's Taxonomy Revised")) {
				mddbPage.inherittest(i); 
			}
			if (sValue.equalsIgnoreCase("Webb's DOK (2002)")) {
				mddbPage.inherittest(i); 
			}
			if (sValue.equalsIgnoreCase("Maximum Grade Level")) {
				mddbPage.inherittest(i); 
			}
			if (sValue.equalsIgnoreCase("Subject")) {
				mddbPage.inherittest(i); 
				Assert.assertEquals("Science", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());		
			}
	    }
			
		// Add a new Subject and validate the new value on the Concept page and the presence of a deleted icon that can remove the value.
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Subjects");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);	
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_subjectselector']/a/span[2]/b")).click();
		Thread.sleep(3000);
		rowCount = Chrome_Driver.findElements(By.xpath("//*[@id='select2-drop']/ul/li")).size();
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li["+i+"]")).getText();
			if(sValue.equalsIgnoreCase("Career Education")) {
				Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li["+i+"]")).click();
				break;
			}
		}
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(2000);		
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();		
			if (sValue.equalsIgnoreCase("Subject")) {
				Assert.assertEquals("Career Education", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
			}
	    }
		
		// Remove the added Subject value.
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Subject")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		
		// Validate that the original Subject value and Inherited icon appears again.
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();		
			if (sValue.equalsIgnoreCase("Subject")) {
				mddbPage.inherittest(i); 
				Assert.assertEquals("Science", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());		
			}
	   }
		
		// Navigate back to the Tagging Tool Overview, remove Learning Objective, and logout and exit.
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