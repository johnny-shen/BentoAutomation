package Sprint22;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class CLOP_1134 {

	@Test
	public void CLOP_1134_Test() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
		
		// Launch the Metadata Dashboard and go to the Sign Up page.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);
		Chrome_Driver.manage().window().maximize();
		
		// Validate the Sign Up Page fields and enter some text.
		Assert.assertEquals("Sign Up", Chrome_Driver.findElement(By.xpath("//*[@id='signup-signin']/a")).getText());
		Chrome_Driver.findElement(By.xpath("//*[@class='sign-up']")).click();
		Thread.sleep(2000);
		Assert.assertEquals("Name", Chrome_Driver.findElement(By.xpath("//*[@for='firstname']")).getText());
		Assert.assertEquals("First Name", Chrome_Driver.findElement(By.xpath("//*[@id='firstname']")).getAttribute("placeholder"));
		Chrome_Driver.findElement(By.xpath("//*[@id='firstname']")).sendKeys("CLOP-1134-First");
		Assert.assertEquals("Last Name", Chrome_Driver.findElement(By.xpath("//*[@id='lastname']")).getAttribute("placeholder"));
		Chrome_Driver.findElement(By.xpath("//*[@id='lastname']")).sendKeys("CLOP-1134-Last");
		Assert.assertEquals("Organization", Chrome_Driver.findElement(By.xpath("//*[@for='organization']")).getText());	
		Assert.assertEquals("McGraw Hill Education", Chrome_Driver.findElement(By.xpath("//*[@name='data[User][organization]']")).getAttribute("placeholder"));
		Chrome_Driver.findElement(By.xpath("//*[@name='data[User][organization]']")).sendKeys("CLOP-1134-MHE");
		Assert.assertEquals("Phone", Chrome_Driver.findElement(By.xpath("//*[@for='phone']")).getText());
		Assert.assertEquals("(XXX) XXX-XXXX", Chrome_Driver.findElement(By.xpath("//*[@id='phone']")).getAttribute("placeholder"));
		Chrome_Driver.findElement(By.xpath("//*[@id='phone']")).sendKeys("776-287-913");
		Assert.assertEquals("Email", Chrome_Driver.findElement(By.xpath("//*[@for='email']")).getText());
		Assert.assertEquals("jane.smith@mheducation.com", Chrome_Driver.findElement(By.xpath("//*[@id='email']")).getAttribute("placeholder"));
		Chrome_Driver.findElement(By.xpath("//*[@id='email']")).sendKeys("CLOP1134@mheducation.com");
		Assert.assertEquals("Username", Chrome_Driver.findElement(By.xpath("//*[@for='username']")).getText());	
		Assert.assertEquals("Username", Chrome_Driver.findElement(By.xpath("//*[@id='username']")).getAttribute("placeholder"));
		Chrome_Driver.findElement(By.xpath("//*[@id='username']")).sendKeys("CLOP-1134-username");
		Assert.assertEquals("Password", Chrome_Driver.findElement(By.xpath("//*[@for='password']")).getText());	
		Assert.assertEquals("Password", Chrome_Driver.findElement(By.xpath("//*[@id='password']")).getAttribute("placeholder"));
		Chrome_Driver.findElement(By.xpath("//*[@id='password']")).sendKeys("CLOP-1134-password");
		Assert.assertEquals("Confirm Password", Chrome_Driver.findElement(By.xpath("//*[@id='confirm password']")).getAttribute("placeholder"));
		Chrome_Driver.findElement(By.xpath("//*[@id='confirm password']")).sendKeys("CLOP-1134-password");
		Assert.assertEquals("Sign Up", Chrome_Driver.findElement(By.xpath("//*[@id='registerbutton']")).getText());
		Thread.sleep(1000);
		
		// Validate the entered field values
		Assert.assertEquals("CLOP-1134-First", Chrome_Driver.findElement(By.xpath("//*[@id='firstname']")).getAttribute("value"));
		Assert.assertEquals("CLOP-1134-Last", Chrome_Driver.findElement(By.xpath("//*[@id='lastname']")).getAttribute("value"));
		Assert.assertEquals("CLOP-1134-MHE", Chrome_Driver.findElement(By.xpath("//*[@name='data[User][organization]']")).getAttribute("value"));
		Assert.assertEquals("776-287-913", Chrome_Driver.findElement(By.xpath("//*[@id='phone']")).getAttribute("value"));
		Assert.assertEquals("CLOP1134@mheducation.com", Chrome_Driver.findElement(By.xpath("//*[@id='email']")).getAttribute("value"));
		Assert.assertEquals("CLOP-1134-username", Chrome_Driver.findElement(By.xpath("//*[@id='username']")).getAttribute("value"));
		Assert.assertEquals("CLOP-1134-password", Chrome_Driver.findElement(By.xpath("//*[@id='password']")).getAttribute("value"));
		Assert.assertEquals("CLOP-1134-password", Chrome_Driver.findElement(By.xpath("//*[@id='confirm password']")).getAttribute("value"));
		
		// Exit the browser.
		Chrome_Driver.quit();
		
	}
	
}
