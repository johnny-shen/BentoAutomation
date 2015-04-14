package Production_QA;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.MetadataDashboardPage;

public class CLOP_1951 {

	@Test
	public void CLOP_1951_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login as a Program Manager.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test_author", "Password1");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(3000);
		
		// Navigate and validate the correct entries on the Program Author profile page.
		Chrome_Driver.findElement(By.xpath("//*[@id='username_link']/a")).click();
		Thread.sleep(3000);	
		Assert.assertEquals("Edit", Chrome_Driver.findElement(By.xpath("//*[@class='btn btn-sm btn-default edit-user']")).getText());
		Chrome_Driver.findElement(By.xpath("//*[@href='/groups/view/2']")).click();
		Thread.sleep(3000);	
		int rowCount=Chrome_Driver.findElements(By.xpath("html/body/div[1]/div[2]/div[3]/div/div/table/tbody/tr")).size();
		for (int i=2;i<=rowCount;i++){
			String Group = null;
			String Actions = null;
			Group = Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[3]/div/div/table/tbody/tr["+i+"]/td[3]")).getText();
			Actions = Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div[3]/div/div/table/tbody/tr["+i+"]/td[6]")).getText();
			Assert.assertEquals("2", Group);
			Assert.assertEquals("View", Actions);
			Assert.assertFalse(Actions.equalsIgnoreCase("Delete"));
			Assert.assertFalse(Actions.equalsIgnoreCase("Edit"));
		}
		
		// Navigate to the Learning Statements and validate that Add LS, Edit, Delete, Reject, and Accept should not appear.
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(2000);	
		Chrome_Driver.findElement(By.xpath("//*[@href='learning-objectives/']")).click();
		Thread.sleep(3000);
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/ul/li[2]/a")).click();
		Chrome_Driver.findElement(By.xpath("//*[@id='statement-search-value']")).sendKeys("SG test learning statement - 1");
		Chrome_Driver.findElement(By.xpath("//*[@id='statement-search-button']")).click();
		List<WebElement>hiddentoc = Chrome_Driver.findElements(By.xpath("//*[@id='show-add-button']"));
		assertTrue(hiddentoc.isEmpty());
		hiddentoc = Chrome_Driver.findElements(By.xpath("//*[@id='search-results-learning-statements']/li/div/a"));
		assertTrue(hiddentoc.isEmpty());
		Chrome_Driver.findElement(By.xpath("//*[@id='pendingapproval']/a")).click();
		Assert.assertEquals("display: none;", Chrome_Driver.findElement(By.xpath("//*[@id='approval-content']/div[3]")).getAttribute("style"));
		
		// Navigate to a Summary Page and validate that the Publish selection is NOT available.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/clo/sn_2cb/5c4b38804700499c8638916ebf4adeca/");
		Thread.sleep(3000);
		hiddentoc = Chrome_Driver.findElements(By.xpath("//*[@id='action']/option[1]"));
		assertTrue(hiddentoc.isEmpty());
		Assert.assertEquals("You do not have permission to publish CLO.", Chrome_Driver.findElement(By.xpath("//*[@id='publishing-holder']/div")).getText());
		
		// Logout and exit.
		mddbPage.logout();	
		
	}

}