package Sprint23;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1871 {

	@Test
	public void CLOP_1871_Test() throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();

		// Navigate to the Math K-5: Count On page that does not have a Learning Objective.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		
		// Add a Date.
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='select2-drop']/ul/li/ul/li/div"));
		for  (WebElement eachResult : AllSearchResults) {
		    String match = "Date";
		    String value = eachResult.getText();
		    if (value.equals(match)) {       
		        eachResult.click();
		        break ;
		    }
		}
		Chrome_Driver.findElement(By.xpath("//*[@id='createddateselector']")).sendKeys("2015-01-18");
		Chrome_Driver.findElement(By.xpath("//*[@id='modifieddateselector']")).sendKeys("2015-01-19");
		Chrome_Driver.findElement(By.xpath("//*[@id='publisheddateselector']")).sendKeys("2015-01-20");
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);
		
		// Revise the Created Date.
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();		
			if (sValue.equalsIgnoreCase("Date Created")) {
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[1]")).click();
				break;
			}
	    }
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='createddateselector']")).clear();
		Chrome_Driver.findElement(By.xpath("//*[@id='createddateselector']")).sendKeys("2015-01-05");
		Chrome_Driver.findElement(By.xpath("//*[@id='editsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);
		
		// Add an Expiration Date and Edit it.
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='select2-drop']/ul/li/ul/li/div"));
		for  (WebElement eachResult : AllSearchResults) {
		    String match = "Expiration Date";
		    String value = eachResult.getText();
		    if (value.equals(match)) {       
		        eachResult.click();
		        break ;
		    }
		}
		Chrome_Driver.findElement(By.xpath("//*[@id='experationSelector']")).sendKeys("2018-01-01");
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();		
			if (sValue.equalsIgnoreCase("Expiration Date")) {
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[1]")).click();
				break;
			}
	    }
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='experationSelector']")).clear();
		Chrome_Driver.findElement(By.xpath("//*[@id='experationSelector']")).sendKeys("2019-01-01");
		Chrome_Driver.findElement(By.xpath("//*[@id='editsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);
		
		// Validate the edited Metadata values.
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();		
			if (sValue.equalsIgnoreCase("Date Created")) {
				Assert.assertEquals("2015-01-05", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
			}
			if (sValue.equalsIgnoreCase("Date Modified")) {
				Assert.assertEquals("2015-01-19", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
			}
			if (sValue.equalsIgnoreCase("Date Published")) {
				Assert.assertEquals("2015-01-20", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
			}
			if (sValue.equalsIgnoreCase("Expiration Date")) {
				Assert.assertEquals("2019-01-01", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
			}
	    }
		
		// Remove all the Metadata values, Logout and exit.
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Date Created")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Date Modified")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Date Published")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Expiration Date")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		
		mddbPage.logout(); 
		
	}

}