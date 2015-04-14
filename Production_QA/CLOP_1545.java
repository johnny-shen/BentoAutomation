package Production_QA;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import pages.MetadataDashboardPage;

public class CLOP_1545 {

	@Test
	public void CLOP_1545_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		String RCM = "Rights Category Missing";
		String MT = "Rights Category";
		String MV = "Royalty Free";
		Chrome_Driver.manage().window().maximize();

		// Navigate to the CLO Parallax Design Tagging Tool page that already had a Learning Objective.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_2cb/29c60bdeb1184aa5a23e4c090f77a358");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(15000);
		
		// Click on and validate the image in the Concept page.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(3000);
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[1]/a")).click();
		Thread.sleep(3000);
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@id='content-preview']/img")).getAttribute("src").contains("https://partner.inkling.com/rawfiles/sn_2cb/head/img/chapter02/clobackgroun.jpeg?access_token"));
		
		// Expand the Warnings list and validate the presence of a "Rights Category Missing" message.
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(3000);
		String warning = null;
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[@class='list-group-item']/div/h4"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			if(sValue.equalsIgnoreCase(RCM)) {
				Assert.assertEquals("Rights Category Missing", eachResult.getText());
				warning = "true";
				break;
	        }
		}
		Assert.assertEquals("true", warning);
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(2000);
		
		// Add a Rights Category -> Royalty Free value.
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Rights Category");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
		Select mydrpdwn = new  Select(Chrome_Driver.findElement(By.xpath("//*[@name='rightscategory']")));
		mydrpdwn.selectByVisibleText("Royalty Free");
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(5000);
		
		// Expand the Warning list and validate that the "Rights Category Messing" message has been removed.				
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(3000);	
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[@class='list-group-item']/div/h4"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase(RCM));
		}
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(3000);
		
		// Validate that the new Rights Category title and Royalty Free value appears in the Metadata table, and remove the Metadata. 
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		String Title = null;
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase(MT)){
				Assert.assertEquals(MV, Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(3000);
				Title = "true";
				break;
	        }
	    }
		Assert.assertEquals("true", Title);
		
		// Expand the Warnings list and validate the presence of a "Rights Category Messing" message has been restored again.
		warning = null;
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(3000);
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[@class='list-group-item']/div/h4"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			if(sValue.equalsIgnoreCase(RCM)) {
				Assert.assertEquals("Rights Category Missing", eachResult.getText());
				warning = "true";
				break;
	        }
		}
		Assert.assertEquals("true", warning);
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(2000);
		
		// Logout and exit
		mddbPage.logout();
		
	}

}