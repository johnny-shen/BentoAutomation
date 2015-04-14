package Sprint25;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_2139 {

	@Test
	public void CLOP_2139_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
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
		
		// Expand all the standards to show their parent standards, and validate that the parent appears before the child.		
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@class='list-group']/li")).size();	
		for (int i=3;i<=rowCount;i++) {
			Chrome_Driver.findElement(By.xpath("//*[@class='list-group']/li["+i+"]/a/div/div[2]")).click();
			Thread.sleep(1000);
			String child = null;
			String parent = null;
			child = Chrome_Driver.findElement(By.xpath("//*[@class='list-group']/li["+i+"]/a/div/div[2]/small/strong")).getText();			
			parent = Chrome_Driver.findElement(By.xpath("//*[@class='list-group']/li["+i+"]/a/div/div[1]/small/strong")).getText();
			Assert.assertTrue((parent).compareTo(child) < 0);
		}
	   
		// Validate that the State for the Learning Objectives appear and that they are in alphabetical order.
		String state1 = Chrome_Driver.findElement(By.xpath("//*[@class='list-group']/li[1]/span")).getText();	
		String state2 = Chrome_Driver.findElement(By.xpath("//*[@class='list-group']/li[2]/span")).getText();	
		String state3 = Chrome_Driver.findElement(By.xpath("//*[@class='list-group']/li["+rowCount+"]/span")).getText();		
		Assert.assertTrue((state1).compareTo(state2) <= 0);
		Assert.assertTrue((state2).compareTo(state3) <= 0);
	
		// Logout and exit.
		mddbPage.logout();	
		
	}

}