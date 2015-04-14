package Sprint23;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1876 {

	@Test
	public void CLOP_1876_Test() throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		String LS = "Learning Statements";
		String LO = "Learning Objective";
		
		// Navigate to the Math K-5: Count On page that does not have a Learning Objective.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		
		// Add a Learning Objective
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
		
		// Validate that in the Overview page, Learning Statements is disabled and that Learning Statements do appears in the Metadata Table.
		Assert.assertEquals("true", Chrome_Driver.findElement(By.xpath("//*[@id='metadatakey']/optgroup[4]/option[2]")).getAttribute("disabled"));
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		String noLS = null;
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();			
			if (sValue.equalsIgnoreCase(LS)) {
				noLS = "true";
				break;
			}
	    }
		Assert.assertEquals("true", noLS);
		
		// Go to the Concept page and validate that the Learning Statements appear both in the Metadata Table and in the Add New Metadata section.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(2000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();		
			if (sValue.equalsIgnoreCase(LS)) {
				mddbPage.inherittest(i); 
				noLS = "true";
				break;
			}
	    }
		Assert.assertEquals("true", noLS);
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Learning Statement");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);	
		Thread.sleep(2000);
		
		// Record two of the Learning Statements.
		String LSvalue1 = Chrome_Driver.findElement(By.xpath("//*[@id='learningstatementcontainer']/div[1]/label/p")).getText();
		String LSvalue2 = Chrome_Driver.findElement(By.xpath("//*[@id='learningstatementcontainer']/div[2]/label/p")).getText();
		
		// De-select the first Learning Statement in the list and validate that it is removed from the Metadata Dashboard.
		Chrome_Driver.findElement(By.xpath("//*[@id='learningstatementcontainer']/div[1]/label/input")).click();
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(2000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		noLS = null;
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText();		
			if (sValue.equalsIgnoreCase(LSvalue1)) {
				noLS = "true";
				break;
			}
	    }
		Assert.assertEquals(null, noLS);
		
		// Click Edit on another Learning Statement in the list.
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText();		
			if (sValue.equalsIgnoreCase(LSvalue2)) {
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[1]")).click();
				break;
			}
	    }
		
		// De-select the second Learning Statement in the list and validate that it is removed from the Metadata Dashboard as well.
		Thread.sleep(5000);
		Chrome_Driver.findElement(By.xpath("//*[@id='learningstatementcontainer']/div[2]/label/input")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='editsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(2000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		noLS = null;
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText();		
			if (sValue.equalsIgnoreCase(LSvalue2)) {
				noLS = "true";
				break;
			}
	    }
		Assert.assertEquals(null, noLS);

		// Navigate back to the Overview page, remove the Learning Objective, logout and exit.
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
		mddbPage.logout();
		
	}

}