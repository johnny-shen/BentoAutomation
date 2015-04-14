package Production_QA;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1996 {

	@Test
	public void CLOP_1996_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		String originaltitle = "Math K-5: Count On To Add";

		// Navigate to the Math K-5: Count On page validate the default Title.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();		
			if (sValue.equalsIgnoreCase("Title")) {
				Assert.assertEquals(originaltitle, Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());		
				mddbPage.inherittest(i);
				break;
			}
	    }
		
		// Add a new Metadata Title and validate the new Title value.
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Title");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='metadatavalue']")).sendKeys("CLOP-1996");
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(5000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();		
			if (sValue.equalsIgnoreCase("Title")) {
				Assert.assertEquals("editbutton icon-large align-center", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[1]")).getAttribute("class"));
				Assert.assertEquals("deletebutton icon-large align-center", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[2]")).getAttribute("class"));
				break;
			}
	    }
		
		// Search for the original Title and validate that no search results are found.
		Chrome_Driver.findElement(By.xpath("//*[@id='search-term']")).sendKeys("Math K-5: Count On To Add");
		Chrome_Driver.findElement(By.xpath("//*[@id='search-term']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		Assert.assertEquals("Sorry, there weren't any results that matched \"Math K-5: Count On To Add\".", Chrome_Driver.findElement(By.xpath("//*[@id='no-results']/h4")).getText());
		Chrome_Driver.findElement(By.xpath("//*[@id='reset-search']")).click();
		Thread.sleep(2000);
		
		// Search for the added Metadata Title and validate the results and click to go the Summary page.
		Chrome_Driver.findElement(By.xpath("//*[@id='clo_title']")).clear();
		Chrome_Driver.findElement(By.xpath("//*[@id='clo_title']")).sendKeys("CLOP-1996");
		Chrome_Driver.findElement(By.xpath("//*[@type='submit']")).click();
		Thread.sleep(2000);
		Assert.assertEquals("CLOP-1996", Chrome_Driver.findElement(By.xpath("//*[@class='match']")).getText());
		Chrome_Driver.findElement(By.xpath("//*[@class='match']")).click();
		Thread.sleep(5000);
		
		// Validate that the Metadata Title appears in the Summary page.
		Assert.assertEquals("CLOP-1996", Chrome_Driver.findElement(By.xpath("//*[@id='title-holder']/h2")).getText());
		
		// Remove the added Metadata Title value, logout and exit.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(8000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Title")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		mddbPage.logout(); 
		
	}

}