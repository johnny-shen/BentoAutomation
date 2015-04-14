package Sprint22;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1677 {

	@Test
	public void CLOP_1677_Test() throws Exception {
	
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		
		// Navigate to the Learning Objectives and open the Create page.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/learning-objectives/");
		Thread.sleep(3000);
		Chrome_Driver.findElement(By.xpath("//*[@id='show-add-button']")).click();
		Thread.sleep(1000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_add-subject']/a/span[2]/b")).click();
		Thread.sleep(2000);
		
		// Validate that the Subject list in the Create Learning Objective page match the desired values.
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='select2-drop']/ul/li/div"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			String subjectlist = null;
			sValue = eachResult.getText();
			if(sValue.equalsIgnoreCase("Art, Music and Performing Arts") || sValue.equalsIgnoreCase("Business") || sValue.equalsIgnoreCase("Career Education") ||
			   sValue.equalsIgnoreCase("Computer and Information Technology") || sValue.equalsIgnoreCase("Education-Other") || sValue.equalsIgnoreCase("Family Studies") ||
			   sValue.equalsIgnoreCase("Health and Fitness") || sValue.equalsIgnoreCase("Language Arts") || sValue.equalsIgnoreCase("Mathematics") ||
			   sValue.equalsIgnoreCase("Professional Development") || sValue.equalsIgnoreCase("Science") || sValue.equalsIgnoreCase("Social Studies") ||
			   sValue.equalsIgnoreCase("Test Prep and Assessment") || sValue.equalsIgnoreCase("Vocational Technology") || sValue.equalsIgnoreCase("World Languages")) {
				subjectlist = "true";   
			}
			Assert.assertEquals("true", subjectlist);	
		}
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
		Chrome_Driver.findElement(By.xpath("//*[@id='add-learning-objective']/div/div/form/div[2]/button[1]")).click();
		Thread.sleep(2000);	
		
		// Navigate to the Math K-5: Count On page that does not have a Learning Objective.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(7000);
		
		// Navigate to the Concept page and validate that the selections in the Subject page match the desired values.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);		
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Subjects");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);	
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_subjectselector']/a/span[2]/b")).click();
		Thread.sleep(2000);
		
		AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='select2-drop']/ul/li/div"));
		for (WebElement eachResult : AllSearchResults) {		
			String sValue = null;
			String subjectlist = null;
			sValue = eachResult.getText();
			if(sValue.equalsIgnoreCase("Art, Music and Performing Arts") || sValue.equalsIgnoreCase("Business") || sValue.equalsIgnoreCase("Career Education") ||
			   sValue.equalsIgnoreCase("Computer and Information Technology") || sValue.equalsIgnoreCase("Education-Other") || sValue.equalsIgnoreCase("Family Studies") ||
			   sValue.equalsIgnoreCase("Health and Fitness") || sValue.equalsIgnoreCase("Language Arts") || sValue.equalsIgnoreCase("Mathematics") ||
			   sValue.equalsIgnoreCase("Professional Development") || sValue.equalsIgnoreCase("Science") || sValue.equalsIgnoreCase("Social Studies") ||
			   sValue.equalsIgnoreCase("Test Prep and Assessment") || sValue.equalsIgnoreCase("Vocational Technology") || sValue.equalsIgnoreCase("World Languages")) {
				subjectlist = "true";
			}
			Assert.assertEquals("true", subjectlist);
		}
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);

		// Logout and exit.
		mddbPage.logout();	
		
	}

}