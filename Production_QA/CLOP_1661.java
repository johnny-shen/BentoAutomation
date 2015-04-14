package Production_QA;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.MetadataDashboardPage;

public class CLOP_1661 {

	@Test
	public void CLOP_1661_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/");
		Thread.sleep(3000);
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		
		// Navigate to the Math K-5: Count On page that does not have a Learning Objective.
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		
		// Validate that the Warnings button color is red and the Warnings list is pink.
		Assert.assertEquals("rgba(226, 27, 36, 1)", Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).getCssValue("background-color"));
		Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).click();
		Thread.sleep(1000);
		Assert.assertEquals("rgba(221, 187, 187, 1)", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_wrapper']/div[2]/div/div[2]/a[1]")).getCssValue("background-color"));
		
		// Navigate to the CLO Summary Page page that does not have any Warnings
		Chrome_Driver.get("https://metadatadev.cloqa.edmesh.com/taggingtool/chapter/sn_5353/9ac201ec75f54f27a6d30b8cab1faccb");
		Thread.sleep(10000);
		
		// Validate that the Warnings button is transparent and the Add Metadata, Review Metadata, and page button are all blue.
		Assert.assertEquals("rgba(255, 255, 255, 1)", Chrome_Driver.findElement(By.xpath("//*[@id='addMetadataAndWarning']/button[2]")).getCssValue("background-color"));
		Assert.assertEquals("rgba(51, 122, 183, 1)", Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).getCssValue("background-color"));
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Assert.assertEquals("rgba(51, 122, 183, 1)", Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).getCssValue("background-color"));
		Assert.assertEquals("rgba(51, 122, 183, 1)", Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).getCssValue("background-color"));
		Assert.assertEquals("rgba(51, 122, 183, 1)", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable_paginate']/ul/li[2]/a")).getCssValue("background-color"));
		
		// Logout and exit.
		mddbPage.logout();	
		
	}

}