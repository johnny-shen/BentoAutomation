package Production_QA;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.MetadataDashboardPage;

public class CLOP_1587 {

	@Test
	public void CLOP_1587_Test() throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 

		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();

		// Navigate to the CLO Parallax Project to test the individual taggable assets.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_2cb/29c60bdeb1184aa5a23e4c090f77a358");
		Thread.sleep(10000);
		
		// Validate that in the Concept page there are a list of assets.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/a")).click();
		Thread.sleep(5000);
		
		// Validate that in the Concept -> Paragraph page there are two Warnings and a Paragraph section.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[2]/a")).click();
		Thread.sleep(5000);		
		Assert.assertEquals("Paragraph", Chrome_Driver.findElement(By.xpath("//*[@id='content-title-area']")).getText());
		Assert.assertEquals("This goes with the entire page", Chrome_Driver.findElement(By.xpath("//*[@id='content-preview']/div")).getText());
		Assert.assertEquals("2", Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]/span[1]")).getText());
		Assert.assertEquals("Add New Metadata", Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).getText());
		
		// Validate that in the Concept -> Table page has sections a table section.
		Chrome_Driver.findElement(By.xpath(".//*[@id='chapterHierarchy']/ul/li/ul/li[1]/ul/li[4]/a")).click();
		Thread.sleep(5000);		
		Assert.assertEquals("Table", Chrome_Driver.findElement(By.xpath("//*[@id='content-title-area']")).getText());
		Assert.assertEquals("Water Effect01", Chrome_Driver.findElement(By.xpath("//*[@id='content-preview']/div/table/tbody/tr/td[1]/figure/figcaption/span/span")).getText());
		Assert.assertEquals("Water Effect02", Chrome_Driver.findElement(By.xpath("//*[@id='content-preview']/div/table/tbody/tr/td[2]/figure/figcaption/span")).getText());
		Assert.assertEquals("Add New Metadata", Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).getText());
		
		// Validate that in Model -> Widget the Widget is displayed and it can be tagged.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/a")).click();
		Thread.sleep(5000);
		Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_filter']/label/input")).click();
		Thread.sleep(5000);
		Chrome_Driver.findElement(By.xpath("//*[@data-name='Model']")).click();
		Thread.sleep(7000);	
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[2]/ul/li[4]/a")).click();
		Thread.sleep(5000);	
		Chrome_Driver.switchTo().frame(Chrome_Driver.findElementByCssSelector("#content-preview iframe"));
		Assert.assertEquals("Cras mattis consectetur purus sit amet fermentum. Vestibulum id ligula porta felis euismod semper?", Chrome_Driver.findElement(By.xpath("//html/body/div/div[1]/p")).getText());
		Chrome_Driver.switchTo().defaultContent();
		Assert.assertEquals("Add New Metadata", Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).getText());

		// Validate that in Practice -> Widget, a Title can be added and then removed.
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/a")).click();
		Thread.sleep(5000);
		Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_filter']/label/input")).click();
		Thread.sleep(5000);
		Chrome_Driver.findElement(By.xpath("//*[@data-name='Practice']")).click();
		Thread.sleep(5000);	
		Chrome_Driver.findElement(By.xpath("//*[@id='chapterHierarchy']/ul/li/ul/li[3]/ul/li[7]/a")).click();
		Thread.sleep(5000);	
		String widgettitle = null;
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@id='select2-drop']/ul/li/ul/li/div"));
		for  (WebElement eachResult : AllSearchResults) {
		    String match = "Title";
		    String value = eachResult.getText();
		    if (value.equals(match)) {       
		        eachResult.click();
		        break ;
		    }
		}
		Chrome_Driver.findElement(By.xpath("//*[@id='metadatavalue']")).sendKeys("CLOP-1587 Title");
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText();
			if(sValue.equalsIgnoreCase("CLOP-1587 Title")){
				widgettitle = "true";
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[2]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		Assert.assertEquals("true", widgettitle);
		mddbPage.logout();
		
	}	
		
}