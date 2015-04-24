package Sprint27;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import pages.MetadataDashboardPage;

public class CLOP_2311 {

	@Test
	public void CLOP_2311_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "src\\pages\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
		
		// Launch the Metadata Dashboard.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(5000);
				
		// Validate that a Synchronized time and Synchronized button appears on the homepage.
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='last-synced pull-right']")).getText().contains("Last Synced:"));
		Assert.assertEquals("Sync Warning", Chrome_Driver.findElement(By.xpath("//*[@class='sync-user-warnings btn btn-xs btn-default pull-right']")).getText());
		Assert.assertEquals("button", Chrome_Driver.findElement(By.xpath("//*[@class='sync-user-warnings btn btn-xs btn-default pull-right']")).getAttribute("type"));
		
		// Click on the Sync Warning button and validate the Sync measuring bar and the Sync date will then read today.
		Chrome_Driver.findElement(By.xpath("//*[@class='sync-user-warnings btn btn-xs btn-default pull-right']")).click();
		Thread.sleep(20000);
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@id='warning-sync-progress']/div/span")).getText().contains("Completed"));
		Thread.sleep(70000);
		Assert.assertEquals("Today", Chrome_Driver.findElement(By.xpath("//*[@class='last-synced pull-right']/span")).getText());
		
		// Refresh the page and validate that Today has been replaced by a date.
		Chrome_Driver.navigate().refresh();
		Thread.sleep(5000);
		Assert.assertTrue((!Chrome_Driver.findElement(By.xpath("//*[@class='last-synced pull-right']/span")).getText().equalsIgnoreCase("Today")));
		
		// Validate that the number of warnings in the dial equals the number displayed in CLOs with warnings:
		int dialwarningvalue = Integer.parseInt(Chrome_Driver.findElement(By.xpath("//*[@class='application-reports-graph-label']")).getText());
		Assert.assertEquals(dialwarningvalue, Integer.parseInt(Chrome_Driver.findElement(By.xpath("//*[@id='application-clo-warning']")).getText()));
		
		// Navigate to a page that does not have any warnings and remove the Rights Category tagging tool.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2643/dfb0f387d2d54ca6b9e72a2f680f5b28/");
		Thread.sleep(5000);
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Rights Category")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(3000);
				break;
	        }
		}
		
		// Navigate back to the homepage and validate that the Warnings has increased.
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(5000);
		Chrome_Driver.findElement(By.xpath("//*[@class='sync-user-warnings btn btn-xs btn-default pull-right']")).click();
		Thread.sleep(75000);
		Integer newdialwarningvalue = Integer.parseInt(Chrome_Driver.findElement(By.xpath("//*[@class='application-reports-graph-label']")).getText());
		Assert.assertTrue(newdialwarningvalue > dialwarningvalue);
		
		// Navigate back to CLO and restore the Rights Category tagging tool.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2643/dfb0f387d2d54ca6b9e72a2f680f5b28/");
		Thread.sleep(5000);
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
		
		// Navigate back to the homepage and validate that the Warnings has decreased.
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(5000);
		Chrome_Driver.findElement(By.xpath("//*[@class='sync-user-warnings btn btn-xs btn-default pull-right']")).click();
		Thread.sleep(90000);
		newdialwarningvalue = Integer.parseInt(Chrome_Driver.findElement(By.xpath("//*[@class='application-reports-graph-label']")).getText());
		Assert.assertTrue(newdialwarningvalue.equals(dialwarningvalue));
		
		mddbPage.logout();	
		
	}

}