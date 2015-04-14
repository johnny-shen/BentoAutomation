package Sprint21;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1550 {

	@Test
	public void CLOP_1550_Test() throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
        String NoCard = "The CLO needs a Concept, Model, or Practice card.Resolve";
		String NoAssess = "The CLO needs an assess card.Resolve";
		String conceptpass = "CLO Concept card is included";
		String modelpass = "CLO Model card is included";
		String practicepass = "CLO Practice card is included";
		String assessmentpass = "CLO Assessment card is included";
		String passed = "Requirement verified";
		
		// Navigate to Grace's Test Page that has missing Chapter exhibits
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2643/65bd960071cd4ad4a394b56a787b5ae7");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(10000);
		
		// Click on the Summary Page link, validate that it navigates to the Summary Page.
		Chrome_Driver.findElement(By.xpath("//*[@id='to_summary']")).click();
		Thread.sleep(3000);		
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[2]/div/div[2]/a")).click();
		Thread.sleep(1000);

		// Validate that the four Exhibit Before Publication messages appear in the Warning table.
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='warnings-table']/tbody/tr")).size();
		String card = null;
		String assessment = null;
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='warnings-table']/tbody/tr["+i+"]/td[2]")).getText();
			if(sValue.equalsIgnoreCase(NoCard)){
				card = "true";
	        }

			if(sValue.equalsIgnoreCase(NoAssess)){
				assessment = "true";
	        }
		}
		Assert.assertEquals("true", card);
		Assert.assertEquals("true", assessment);
		
		// Validate that the Publish Submit button is disabled with some warning messages.
		Assert.assertEquals("true", Chrome_Driver.findElement(By.xpath("//*[@id='publishform']/button")).getAttribute("disabled"));
		Assert.assertEquals("Publishing has been disabled because of outstanding warnings.", Chrome_Driver.findElement(By.xpath("//*[@id='publishform-feedback']")).getText());

		// Navigate to CLO Parallax Design page that has exhibits for all four pages.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2cb/29c60bdeb1184aa5a23e4c090f77a358");
		Thread.sleep(12000);
		Chrome_Driver.findElement(By.xpath("//*[@id='to_summary']")).click();
		Thread.sleep(3000);		
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[2]/div/div[2]/a")).click();
		Thread.sleep(1000);
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[3]/div/div[2]/a")).click();
		Thread.sleep(1000);
		
		// Validate that the four Exhibit entries have been removed from the Warnings table and appear in the Passes table.
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='warnings-table']/tbody/tr")).size();
		String concept1 = null;
		String model1 = null;
		String practice1 = null;
		String assessment1 = null;
		
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='warnings-table']/tbody/tr/td[1]"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getText();
			Assert.assertFalse(sValue.equalsIgnoreCase("CLO cards missing"));
			Assert.assertFalse(sValue.equalsIgnoreCase("CLO assess card missing"));
		}
		
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='verified-table']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[1]")).getText();						
			if(sValue.equalsIgnoreCase(conceptpass)){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				concept1 = "true";
	        }
			if(sValue.equalsIgnoreCase(modelpass)){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				model1 = "true";
	        }
			if(sValue.equalsIgnoreCase(practicepass)){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				practice1 = "true";
	        }
			if(sValue.equalsIgnoreCase(assessmentpass)){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				assessment1 = "true";
	        }							
		}
		Assert.assertEquals("true", concept1);
		Assert.assertEquals("true", model1);
		Assert.assertEquals("true", practice1);
		Assert.assertEquals("true", assessment1);
	
		// Logout and exit
		mddbPage.logout();
		
	}

}
