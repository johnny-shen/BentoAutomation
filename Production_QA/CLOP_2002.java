package Production_QA;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_2002 {

	@Test
	public void CLOP_2002_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and attempt to login with a rejected user.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("clop_2002_reject", "clop2002reject");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(3000);
		
		// Validate the Rejected notice and message.
		Assert.assertEquals("Your account request has been rejected.", Chrome_Driver.findElement(By.xpath("//*[@class='form-signin-heading']")).getText());
		Assert.assertEquals("Hello again!", Chrome_Driver.findElement(By.xpath("//*[@class='helloLabel']")).getText());
		Assert.assertEquals("For some reason, an administrator has rejected your account request. As a result, you will not be able to access the Bento tools at this time. If you believe this is a mistake, please contact the administrator by email at Laura.Ellis@mheducation.com", Chrome_Driver.findElement(By.xpath("//*[@class='messageLabel']")).getText());
		Assert.assertEquals("McGraw-Hill Education", Chrome_Driver.findElement(By.xpath("//*[@class='company-name']")).getText());
		Assert.assertEquals("Back to login", Chrome_Driver.findElement(By.xpath("//*[@id='rejectioncancel']")).getText());
		
		// Exit.
		Chrome_Driver.quit();
	}

}