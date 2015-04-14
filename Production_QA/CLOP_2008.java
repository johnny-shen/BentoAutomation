package Production_QA;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_2008 {

	@Test
	public void CLOP_2008_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and attempt to login with a pending user.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("clop_2008_pend", "clop2008pend");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(3000);
		
		// Validate the Pending notice and message.
		Assert.assertEquals("Your user account is pending administrative approval.", Chrome_Driver.findElement(By.xpath("//*[@id='ResendAdministratorNotification']/h2")).getText());
		Assert.assertEquals("Hello CLOP Lastname,", Chrome_Driver.findElement(By.xpath("//*[@id='ResendAdministratorNotification']/p[1]")).getText());
		Assert.assertEquals("We've sent a notification to the site administrator in order to approve your account for use. If it's been a while and you feel like something's gone amiss, feel free to use the form below to resend the administrator notification.", Chrome_Driver.findElement(By.xpath("//*[@id='ResendAdministratorNotification']/p[2]")).getText());
		Assert.assertEquals("Cancel", Chrome_Driver.findElement(By.xpath("//*[@id='registercancel']")).getText());
		Assert.assertEquals("Resend Administrator Notification", Chrome_Driver.findElement(By.xpath("//*[@id='resendnotificationbutton']")).getText());
		Assert.assertEquals("McGraw-Hill Education", Chrome_Driver.findElement(By.xpath("//*[@class='company-name']")).getText());
		
		// Exit.
		Chrome_Driver.quit();
		
	}

}
