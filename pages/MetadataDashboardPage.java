package pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MetadataDashboardPage {
    
    WebDriver driver;

    public MetadataDashboardPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void login(String username, String password) {
        driver.findElement(By.xpath("//*[@name='data[User][username]']")).sendKeys(username);
        driver.findElement(By.xpath("//*[@name='data[User][password]']")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id='signup-signin']/button")).click();
        try {Thread.sleep(3000);} catch (InterruptedException e) {}
    }
    
    public void inherittest(int i) {
    	String inherited = "inherited icon-large align-center";
		Assert.assertEquals(inherited, driver.findElement(By.xpath("//*[@id='metadatatable']/tbody/tr["+i+"]/td[3]/span")).getAttribute("class"));
    }
    
    public void logout() throws Exception {
	    driver.findElement(By.xpath("//*[@id='nav-icons']/ul/li[2]/a/img")).click();
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(3000);
		driver.quit();
    }
    
    public void mddb_thumbnail_selection(String thumbnail) {
    	List<WebElement> AllSearchResults=driver.findElements(By.xpath("//*[@class='row dashboard-links text-center']/div/a/img"));	
		for  (WebElement eachResult : AllSearchResults) {
			String sValue = null;
			sValue = eachResult.getAttribute("src");	
			if (sValue.equalsIgnoreCase(thumbnail)) {
				eachResult.click();
			}
		}
    }
   
}