package demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(JUnit4ClassRunner.class)
public class MongoLoginTest {
//	WebDriver driver = null;
	
	@Before
	public void before() {
//		driver = new FirefoxDriver();
//		driver.get("https://localhost/modelphp/");
	}
	
	@Test
	public void successfulLogin() {
		WebDriver driver = new FirefoxDriver();
		driver.get("https://university.mongodb.com/");
		
		WebElement signin = driver.findElement(By.xpath("//a[@class='nav-panel-top__a' and @href='#']"));
		signin.click();
		
		WebElement email = driver.findElement(By.name("email"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement submit = driver.findElement(By.name("submit"));
		email.sendKeys("ashismo@gmail.com");
		password.sendKeys("ashish");
		submit.click();
//		driver.close();
	}
	
	@Test
	public void failedLogin() {
		WebDriver driver = new FirefoxDriver();
		driver.get("https://university.mongodb.com/");
		
		WebElement signin = driver.findElement(By.xpath("//a[@class='nav-panel-top__a' and @href='#']"));
		signin.click();
		
		WebElement email = driver.findElement(By.name("email"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement submit = driver.findElement(By.name("submit"));
		email.sendKeys("ashismo@gmail.com");
		password.sendKeys("ashish1");
		submit.click();
//		driver.close();
	}
}
