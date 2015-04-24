package Sprint27;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_2308 {

	@Test
	public void CLOP_2308_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "src\\pages\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		
		// Click and validate the contents of the MOST RECENT PROJECTS more... link.
		Chrome_Driver.findElement(By.xpath("//*[@href='/users/project']")).click();
		Thread.sleep(3000);	
		Assert.assertEquals("MY MOST RECENT PROJECT", Chrome_Driver.findElement(By.xpath("//*[@class='container']/h2")).getText());
		List<WebElement> clolist=Chrome_Driver.findElements(By.xpath("//*[@class='application-clo-listing']/div"));
		int projectlistsize = clolist.size();          
		if (projectlistsize >= 20) {
			Assert.assertEquals(20, projectlistsize);
		}
		List<WebElement> clos=Chrome_Driver.findElements(By.xpath("//*[@class='application-clo-listing']/div/div[2]/p"));
		for  (WebElement eachResult : clos) {
		    String value = eachResult.getText();
			Assert.assertTrue(value.contains("Last Accessed"));
		}
		List<WebElement> habitatlink=Chrome_Driver.findElements(By.xpath("//*[@class='application-clo-launch']/div/div[1]/div/a"));
		for  (WebElement eachResult : habitatlink) {
		    String value = eachResult.getAttribute("href");
		    Assert.assertTrue(value.contains("https://habitat.inkling.com/project/"));
		}
		List<WebElement> ttlink=Chrome_Driver.findElements(By.xpath("//*[@class='application-clo-launch']/div/div[2]/div/a"));
		for  (WebElement eachResult : ttlink) {
		    String value = eachResult.getAttribute("href");
			Assert.assertTrue(value.contains("https://metadatadev.clo.edmesh.com/taggingtool/chapter"));
		}
		
		// Determine the number total projects.
		List<WebElement> project=Chrome_Driver.findElements(By.xpath("//*[@class='pagination']/li"));
		Integer totalpages = 1;
		if (project.size() > 1 ) {  
			totalpages = project.size() - 1;
		}
		Chrome_Driver.findElement(By.xpath("//*[@class='pagination']/li["+totalpages+"]/a")).click();
		Thread.sleep(5000);
		clolist=Chrome_Driver.findElements(By.xpath("//*[@class='application-clo-listing']/div"));
		Integer lastprojectlistsize = clolist.size(); 
		Integer totalprojectssize;
		totalprojectssize = lastprojectlistsize;
		if (project.size() > 1 ) { 
			totalpages--;
			totalprojectssize = ((totalpages*20) + lastprojectlistsize);
		}
		
		// Click on the fifth Content Browser page, then navigate back to the Homepage and validate that the latest Project has been updated.
		Chrome_Driver.findElement(By.xpath("//*[@class='pagination']/li[1]/a")).click();
		Thread.sleep(5000);
		Chrome_Driver.findElement(By.xpath("//*[@class='application-clo-listing']/div[5]/div/div[2]/div/div/div[2]/div/a/p")).click();
		Thread.sleep(8000);	
		Assert.assertEquals("Content Browser | Metadata Dashboard", Chrome_Driver.getTitle());
		String projecttitle = Chrome_Driver.findElement(By.xpath("//*[@class='title']")).getText();
		Chrome_Driver.findElement(By.xpath("//*[@id='mainlogo']")).click();
		Thread.sleep(3000);
		Assert.assertEquals(projecttitle, Chrome_Driver.findElement(By.xpath("//*[@id='application-clo-projects']/ol/li[1]/a")).getText());
		
		// Validate that the total number of projects reported matches the collected number of reports.
		Integer reportedprojectsize = Integer.parseInt(Chrome_Driver.findElement(By.xpath("//*[@id='application-clo-projects-count']")).getText());
		Assert.assertTrue(totalprojectssize.equals(reportedprojectsize));
		
		// Logout, and exit.
		mddbPage.logout();	
		
	}

}