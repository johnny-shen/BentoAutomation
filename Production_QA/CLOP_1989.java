package Production_QA;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1989 {

	@Test
	public void CLOP_1989_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login as a Program Manager.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(3000);
		
		// Navigate to the Math K-5: Count On page that does not have a Learning Objective.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		
		// Go to the Concept page and add a Subject and validate the initial value.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(3000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Subjects");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);	
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_subjectselector']/a/span[2]/b")).click();
		Thread.sleep(3000);
		int rowCount = Chrome_Driver.findElements(By.xpath("//*[@id='select2-drop']/ul/li")).size();
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li["+i+"]")).getText();
			if(sValue.equalsIgnoreCase("Business")) {
				Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li["+i+"]")).click();
				break;
			}
		}
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(2000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();		
			if (sValue.equalsIgnoreCase("Subject")) {
				Assert.assertEquals("Business", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
			}
	    }
		
		// Edit the Subject and validate the new value.
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();		
			if (sValue.equalsIgnoreCase("Subject")) {
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[1]")).click();
				break;
			}
	    }
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
		Chrome_Driver.findElement(By.xpath("//*[@id='editsubmitbutton']")).click();
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
		
		// Remove the Subject value, logout, and exit.
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
		
		mddbPage.logout(); 
	
	}

}