package Production_QA;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1668 {

	@Test
	public void CLOP_1668_Test() throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		
		// Navigate to the Math K-5: Count On page that does not have a Learning Objective.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		
		// Validate that in the Overview Add New Metadata drop-down box certain selections are disabled.
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatakey']/optgroup/option"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getAttribute("value");
			if(sValue.equalsIgnoreCase("subject") || sValue.equalsIgnoreCase("graderange") || sValue.equalsIgnoreCase("cognitivelevel") ||
			   sValue.equalsIgnoreCase("timeontask") || sValue.equalsIgnoreCase("learningstatement") || sValue.equalsIgnoreCase("conceptterms")) {
				Assert.assertEquals("true", eachResult.getAttribute("disabled"));
			}
		}
		
		// Navigate to the Concept page and validate that the Learning Objective is disabled.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatakey']/optgroup/option"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getAttribute("value");
			if(sValue.equalsIgnoreCase("learningobjective")) {
				Assert.assertEquals("true", eachResult.getAttribute("disabled"));
			}
		}

		// Navigate to the Paragraph page in Concept and validate that in the drop-down box certain selections are disabled.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[1]/a")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatakey']/optgroup/option"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getAttribute("value");
			if(sValue.equalsIgnoreCase("subject") || sValue.equalsIgnoreCase("expiration") || sValue.equalsIgnoreCase("author") ||
			   sValue.equalsIgnoreCase("contributor") || sValue.equalsIgnoreCase("dates") || sValue.equalsIgnoreCase("graderange") || 
			   sValue.equalsIgnoreCase("agerange") || sValue.equalsIgnoreCase("cognitivelevel") || sValue.equalsIgnoreCase("timeontask") ||
			   sValue.equalsIgnoreCase("learningobjective") || sValue.equalsIgnoreCase("learningstatement") || sValue.equalsIgnoreCase("conceptterms") ||
			   sValue.equalsIgnoreCase("alignedstandards") || sValue.equalsIgnoreCase("compliance")) {
				Assert.assertEquals("true", eachResult.getAttribute("disabled"));
			}
		}
		
		// Navigate back to the Overview page, add a Learning Objective, then validate that the Learning Objective shows an error message.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/a")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='metadatakey']/optgroup/option"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			sValue = eachResult.getAttribute("value");
			if(sValue.equalsIgnoreCase("learningobjective")) {
				Assert.assertEquals(null, eachResult.getAttribute("disabled"));
			}
		}
		
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);		
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Learning Objective");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);	
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_learningobjectiveselector']/a/span[2]/b")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("SG Test LO with blooms and webbs");
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);		
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);		
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Learning Objective");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);	
		Thread.sleep(2000);	
		Assert.assertEquals("Learning Objective limit reached. Please remove or edit existing metadata.", Chrome_Driver.findElement(By.xpath("//*[@id='generalerror']/div")).getText());
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);		
		
		// Remove the Learning Objective, logout and exit
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Learning Objective")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(2000);
				break;
	        }
		}

		mddbPage.logout();	

	}

}