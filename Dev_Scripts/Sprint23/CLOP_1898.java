package Sprint23;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CLOP_1898 {

	@Test
	public void CLOP_1898_Test() throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe"); 
		ChromeDriver Chrome_Driver = new ChromeDriver(); 
		Chrome_Driver.manage().deleteAllCookies();
		
		// Test various parameters in the Login page.
		Chrome_Driver.get("https://metadatadev.clo.edmesh.com/");
		Thread.sleep(3000);		
		Chrome_Driver.manage().window().maximize();
		Assert.assertEquals("bento", Chrome_Driver.findElement(By.xpath("//*[@class='form-signin-heading']")).getText());
		Assert.assertEquals("rgba(223, 27, 36, 1)", Chrome_Driver.findElement(By.xpath("//*[@id='signup-signin']/button")).getCssValue("background-color"));
		Assert.assertEquals("https://metadatadev.clo.edmesh.com/img/registration/bento-logo.png", Chrome_Driver.findElement(By.xpath("//*[@class='bento-logo-container']/img")).getAttribute("src"));
		Assert.assertEquals("url(https://metadatadev.clo.edmesh.com/img/registration/login-background-1-blur.jpg)", Chrome_Driver.findElement(By.xpath("//*[@id='UserLoginForm']")).getCssValue("background-image"));
		Assert.assertEquals("Sign Up", Chrome_Driver.findElement(By.xpath("//*[@class='sign-up']")).getText());
		Assert.assertEquals("none", Chrome_Driver.findElement(By.xpath("//*[@class='sign-up']")).getCssValue("text-decoration"));
		Assert.assertEquals("none", Chrome_Driver.findElement(By.xpath("//*[@id='login-subright-right']/a")).getCssValue("text-decoration"));
		WebElement menu = Chrome_Driver.findElement(By.xpath("//*[@class='sign-up']"));
		Actions builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		WebDriverWait wait = new  WebDriverWait(Chrome_Driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='sign-up']"))); 
		Assert.assertEquals("underline", Chrome_Driver.findElement(By.xpath("//*[@class='sign-up']")).getCssValue("text-decoration"));
		menu = Chrome_Driver.findElement(By.xpath("//*[@id='login-subright-right']/a"));
		builder = new  Actions(Chrome_Driver);
		builder.moveToElement(menu).build().perform();
		wait = new  WebDriverWait(Chrome_Driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='login-subright-right']/a"))); 
		Assert.assertEquals("underline", Chrome_Driver.findElement(By.xpath("//*[@id='login-subright-right']/a")).getCssValue("text-decoration"));
	
		// Navigate to the Sign Up page and test the Sign Up page.
		Chrome_Driver.findElement(By.xpath("//*[@id='signup-signin']/a")).click();
		Thread.sleep(2000);	
		Assert.assertEquals("Sign Up", Chrome_Driver.findElement(By.xpath("//*[@id='UserSignupForm']/h2")).getText());
		Assert.assertEquals("rgba(215, 0, 27, 1)", Chrome_Driver.findElement(By.xpath("//*[@id='UserSignupForm']/h2")).getCssValue("color"));
		Assert.assertEquals("Sign Up", Chrome_Driver.findElement(By.xpath("//*[@id='registerbutton']")).getText());
		Assert.assertEquals("rgba(223, 27, 36, 1)", Chrome_Driver.findElement(By.xpath("//*[@id='registerbutton']")).getCssValue("background-color"));
		Chrome_Driver.findElement(By.xpath("//*[@id='registercancel']")).click();
		Thread.sleep(2000);	
		
		// Navigate to the Lost Password page and test the Lost Password page.
		Chrome_Driver.findElement(By.xpath("//*[@id='login-subright-right']/a")).click();
		Thread.sleep(2000);	
		Assert.assertEquals("Lost Password", Chrome_Driver.findElement(By.xpath("//*[@id='registration-container']/form/h2")).getText());
		Assert.assertEquals("rgba(215, 0, 27, 1)", Chrome_Driver.findElement(By.xpath("//*[@id='registration-container']/form/h2")).getCssValue("color"));
		Assert.assertEquals("Reset Password", Chrome_Driver.findElement(By.xpath("//*[@id='resetpasswordbutton']")).getText());
		Assert.assertEquals("rgba(223, 27, 36, 1)", Chrome_Driver.findElement(By.xpath("//*[@id='resetpasswordbutton']")).getCssValue("background-color"));
		Chrome_Driver.findElement(By.xpath("//*[@id='registercancel']")).click();
		Thread.sleep(2000);	
		
		// Exit.
		Chrome_Driver.quit();		
	
	}
	
}