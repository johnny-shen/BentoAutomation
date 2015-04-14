package Production_QA;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_2140 {

	@Test
	public void CLOP_2140_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(3000);
		
		// Navigate to Learning Objectives Pending Approval.
		Chrome_Driver.findElement(By.xpath("//*[@href='learning-objectives/']")).click();
		Thread.sleep(3000);
		Chrome_Driver.findElement(By.xpath("//*[@id='pendingapproval']")).click();
		Thread.sleep(5000);
		
		// Validate the initial display in that the objective is collapsed and the parent is hidden.	
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='list-group']/li[1]/a/div/div[1]/small")).getText().isEmpty());
		Assert.assertFalse(Chrome_Driver.findElement(By.xpath("//*[@class='list-group']/li[1]/a/div/div[2]/small")).getText().isEmpty());
		Assert.assertEquals("0px", Chrome_Driver.findElement(By.xpath("//*[@class='list-group']/li[1]/a/div/div[2]")).getCssValue("padding-left"));	
	
		// Validate the values when the objective is expanded showing the parent objective and the child objective is indented.
		Chrome_Driver.findElement(By.xpath("//*[@class='list-group']/li[1]/a/div/div[2]")).click();
		Thread.sleep(1000);
		Assert.assertFalse(Chrome_Driver.findElement(By.xpath("//*[@class='list-group']/li[1]/a/div/div[1]/small")).getText().isEmpty());	
		Assert.assertEquals("0px", Chrome_Driver.findElement(By.xpath("//*[@class='list-group']/li[1]/a/div/div[1]")).getCssValue("padding-left"));
		Assert.assertEquals("15px", Chrome_Driver.findElement(By.xpath("//*[@class='list-group']/li[1]/a/div/div[2]")).getCssValue("padding-left"));	
	
		// Validate the values when the objective is collapsed again.
		Chrome_Driver.findElement(By.xpath("//*[@class='list-group']/li[1]/a/div/div[2]")).click();
		Thread.sleep(1000);
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='list-group']/li[1]/a/div/div[1]/small")).getText().isEmpty());
		Assert.assertFalse(Chrome_Driver.findElement(By.xpath("//*[@class='list-group']/li[1]/a/div/div[2]/small")).getText().isEmpty());
		Assert.assertEquals("0px", Chrome_Driver.findElement(By.xpath("//*[@class='list-group']/li[1]/a/div/div[2]")).getCssValue("padding-left"));		

		// Logout and exit.
		mddbPage.logout();	
		
	}

}