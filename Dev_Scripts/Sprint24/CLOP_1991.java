package Sprint24;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1991 {

	@Test
	public void CLOP_1991_Test() throws Exception {
		
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
		
		// Add two new Keywords and validate the initial set values.
		String Keyword1 = null;
		String Keyword2 = null;
		String Keyword3 = null;
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Keywords");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);	
		Thread.sleep(2000);   
		Chrome_Driver.findElement(By.xpath("//*[@class='select2-search-field']/input")).sendKeys("Sun");
		Chrome_Driver.findElement(By.xpath("//*[@class='select2-search-field']/input")).sendKeys(Keys.ENTER);
		Chrome_Driver.findElement(By.xpath("//*[@class='select2-search-field']/input")).sendKeys("Moon");
		Chrome_Driver.findElement(By.xpath("//*[@class='select2-search-field']/input")).sendKeys(Keys.ENTER);
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(2000);
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText();		
			if (sValue.equalsIgnoreCase("Sun")) {
				Assert.assertEquals("Keyword", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText());
				Keyword1 = "true";
			}
			if (sValue.equalsIgnoreCase("Moon")) {
				Assert.assertEquals("Keyword", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText());
				Keyword2 = "true";
			}
	    }
		Assert.assertEquals("true", Keyword1);
		Assert.assertEquals("true", Keyword2);
		
		// Add a third Keyword, validate the existing two values and the new value.
		Keyword1 = null;
		Keyword2 = null;
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText();		
			if (sValue.equalsIgnoreCase("Sun")) {
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[1]")).click();
				break;
			}
	    }
		Thread.sleep(2000);
		Assert.assertEquals("Sun", Chrome_Driver.findElement(By.xpath("//*[@id='s2id_multibox']/ul/li[1]/div")).getText());
		Assert.assertEquals("Moon", Chrome_Driver.findElement(By.xpath("//*[@id='s2id_multibox']/ul/li[2]/div")).getText());
		Chrome_Driver.findElement(By.xpath("//*[@class='select2-search-field']/input")).sendKeys("Earth");
		Chrome_Driver.findElement(By.xpath("//*[@class='select2-search-field']/input")).sendKeys(Keys.ENTER);
		Chrome_Driver.findElement(By.xpath("//*[@id='editsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(2000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText();		
			if (sValue.equalsIgnoreCase("Sun")) {
				Assert.assertEquals("Keyword", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText());
				Keyword1 = "true";
			}
			if (sValue.equalsIgnoreCase("Moon")) {
				Assert.assertEquals("Keyword", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText());
				Keyword2 = "true";
			}
			if (sValue.equalsIgnoreCase("Earth")) {
				Assert.assertEquals("Keyword", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText());
				Keyword3 = "true";
			}
	    }
		Assert.assertEquals("true", Keyword1);
		Assert.assertEquals("true", Keyword2);
		Assert.assertEquals("true", Keyword3);
		
		// Remove the first Keyword in the Keyword edit section and validate that the first keyword does not appear.
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText();		
			if (sValue.equalsIgnoreCase("Sun")) {
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[1]")).click();
				break;
			}
	    }
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_multibox']/ul/li[1]/a")).click();
		Chrome_Driver.findElement(By.xpath("//*[@id='editsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(2000);
		Keyword1 = null;
		Keyword2 = null;
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText();		
			Assert.assertFalse(sValue.equalsIgnoreCase("Sun"));
			if (sValue.equalsIgnoreCase("Moon")) {
				Assert.assertEquals("Keyword", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText());
				Keyword2 = "true";
			}
			if (sValue.equalsIgnoreCase("Earth")) {
				Assert.assertEquals("Keyword", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText());
				Keyword3 = "true";
			}
	    }
		
		Assert.assertEquals("true", Keyword2);
		Assert.assertEquals("true", Keyword3);
		
		// Remove the remaining two Keywords, logout, and exit.
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText();
			if(sValue.equalsIgnoreCase("Moon")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText();
			if(sValue.equalsIgnoreCase("Earth")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}	
		mddbPage.logout();   
	
	}

}