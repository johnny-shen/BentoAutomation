package Sprint27;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import pages.MetadataDashboardPage;

public class CLOP_2297 {

	@Test
	public void CLOP_2297_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "src\\pages\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and validate and record the number of Published, Updated, and Private are displayed and total Projects as well.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(5000);		
		Assert.assertEquals("Published", Chrome_Driver.findElement(By.xpath("//*[@class='Published-name']/a")).getText());
		Assert.assertEquals("Updated", Chrome_Driver.findElement(By.xpath("//*[@class='Updated-name']/a")).getText());
		Assert.assertEquals("Private", Chrome_Driver.findElement(By.xpath("//*[@class='Private-name']/a")).getText());
		int publishnumber = Integer.parseInt(Chrome_Driver.findElement(By.xpath("//*[@class='Published-count']/a")).getText());
		int updatenumber = Integer.parseInt(Chrome_Driver.findElement(By.xpath("//*[@class='Updated-count']/a")).getText());
		int privatenumber = Integer.parseInt(Chrome_Driver.findElement(By.xpath("//*[@class='Private-count']/a")).getText());
		
		// Navigate to a Summary page of a CLO that does not have any warnings and has recently been deleted.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/clo/sn_2643/890080c2172043e0beb242187eee6beb/");
		Thread.sleep(8000);	
		Assert.assertEquals("0", Chrome_Driver.findElement(By.xpath("//*[@id='warnings-count']")).getText());
		Assert.assertEquals("This CLO has been removed from publication.", Chrome_Driver.findElement(By.xpath("//*[@id='publishing-holder']/h4")).getText());
		
		// Do a Private publish of the CLO and validate that the number of Private CLOs has been incremented in the homepage.
		Select mydrpdwn = new Select(Chrome_Driver.findElement(By.xpath("//*[@id='action']")));
		mydrpdwn.selectByVisibleText("Private");
		Chrome_Driver.findElement(By.xpath("//*[@id='publishform']/button")).click();
		Thread.sleep(8000);	
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(5000);
		int newprivatenumber = Integer.parseInt(Chrome_Driver.findElement(By.xpath("//*[@href='/users/clo?status=Private']")).getText());
		Assert.assertTrue(newprivatenumber > privatenumber);

		// In the Summary page, do a public Publish of the CLO and validate that the Private CLO has been decremented while Published has been incremented.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/clo/sn_2643/890080c2172043e0beb242187eee6beb/");
		Thread.sleep(8000);	
		mydrpdwn = new Select(Chrome_Driver.findElement(By.xpath("//*[@id='action']")));
		mydrpdwn.selectByVisibleText("Publish");
		Chrome_Driver.findElement(By.xpath("//*[@id='publishform']/button")).click();
		Thread.sleep(8000);	
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(5000);
		int newpublishnumber = Integer.parseInt(Chrome_Driver.findElement(By.xpath("//*[@class='Published-count']/a")).getText());
		int decprivatenumber = Integer.parseInt(Chrome_Driver.findElement(By.xpath("//*[@class='Private-count']/a")).getText());
		Assert.assertTrue(newpublishnumber > publishnumber);
		Assert.assertTrue(decprivatenumber < newprivatenumber);
		
		// In the Summary page, do an Update of the CLO and validate that the Published CLO has been decremented while Updated has been incremented.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/clo/sn_2643/890080c2172043e0beb242187eee6beb/");
		Thread.sleep(8000);	
		mydrpdwn = new Select(Chrome_Driver.findElement(By.xpath("//*[@id='action']")));
		mydrpdwn.selectByVisibleText("Update");
		Chrome_Driver.findElement(By.xpath("//*[@id='publishform']/button")).click();
		Thread.sleep(8000);	
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(5000);
		int newupdatenumber = Integer.parseInt(Chrome_Driver.findElement(By.xpath("//*[@class='Updated-count']/a")).getText());
		int decpublishnumber = Integer.parseInt(Chrome_Driver.findElement(By.xpath("//*[@class='Published-count']/a")).getText());
		Assert.assertTrue(newupdatenumber > updatenumber);
		Assert.assertTrue(decpublishnumber < newpublishnumber);
		
		// Navigate to a Summary and delete the CLO to prepare for the next text and do a final check on published project.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/clo/sn_2643/890080c2172043e0beb242187eee6beb/");
		Thread.sleep(8000);	
		mydrpdwn = new Select(Chrome_Driver.findElement(By.xpath("//*[@id='action']")));
		mydrpdwn.selectByVisibleText("Delete");
		Chrome_Driver.findElement(By.xpath("//*[@id='publishform']/button")).click();
		Thread.sleep(8000);	
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(5000);
		
		// Logout, and exit.
		mddbPage.logout();	
		
	}

}