package Production_QA;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1863 {

	@Test
	public void CLOP_1863_Test() throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(3000);
		String cloassessmentcardverify = "CLO Assessment card is included";
		String assessmentcardwarning = "Assessment card title missing";
		String assessmentcardwarningmsg = "The Assessment card needs a title.Resolve";
		String assesscardwarningremoved = "Assess card title missing";
		String assessmentcardverify = "Assessment card 508 Compliant is included";
		String assesscardverifyremoved = "Assess card 508 Compliant is included";
		String passed = "Requirement verified";

		// Navigate to a CLO that does not have a Learning Objective
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_2643/3985bc50e9b949db99414c22182d9b7c");
		Thread.sleep(10000);
		Assert.assertEquals("Concept", Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).getAttribute("data-name"));
		Assert.assertEquals("Model", Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[2]/a")).getAttribute("data-name"));
		Assert.assertEquals("Practice", Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[3]/a")).getAttribute("data-name"));
		Assert.assertEquals("Assess", Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[4]/a")).getAttribute("data-name"));
		
		// Click on the Summary Page link and validate the Warning publication entry now show Assessment and not Assess.
		Chrome_Driver.findElement(By.xpath("//*[@id='to_summary']")).click();
		Thread.sleep(3000);		
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[2]/div/div[2]/a")).click();
		Thread.sleep(1000);
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[3]/div/div[2]/a")).click();
		Thread.sleep(1000);
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='warnings-table']/tbody/tr")).size();
		String assessmentwarning = null;
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='warnings-table']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase(assessmentcardwarning)){
				Assert.assertEquals(assessmentcardwarningmsg, Chrome_Driver.findElement(By.xpath("//*[@id='warnings-table']/tbody/tr["+i+"]/td[2]")).getText());
				assessmentwarning = "true";
	        }
			Assert.assertFalse(sValue.equalsIgnoreCase(assesscardwarningremoved));
		}
		Assert.assertEquals("true", assessmentwarning);
	
		// Validate that in the Verified table CLO Assessment card is included appears and other references are changed from Assess to Assessment.
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='verified-table']/tbody/tr")).size();
		String cloassessmentverify = null;
		String assessmentcardverification = null;
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[1]")).getText();						
			if(sValue.equalsIgnoreCase(cloassessmentcardverify)){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				cloassessmentverify = "true";
	        }
			if(sValue.equalsIgnoreCase(assessmentcardverify)){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				assessmentcardverification = "true";
	        }
			Assert.assertFalse(sValue.equalsIgnoreCase(assesscardverifyremoved));
		}
		Assert.assertEquals("true", cloassessmentverify);
		Assert.assertEquals("true", assessmentcardverification);
		
		// Logout and exit.
		 mddbPage.logout();
	
	}
	
}