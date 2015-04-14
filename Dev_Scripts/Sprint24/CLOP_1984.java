package Sprint24;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import pages.MetadataDashboardPage;

public class CLOP_1984 {

	@Test
	public void CLOP_1984_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login as a Program Manager.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(3000);
		
		// Navigate to the Math K-5: Count On page that does not have a Learning Objective.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		
		// Go to the Concept page and add a set of Grade Ranges and validate the initial values.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(3000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Grade Range");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);	
		Thread.sleep(2000);
		Select mydrpdwn = new Select(Chrome_Driver.findElement(By.xpath("//*[@name='mingrade']")));
		mydrpdwn.selectByVisibleText("1st Grade");
		mydrpdwn = new Select(Chrome_Driver.findElement(By.xpath("//*[@name='maxgrade']")));
		mydrpdwn.selectByVisibleText("5th Grade");
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(2000);
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();		
			if (sValue.equalsIgnoreCase("Minimum Grade Level")) {
				Assert.assertEquals("1", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
			}
			if (sValue.equalsIgnoreCase("Maximum Grade Level")) {
				Assert.assertEquals("5", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
			}
	    }
		
		// Edit the Grade Range values and validate the new Minimum and Maximum values.
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();		
			if (sValue.equalsIgnoreCase("Minimum Grade Level")) {
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[1]")).click();
				break;
			}
	    }
		Thread.sleep(2000);
		mydrpdwn = new Select(Chrome_Driver.findElement(By.xpath("//*[@name='mingrade']")));
		mydrpdwn.selectByVisibleText("2nd Grade");
		mydrpdwn = new Select(Chrome_Driver.findElement(By.xpath("//*[@name='maxgrade']")));
		mydrpdwn.selectByVisibleText("6th Grade");
		Chrome_Driver.findElement(By.xpath("//*[@id='editsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(2000);		
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();		
			if (sValue.equalsIgnoreCase("Minimum Grade Level")) {
				Assert.assertEquals("2", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
			}
			if (sValue.equalsIgnoreCase("Maximum Grade Level")) {
				Assert.assertEquals("6", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
			}
	    }
		
		// Remove the Grade Range values, logout, and exit.
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Minimum Grade Level")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Maximum Grade Level")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		
		mddbPage.logout();
	
	}

}