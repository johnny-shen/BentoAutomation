package Sprint23;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import pages.MetadataDashboardPage;

public class CLOP_1892 {

	@Test
	public void CLOP_1892_Test() throws Exception {

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
		
		// Navigate to a CLO for testing in the Overview and validate that Thumbnail and Title should appear and cannot be removed.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_5353/67ef4864122f4861a3e7ffc559f33d12");
		Thread.sleep(10000);
		Select mydrpdwn = new Select(Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_length']/label/select")));
		mydrpdwn.selectByVisibleText("100 per page");
		String Thumbnail = "Thumbnail";
		String Title = "Title";
		String Thumbnailvalue = "../img/clo04/red_flower.svg";
		String Titlevalue = "Demo Test CLO";
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		String thumbnailmetadata = null;
		String titlemetadata = null;
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			Assert.assertFalse(sValue.equalsIgnoreCase("Paragraph"));
			Assert.assertFalse(sValue.equalsIgnoreCase("Image"));
			if(sValue.equalsIgnoreCase(Thumbnail)) {
				Assert.assertEquals(Thumbnailvalue, Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
				mddbPage.inherittest(i); 
				thumbnailmetadata = "true";
	        }
			if(sValue.equalsIgnoreCase(Title)) {
				Assert.assertEquals(Titlevalue, Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
				mddbPage.inherittest(i); 
				titlemetadata = "true";
	        }

		}
		Assert.assertEquals("true", thumbnailmetadata);
		Assert.assertEquals("true", titlemetadata);
		
		// Navigate to the Concept page and validate that the Asset Type from Habitat should not appear on the Metadata table.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(3000);
		mydrpdwn = new Select(Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_length']/label/select")));
		mydrpdwn.selectByVisibleText("100 per page");
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			Assert.assertFalse(sValue.equalsIgnoreCase("Paragraph"));
			Assert.assertFalse(sValue.equalsIgnoreCase("Image"));
		}
		
		// Navigate to the Summary page and validate that the Thumbnail and Title entries appear in Metadata table, Paragraph and Image do not appear in all three tables, and actual Image appears.
		Chrome_Driver.findElement(By.xpath("//*[@id='to_summary']")).click();
		Thread.sleep(3000);		
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[1]/div/div[2]/a")).click();
		Thread.sleep(1000);
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[2]/div/div[2]/a")).click();
		Thread.sleep(1000);
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[3]/div/div[2]/a")).click(); 
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadata-table']/tbody/tr")).size();
		thumbnailmetadata = null;
		titlemetadata = null;
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadata-table']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase(Thumbnail)) {
				Assert.assertEquals(Thumbnailvalue, Chrome_Driver.findElement(By.xpath("//*[@id='metadata-table']/tbody/tr["+i+"]/td[2]")).getText());
				thumbnailmetadata = "true";
	        }
			if(sValue.equalsIgnoreCase(Title)) {
				Assert.assertEquals(Titlevalue, Chrome_Driver.findElement(By.xpath("//*[@id='metadata-table']/tbody/tr["+i+"]/td[2]")).getText());
				titlemetadata = "true";
	        }
			Assert.assertFalse(sValue.equalsIgnoreCase("Paragraph"));
			Assert.assertFalse(sValue.equalsIgnoreCase("Image"));
		}
		Assert.assertEquals("true", thumbnailmetadata);
		Assert.assertEquals("true", titlemetadata);
		
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='warnings-table']/tbody/tr/td[1]"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase("Paragraph"));
			Assert.assertFalse(sValue.equalsIgnoreCase("Image"));
		}
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='verified-table']/tbody/tr/td[1]"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase("Paragraph"));
			Assert.assertFalse(sValue.equalsIgnoreCase("Image"));
		}
		Assert.assertEquals("true", thumbnailmetadata);
		Assert.assertEquals("true", titlemetadata);
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@id='image-holder']/img")).getAttribute("src").contains("https://partner.inkling.com/rawfiles/sn_5353/img/clo04/red_flower.svg"));
		
		// Logout and exit.
		mddbPage.logout();	
	
	}
	
}