package Sprint24;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import pages.MetadataDashboardPage;

public class CLOP_1986 {

	@Test
	public void CLOP_1986_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
				
		// Launch the Metadata Dashboard and Login as a Program Manager.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);		
        MetadataDashboardPage mddbPage = new MetadataDashboardPage(Chrome_Driver);
        mddbPage.login("qa_test", "Password1");
		Chrome_Driver.manage().window().maximize();
		Thread.sleep(3000);
		
		// Navigate to the Math K-5: Count On page that does not have a Learning Objective.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/taggingtool/chapter/sn_2cb/5c4b38804700499c8638916ebf4adeca");
		Thread.sleep(10000);
		
		// Add a Copyright Holder and validate the initial set values.
		Chrome_Driver.findElement(By.xpath("//*[@id='addeditmetadata']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='s2id_metadatakey']/a/span[2]/b")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("Copyright Holder");
		Chrome_Driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);	
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@name='copyrightholderfirstname']")).sendKeys("CLOP-1986 First");
		Chrome_Driver.findElement(By.xpath("//*[@name='copyrightholdermiddlename']")).sendKeys("CLOP-1986 Middle");
		Chrome_Driver.findElement(By.xpath("//*[@name='copyrightholderlastname']")).sendKeys("CLOP-1986 Last");
		Chrome_Driver.findElement(By.xpath("//*[@name='copyrightholderorganization']")).sendKeys("CLOP-1986 Organization");
		Select mydrpdwn = new Select(Chrome_Driver.findElement(By.xpath("//*[@name='copyrightyear']")));
		mydrpdwn.selectByVisibleText("2011");
		Chrome_Driver.findElement(By.xpath("//*[@id='addsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(2000);
		int rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();		
			if (sValue.equalsIgnoreCase("Copyright Holder First Name")) {
				Assert.assertEquals("CLOP-1986 First", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
			}
			if (sValue.equalsIgnoreCase("Copyright Holder Middle Name")) {
				Assert.assertEquals("CLOP-1986 Middle", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
			}
			if (sValue.equalsIgnoreCase("Copyright Holder Last Name")) {
				Assert.assertEquals("CLOP-1986 Last", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
			}
			if (sValue.equalsIgnoreCase("Copyright Organization")) {
				Assert.assertEquals("CLOP-1986 Organization", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
			}
			if (sValue.equalsIgnoreCase("Copyright Year")) {
				Assert.assertEquals("2011", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
			}
	    }
		
		// Edit the Copyright Holder values and validate the new names, organization, and year values.
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();		
			if (sValue.equalsIgnoreCase("Copyright Holder First Name")) {
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[1]")).click();
				break;
			}
	    }
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@name='copyrightholderfirstname']")).clear();
		Chrome_Driver.findElement(By.xpath("//*[@name='copyrightholdermiddlename']")).clear();
		Chrome_Driver.findElement(By.xpath("//*[@name='copyrightholderlastname']")).clear();
		Chrome_Driver.findElement(By.xpath("//*[@name='copyrightholderorganization']")).clear();
		Chrome_Driver.findElement(By.xpath("//*[@name='copyrightholderfirstname']")).sendKeys("CLOP-1986 First New");
		Chrome_Driver.findElement(By.xpath("//*[@name='copyrightholdermiddlename']")).sendKeys("CLOP-1986 Middle New");
		Chrome_Driver.findElement(By.xpath("//*[@name='copyrightholderlastname']")).sendKeys("CLOP-1986 Last New");
		Chrome_Driver.findElement(By.xpath("//*[@name='copyrightholderorganization']")).sendKeys("CLOP-1986 Organization New");
		mydrpdwn = new Select(Chrome_Driver.findElement(By.xpath("//*[@name='copyrightyear']")));
		mydrpdwn.selectByVisibleText("2014");
		Chrome_Driver.findElement(By.xpath("//*[@id='editsubmitbutton']")).click();
		Thread.sleep(2000);
		Chrome_Driver.findElement(By.xpath("//*[@id='viewdeletemetadata']")).click();
		Thread.sleep(2000);		
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++) {
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();		
			if (sValue.equalsIgnoreCase("Copyright Holder First Name")) {
				Assert.assertEquals("CLOP-1986 First New", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
			}
			if (sValue.equalsIgnoreCase("Copyright Holder Middle Name")) {
				Assert.assertEquals("CLOP-1986 Middle New", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
			}
			if (sValue.equalsIgnoreCase("Copyright Holder Last Name")) {
				Assert.assertEquals("CLOP-1986 Last New", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
			}
			if (sValue.equalsIgnoreCase("Copyright Organization")) {
				Assert.assertEquals("CLOP-1986 Organization New", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
			}
			if (sValue.equalsIgnoreCase("Copyright Year")) {
				Assert.assertEquals("2014", Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[2]")).getText());
			}
	    }
		
		// Remove the Copyright Holder values, logout, and exit.
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Copyright Holder First Name")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Copyright Holder Middle Name")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Copyright Holder Last Name")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		rowCount=Chrome_Driver.findElements(By.xpath("//*[@id='metadatatable']/tbody/tr")).size();
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Copyright Organization")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		for (int i=1;i<=rowCount;i++){
			String sValue = null;
			sValue = Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[1]")).getText();
			if(sValue.equalsIgnoreCase("Copyright Year")){
				Chrome_Driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/div/span[starts-with(@class,'deletebutton')]")).click();
				Thread.sleep(1000);
				break;
	        }
		}
		mddbPage.logout();
	
	}

}