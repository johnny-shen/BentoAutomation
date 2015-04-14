package Production_QA;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1497 {

	@Test
	public void CLOP_1497_Test() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
		
		// Launch the Metadata Dashboard and Login.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		
		// Navigate to the Math K-5: Count On page that does not have a Learning Objective.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(10000);
		
		// Validate the general appearance and position of the various features underneath the Overview title.
		Assert.assertEquals("Add New Metadata", Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).getText());
		Assert.assertEquals("0px", Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).getCssValue("margin-bottom"));
		Assert.assertEquals("0px", Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).getCssValue("margin-left"));
		Assert.assertEquals("0px", Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).getCssValue("margin-right"));
		Assert.assertEquals("0px", Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).getCssValue("margin-top"));
		
		Assert.assertEquals("btn btn-warning btn-sm warnings-button", Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).getAttribute("class"));
		Assert.assertEquals("0px", Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).getCssValue("margin-bottom"));
		Assert.assertEquals("0px", Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).getCssValue("margin-left"));
		Assert.assertEquals("15px", Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).getCssValue("margin-right"));
		Assert.assertEquals("30px", Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).getCssValue("margin-top"));
		
		Assert.assertEquals("Entries - Found 2 results:\n10 per page\n25 per page\n50 per page\n100 per page", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_length']/label")).getText());
		Assert.assertEquals("5px", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_length']/label")).getCssValue("margin-bottom"));
		Assert.assertEquals("15px", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_length']/label")).getCssValue("padding-right"));
		
		Assert.assertEquals("Search", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_filter']/label/input")).getAttribute("placeholder"));
		Assert.assertEquals("0px", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_filter']/label/input")).getCssValue("margin-bottom"));
		Assert.assertEquals("10px", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_filter']/label/input")).getCssValue("margin-left"));
		Assert.assertEquals("0px", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_filter']/label/input")).getCssValue("margin-right"));
		Assert.assertEquals("0px", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_filter']/label/input")).getCssValue("margin-top"));
		Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_filter']/label/input")).sendKeys("CLOP-1497");
		Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_filter']/label/input")).sendKeys(Keys.ENTER);	
		Assert.assertEquals("CLOP-1497", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_filter']/label/input")).getAttribute("value"));
		Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_filter']/label/input")).clear();
		Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_filter']/label/input")).sendKeys(Keys.ENTER);	
		Thread.sleep(3000);
		
		// Validate the font, color, and background of the Warning list.
		Assert.assertEquals("btn btn-warning btn-sm warnings-button", Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).getAttribute("class"));
		Assert.assertEquals("", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[1]/legend")).getText());
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(1000);
		Assert.assertEquals("btn btn-warning btn-sm warnings-button dropup", Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).getAttribute("class"));
		Assert.assertEquals("Required to publish", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[1]/legend")).getText());
		Assert.assertEquals("rgba(221, 187, 187, 1)", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[1]")).getCssValue("background-color"));
		Assert.assertEquals("rgba(51, 51, 51, 1)", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[1]/div/h4")).getCssValue("color"));
		Assert.assertEquals("ProximaNova", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[1]/div/h4")).getCssValue("font-family"));
		Assert.assertEquals("16px", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[1]/div/h4")).getCssValue("font-size"));
		Assert.assertEquals("rgba(153, 51, 51, 1)", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[1]/p")).getCssValue("color"));
		Assert.assertEquals("ProximaNova", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[1]/p")).getCssValue("font-family"));
		Assert.assertEquals("14px", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[1]/p")).getCssValue("font-size"));
		
		// Validate the warning list is still present when Add New Metadata button is pressed.
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(3000);
		Assert.assertEquals("btn btn-warning btn-sm warnings-button dropup", Chrome_Driver.findElement(By.xpath("//*[@id='warningSummary']/button")).getAttribute("class"));
		Assert.assertEquals("Required to publish", Chrome_Driver.findElement(By.xpath("//*[@id='addeditModal']/div/div/div[1]/div[1]/div/div[1]/legend")).getText());
		Assert.assertEquals("rgba(221, 187, 187, 1)", Chrome_Driver.findElement(By.xpath("//*[@id='addeditModal']/div/div/div[1]/div[1]/div/div[2]/a[1]")).getCssValue("background-color"));
		Assert.assertEquals("rgba(51, 51, 51, 1)", Chrome_Driver.findElement(By.xpath("//*[@id='addeditModal']/div/div/div[1]/div[1]/div/div[2]/a[1]/div/h4")).getCssValue("color"));
		Assert.assertEquals("ProximaNova", Chrome_Driver.findElement(By.xpath("//*[@id='addeditModal']/div/div/div[1]/div[1]/div/div[2]/a[1]/div/h4")).getCssValue("font-family"));
		Assert.assertEquals("16px", Chrome_Driver.findElement(By.xpath("//*[@id='addeditModal']/div/div/div[1]/div[1]/div/div[2]/a[1]/div/h4")).getCssValue("font-size"));
		Assert.assertEquals("rgba(153, 51, 51, 1)", Chrome_Driver.findElement(By.xpath("//*[@id='addeditModal']/div/div/div[1]/div[1]/div/div[2]/a[1]/p")).getCssValue("color"));
		Assert.assertEquals("ProximaNova", Chrome_Driver.findElement(By.xpath("//*[@id='addeditModal']/div/div/div[1]/div[1]/div/div[2]/a[1]/p")).getCssValue("font-family"));
		Assert.assertEquals("14px", Chrome_Driver.findElement(By.xpath("//*[@id='addeditModal']/div/div/div[1]/div[1]/div/div[2]/a[1]/p")).getCssValue("font-size"));
			
		// Close and collapse the warning list.
		Chrome_Driver.findElement(By.xpath("//*[@id='warningSummary']/button")).click();
		Thread.sleep(1000);
		Assert.assertEquals("btn btn-warning btn-sm warnings-button", Chrome_Driver.findElement(By.xpath("//*[@id='warningSummary']/button")).getAttribute("class"));
		Assert.assertEquals("", Chrome_Driver.findElement(By.xpath("//*[@id='addeditModal']/div/div/div[1]/div[1]/div/div[1]/legend")).getText());
		
		// Click on and validate the image in the Concept page.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(3000);
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[2]/a")).click();
		Thread.sleep(3000);
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@id='content-preview']/img")).getAttribute("src").contains("https://partner.inkling.com/rawfiles/sn_2cb/head/img/chapter01/groupduckspondillustrationwhitebackground33098635.jpg?access_token"));
		Assert.assertEquals("0px", Chrome_Driver.findElement(By.xpath("//*[@id='content-preview']/img")).getCssValue("margin-top"));
		Assert.assertEquals("0px", Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).getCssValue("margin-bottom"));
		Assert.assertEquals("6px", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']")).getCssValue("margin-bottom"));
		
		// Logout and exit
		mddbPage.logout();
		
	}

}
