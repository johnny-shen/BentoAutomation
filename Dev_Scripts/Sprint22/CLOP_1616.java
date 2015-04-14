package Sprint22;

import org.junit.Assert;
import pages.MetadataDashboardPage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class CLOP_1616 {

	@Test
	public void CLOP_1616_Test() throws Exception {

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
		String LOmissing = "CLO learning objective missing";
		String LOmissingmessage = "The CLO needs a learning objective.Resolve";
		String Conceptauthormissing = "Concept card author missing";
		String Conceptauthormissingmessage = "The Concept card needs an author name or organization.Resolve";
		String Conceptwidgetchmissing = "Concept card widget copyright holder missing";
		String Conceptwidgetchmissingmessage = "The Concept card needs a copyright holder name or organization associated with the widget.Resolve";
		
		// Navigate to the Math K-5: Count On page that does not have a Learning Objective
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		
		// Click on the Summary Page link and validate the Warnings text are displayed correctly.
		Chrome_Driver.findElement(By.xpath("//*[@id='to_summary']")).click();
		Thread.sleep(3000);		
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[2]/div/div[2]/a")).click();
		Thread.sleep(1000);
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='warnings-table']/tbody/tr")).size();
		String lomissingnull = null;
		String conceptauthornull = null;
		String conceptwidgetnull = null;
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='warnings-table']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase(LOmissing)){
				Assert.assertEquals(LOmissingmessage, Chrome_Driver.findElement(By.xpath("//*[@id='warnings-table']/tbody/tr["+i+"]/td[2]")).getText());
				lomissingnull = "true";
	        }
			if(sValue.equalsIgnoreCase(Conceptauthormissing)){
				Assert.assertEquals(Conceptauthormissingmessage, Chrome_Driver.findElement(By.xpath("//*[@id='warnings-table']/tbody/tr["+i+"]/td[2]")).getText());
				conceptauthornull = "true";
	        }
			if(sValue.equalsIgnoreCase(Conceptwidgetchmissing)){
				Assert.assertEquals(Conceptwidgetchmissingmessage, Chrome_Driver.findElement(By.xpath("//*[@id='warnings-table']/tbody/tr["+i+"]/td[2]")).getText());
				conceptwidgetnull = "true";
	        }
		}
		Assert.assertEquals("true", lomissingnull);
		Assert.assertEquals("true", conceptauthornull);
		Assert.assertEquals("true", conceptwidgetnull);

		// Logout and exit.
		mddbPage.logout();	
	
	}
	
}