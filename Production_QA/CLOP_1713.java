package Production_QA;

import org.junit.Assert;
import pages.MetadataDashboardPage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CLOP_1713 {

	@Test
	public void CLOP_1713_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard .
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		
		// Navigate to a CLO Project that has a very long Title
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_5353/94bf6f5f043a46d091ec757feb3822df");
		Thread.sleep(5000);

		// Validate that the CLO titles are displayed properly and a mouseover shows a tool tip.
		WebElement menu = Chrome_Driver.findElement(By.xpath("//*[@id='tag-page-name-text']"));
		Actions builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		Assert.assertEquals("CLO with long name for ...", Chrome_Driver.findElement(By.xpath("//*[@id='tag-page-name-text']")).getText());
		Assert.assertEquals("CLO  with long name for  Title display in Project browser", Chrome_Driver.findElement(By.xpath("//*[@id='tag-page-name-text']")).getAttribute("data-original-title"));
		Assert.assertEquals("22px", Chrome_Driver.findElement(By.xpath("//*[@id='tag-page-name-text']")).getCssValue("font-size"));
		menu = Chrome_Driver.findElement(By.xpath("//*[@id='tag-project-name-text']"));
		builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		Assert.assertEquals("Project: CLO SEG Project for QA (2014-11-04)", Chrome_Driver.findElement(By.xpath("//*[@id='tag-page-name']/a[3]")).getText());
		Assert.assertEquals("CLO SEG Project for QA (2014-11-04)", Chrome_Driver.findElement(By.xpath("//*[@id='tag-page-name']/a[3]")).getAttribute("data-original-title"));
		Assert.assertEquals("14px", Chrome_Driver.findElement(By.xpath("//*[@id='tag-page-name']/a[3]")).getCssValue("font-size"));

		// Validate that clicking the title will take the user back to the All Project page with the original project selected
		Thread.sleep(5000);
		Chrome_Driver.findElement(By.xpath("//*[@id='tag-page-name']/a[3]")).click();
		Thread.sleep(5000);
		Assert.assertEquals("All Projects", Chrome_Driver.findElement(By.xpath("//*[@id='project-list']/h3")).getText());
		Assert.assertEquals("CLO SEG Project for QA (2014-11-04)", Chrome_Driver.findElement(By.xpath("//*[@id='project-overview']/h3/span[1]")).getText());

		// Logout and exit.
		mddbPage.logout();
		
	}

}