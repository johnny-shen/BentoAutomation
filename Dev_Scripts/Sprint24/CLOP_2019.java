package Sprint24;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_2019 {

	@Test
	public void CLOP_2019_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		String reportissuelink = null;
		
		// Validate the presence of the Report Issue link in the footer.
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='footer-links-container']/ul/li"));	
		for  (WebElement eachResult : AllSearchResults) {
			String sValue = null;
			sValue = eachResult.getText();		
			if (sValue.equalsIgnoreCase("Report Issue")) {
				reportissuelink = "true";
				eachResult.click();
				Thread.sleep(5000);
				break;
			}
		}
		Assert.assertEquals("true", reportissuelink);
		
		// Validate the Bento Issue Reporting page.
		Assert.assertEquals("Bento Issue Reporting", Chrome_Driver.findElement(By.xpath("//*[@id='myFeedbackModalLabel']")).getText());
		Assert.assertEquals("Summary", Chrome_Driver.findElement(By.xpath("//*[@for='issue-summary']")).getText());
		Assert.assertEquals("Description", Chrome_Driver.findElement(By.xpath("//*[@for='issue-description']")).getText());
		Assert.assertEquals("Priority", Chrome_Driver.findElement(By.xpath("//*[@for='issue-priority']")).getText());
		Assert.assertEquals("Cancel", Chrome_Driver.findElement(By.xpath("//*[@class='btn btn-sm btn-default']")).getText());
		Assert.assertEquals("Submit", Chrome_Driver.findElement(By.xpath("//*[@id='feedbackSubmit']")).getAttribute("value"));
		Assert.assertEquals("×", Chrome_Driver.findElement(By.xpath("//*[@id='myFeedbackModal']/div/div/div[1]/button/span")).getText());
			
		// Validate that pressing the Cancel button will close the Bento Issue Reporting page.
		Chrome_Driver.findElement(By.xpath("//*[@class='btn btn-sm btn-default']")).click();
		Thread.sleep(5000);
		
		// Reopen the Bento Issue Reporting page and validate that pressing the X button will close the page.
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='footer-links-container']/ul/li"));	
		for  (WebElement eachResult : AllSearchResults) {
			String sValue = null;
			sValue = eachResult.getText();		
			if (sValue.equalsIgnoreCase("Report Issue")) {
				reportissuelink = "true";
				eachResult.click();
				Thread.sleep(5000);
				break;
			}
		}
		Chrome_Driver.findElement(By.xpath("//*[@id='myFeedbackModal']/div/div/div[1]/button/span")).click();
		Thread.sleep(2000);
		
		// Navigate to the Content Browser page and click on the Bento Issue Reporting page again.
		Chrome_Driver.findElement(By.xpath("//*[@href='#clo-section']")).click();
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@class='row dashboard-links text-center']/div/a/img"));	
		for  (WebElement eachResult : AllSearchResults) {
			String sValue = null;
			sValue = eachResult.getAttribute("src");	
			if (sValue.equalsIgnoreCase("https://metadatadev.clo.edmesh.com/img/home/nav-icon-content-browser.png")) {
				eachResult.click();
				Thread.sleep(5000);
				break;
			}
		}
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='footer-links-container']/ul/li"));	
		for  (WebElement eachResult : AllSearchResults) {
			String sValue = null;
			sValue = eachResult.getText();		
			if (sValue.equalsIgnoreCase("Report Issue")) {
				reportissuelink = "true";
				eachResult.click();
				Thread.sleep(5000);
				break;
			}
		}
		
		// Validate the Bento Issue Reporting page from the Content Browser page.
		Assert.assertEquals("Bento Issue Reporting", Chrome_Driver.findElement(By.xpath("//*[@id='myFeedbackModalLabel']")).getText());
		Assert.assertEquals("Summary", Chrome_Driver.findElement(By.xpath("//*[@for='issue-summary']")).getText());
		Assert.assertEquals("Description", Chrome_Driver.findElement(By.xpath("//*[@for='issue-description']")).getText());
		Assert.assertEquals("Priority", Chrome_Driver.findElement(By.xpath("//*[@for='issue-priority']")).getText());
		Assert.assertEquals("Cancel", Chrome_Driver.findElement(By.xpath("//*[@class='btn btn-sm btn-default']")).getText());
		Assert.assertEquals("Submit", Chrome_Driver.findElement(By.xpath("//*[@id='feedbackSubmit']")).getAttribute("value"));
		Assert.assertEquals("×", Chrome_Driver.findElement(By.xpath("//*[@id='myFeedbackModal']/div/div/div[1]/button/span")).getText());
		Chrome_Driver.findElement(By.xpath("//*[@class='btn btn-sm btn-default']")).click();
		Thread.sleep(2000);
				
		// Logout and exit.
		mddbPage.logout(); 
		
	}

}