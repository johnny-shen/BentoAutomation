package Sprint24;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MetadataDashboardPage;

public class CLOP_1992 {

	@Test
	public void CLOP_1992_Test() throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login and set some test values.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);
		MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		String compliance = "Compliance";
		String clocompliance508 = "508 Compliant";
		String clocomplianceADA = "ADA Compliant";
		String clocomplianceWCAG = "WCAG Compliant";
		String allcompliant = "508 Compliant\nWCAG Compliant";
		String clocompliant508 = "CLO 508 Compliant is included";
		String clocompliantADA = "CLO ADA Compliant is included";
		String clocompliantWCAG = "CLO WCAG Compliant is included";
		String passed = "Requirement verified";
		String icon = "editbutton icon-large align-center";
		String comp508 = null;
		String compada = null;
		String compwcag = null;

		// Add all three Compliances in Overview and validate they appear on the Metadata table along with their edit icons.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);		
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Compliance");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);	
		Chrome_Driver.findElement(By.xpath("//*[@name='508compliant']")).click();
		Chrome_Driver.findElement(By.xpath("//*[@name='adacompliant']")).click();
		Chrome_Driver.findElement(By.xpath("//*[@name='wcagcompliant']")).click();
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText();						
			if(sValue.equalsIgnoreCase(clocompliance508)){
				Assert.assertEquals(compliance, Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText());
				Assert.assertEquals(icon, Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[1]")).getAttribute("class"));
				comp508 = "true";
	        }
			if(sValue.equalsIgnoreCase(clocomplianceADA)){
				Assert.assertEquals(compliance, Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText());
				Assert.assertEquals(icon, Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[1]")).getAttribute("class"));
				compada = "true";
	        }
			if(sValue.equalsIgnoreCase(clocomplianceWCAG)){
				Assert.assertEquals(compliance, Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText());
				Assert.assertEquals(icon, Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[1]")).getAttribute("class"));
				compwcag = "true";
	        }
		}                                                              
		Assert.assertEquals("true", comp508);
		Assert.assertEquals("true", compada);
		Assert.assertEquals("true", compwcag);
		Thread.sleep(2000);
		
		// Remove the ADA Compliant through Edit and validate the updates.
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText();
			if(sValue.equalsIgnoreCase(clocomplianceADA)){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'editbutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		Chrome_Driver.findElement(By.xpath("//*[@name='adacompliant']")).click();
		Chrome_Driver.findElement(By.xpath("//*[@id='editsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);
		comp508 = null;
		compwcag = null;
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText();			
			Assert.assertFalse(sValue.equalsIgnoreCase(clocomplianceADA));
			if(sValue.equalsIgnoreCase(clocompliance508)){
				Assert.assertEquals(compliance, Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText());
				Assert.assertEquals(icon, Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[1]")).getAttribute("class"));
				comp508 = "true";
	        }
			if(sValue.equalsIgnoreCase(clocomplianceWCAG)){
				Assert.assertEquals(compliance, Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText());
				Assert.assertEquals(icon, Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[1]")).getAttribute("class"));
				compwcag = "true";
	        }
		}
		Assert.assertEquals("true", comp508);
		Assert.assertEquals("true", compwcag);
		
		// Navigate to the Summary page and validate that there are they are displayed.
		Chrome_Driver.findElement(By.xpath("//*[@id='to_summary']")).click();
		Thread.sleep(3000);		
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[1]/div/div[2]/a")).click();
		Thread.sleep(1000);
		Chrome_Driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div[2]/div[3]/div/div[2]/a")).click();
		Thread.sleep(1000);
		String compliantall = null;
		comp508 = null;
		compada = null;
		compwcag = null;
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadata-table']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadata-table']/tbody/tr["+i+"]/td[1]")).getText();						
			if(sValue.equalsIgnoreCase(compliance)){
				Assert.assertEquals(allcompliant, Chrome_Driver.findElement(By.xpath("//*[@id='metadata-table']/tbody/tr["+i+"]/td[2]")).getText());
				compliantall = "true";
	        }
		}
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='verified-table']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[1]")).getText();						
			if(sValue.equalsIgnoreCase(clocompliant508)){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				comp508 = "true";
	        }
			if(sValue.equalsIgnoreCase(clocompliantADA)){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				compada = "true";
	        }
			if(sValue.equalsIgnoreCase(clocompliantWCAG)){
				Assert.assertEquals(passed, Chrome_Driver.findElement(By.xpath("//*[@id='verified-table']/tbody/tr["+i+"]/td[2]")).getText());
				compwcag = "true";
	        }
		}
		Assert.assertEquals("true", comp508);
		Assert.assertEquals("true", compada);
		Assert.assertEquals("true", compwcag);
		Assert.assertEquals("true", compliantall);	
		
		// Navigate back to the Tagging Tool Overview, go to Concept, add a 508 Compliant and validate various features, the remove 508 Compliant.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		Chrome_Driver.findElement(By.xpath("//*[@data-name='Concept']")).click();
		Thread.sleep(3000);
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);		
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Compliance");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);	
		Chrome_Driver.findElement(By.xpath("//*[@name='508compliant']")).click();
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(3000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText();
			if(sValue.equalsIgnoreCase(clocompliance508)){
				Assert.assertEquals(compliance, Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText());
				Assert.assertEquals(icon, Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[1]")).getAttribute("class"));
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'editbutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		Assert.assertEquals("true", Chrome_Driver.findElement(By.cssSelector("#inputfields.col-sm-6 div.form-group div.subform-group div.checkbox label input[name='508compliant']")).getAttribute("checked"));
		Assert.assertEquals(null, Chrome_Driver.findElement(By.cssSelector("#inputfields.col-sm-6 div.form-group div.subform-group div.checkbox label input[name='adacompliant']")).getAttribute("checked"));
		Assert.assertEquals(null, Chrome_Driver.findElement(By.cssSelector("#inputfields.col-sm-6 div.form-group div.subform-group div.checkbox label input[name='wcagcompliant']")).getAttribute("checked"));
		Chrome_Driver.findElement(By.xpath("//*[@name='508compliant']")).click();
		Chrome_Driver.findElement(By.xpath("//*[@id='editsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(2000);
	
		// Navigate back to the Overview, remove both remaining Compliance values, logout and exit.
		Chrome_Driver.findElement(By.xpath("//*[@data-name='Overview']")).click();
		Thread.sleep(3000);
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText();
			if(sValue.equalsIgnoreCase(clocompliance508)){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText();
			if(sValue.equalsIgnoreCase(clocomplianceWCAG)){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		
		mddbPage.logout();	
		
	}

}