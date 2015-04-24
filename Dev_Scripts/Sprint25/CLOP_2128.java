package Sprint25;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_2128 {

	@Test
	public void CLOP_2128_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "src\\pages\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(3000);
		
		// Validate the new Bento message and logo as well as the Course header and message in the homepage.
		Assert.assertEquals("bento", Chrome_Driver.findElement(By.xpath("//*[@class='row application-banner application-banner-bento']/h2")).getText());
		Assert.assertEquals("bento\nBento is an open and extensible internal MHE toolset. It allows you to manage, tag, and connect to your digital content. Through Bento, you can create CLOs, access Habitat projects, tag assets, and access the course building tools. Bento is your home base for modular content creation.", Chrome_Driver.findElement(By.xpath("//*[@class='row application-banner application-banner-bento']")).getText());
		Assert.assertEquals("https://metadatadev.clo.edmesh.com/img/registration/bento-logo.png", Chrome_Driver.findElement(By.xpath("//*[@class='row application-banner application-banner-bento']/img")).getAttribute("src"));
		Assert.assertEquals("COURSE", Chrome_Driver.findElement(By.xpath("//*[@class='row application-banner']/h2")).getText());
		Assert.assertEquals("COURSE\nThe course tools allow you to curate and assemble your modular content into courses for our customers. You can pull content from repositories, upload your own content, add connecting text, and define and customize your course structure. Course is the gateway to the DLE.\nOpen Course", Chrome_Driver.findElement(By.xpath("//*[@class='row application-banner']")).getText());
		Assert.assertEquals("Open Course", Chrome_Driver.findElement(By.xpath("//*[@class='btn btn-default application-button']")).getText());
		
		// Validate the two header links.
		Assert.assertEquals("CLO", Chrome_Driver.findElement(By.linkText("CLO")).getText());
		Assert.assertEquals("COURSE", Chrome_Driver.findElement(By.linkText("COURSE")).getText());
	
		// Validate the My Most Recent CLOs list.
		Assert.assertEquals("MY MOST RECENT CLOS more...", Chrome_Driver.findElement(By.xpath("//*[@id='clo-section']/div/div[1]/h3")).getText());		
		Assert.assertEquals(10, Chrome_Driver.findElements(By.xpath("//*[@id='application-clo-container']/ol/li")).size());
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@id='clo-section']/div[1]/div")).getText().contains("Published"));	
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@id='clo-section']/div[1]/div")).getText().contains("Updated"));
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@id='clo-section']/div[1]/div")).getText().contains("Private"));
		
		// Validate the Circle report with total CLOs and warnings.
		Assert.assertEquals("250", Chrome_Driver.findElement(By.xpath("//*[@id='clo-report']")).getAttribute("height"));	
		Assert.assertEquals("250", Chrome_Driver.findElement(By.xpath("//*[@id='clo-report']")).getAttribute("width"));	
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-reports-graph-total']")).getText().contains("Total CLOs:"));
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-reports-graph-partial']")).getText().contains("CLOs with warnings:"));
		
		// Validate the Most Recent Projects list.
		Assert.assertEquals("MOST RECENT PROJECTS more...", Chrome_Driver.findElement(By.xpath("//*[@id='clo-section']/div/div[3]/h3")).getText());		
		Assert.assertEquals(10, Chrome_Driver.findElements(By.xpath("//*[@id='application-clo-projects']/ol/li")).size());
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@id='clo-section']/div/div[3]")).getText().contains("Total Projects"));	

/*		// Validate the My Most Recent Lessons list.   -------Lessons removed for now----will replace once it's defined.
		Assert.assertEquals("MY MOST RECENT LESSONS more...", Chrome_Driver.findElement(By.xpath("//*[@id='course-section']/div/div[1]/h3")).getText());		
		Assert.assertEquals(10, Chrome_Driver.findElements(By.xpath("//*[@id='course-section']/div/div[1]/ol/li")).size());
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-report-counts']")).getText().contains("Published"));	
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-report-counts']")).getText().contains("Updated"));
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@class='application-report-counts']")).getText().contains("Private"));
		
		// Validate the Circle report with total Lessons and warnings.
		Assert.assertEquals("250", Chrome_Driver.findElement(By.xpath("//*[@id='course-report']")).getAttribute("height"));	
		Assert.assertEquals("250", Chrome_Driver.findElement(By.xpath("//*[@id='course-report']")).getAttribute("width"));	
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@id='course-section']/div/div[2]/div[2]")).getText().contains("Total Lessons:"));
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@id='course-section']/div/div[2]/div[3]")).getText().contains("Lessons with warnings:"));
		
		// Validate the My Most Recent Courses list.
		Assert.assertEquals("MY MOST RECENT COURSES more...", Chrome_Driver.findElement(By.xpath("//*[@id='course-section']/div/div[3]/h3")).getText());		
		Assert.assertEquals(10, Chrome_Driver.findElements(By.xpath("//*[@id='course-section']/div/div[3]/ol/li")).size());
		Assert.assertTrue(Chrome_Driver.findElement(By.xpath("//*[@id='course-section']/div/div[3]")).getText().contains("Total Projects"));	
*/
		// Validate the buttons on the Metadata Dashboard.
		String IH = null;
		String LO = null;
		String AB = null;
		String CB = null;
		List<WebElement> AllSearchResults=Chrome_Driver.findElements(By.xpath("//*[@class='application-tool-links']/div/a"));	
		for  (WebElement eachResult : AllSearchResults) {
			String sValue = null;
			sValue = eachResult.getAttribute("href");	
			if(sValue.equalsIgnoreCase("https://habitat.inkling.com/")){
				IH = "true";
	        }
			if(sValue.equalsIgnoreCase("https://metadatadev.clo.edmesh.com/learningobjectives/")){
				LO = "true";
	        }
			if(sValue.equalsIgnoreCase("http://academicbenchmarks.com/")){
				AB = "true";
	        }			
			if(sValue.equalsIgnoreCase("https://metadatadev.clo.edmesh.com/taggingtool/")){
				CB = "true";
	        }
		}
		Assert.assertEquals("true", IH);
		Assert.assertEquals("true", LO);
		Assert.assertEquals("true", AB);
		Assert.assertEquals("true", CB);
	
		// Logout and exit.
		mddbPage.logout();	
		
	}

}