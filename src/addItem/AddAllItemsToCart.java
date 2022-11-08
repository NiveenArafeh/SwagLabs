package addItem;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class AddAllItemsToCart {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "E:\\AutomationCourse\\drivers\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.saucedemo.com/");
		String userName= "standard_user";
		String passwordAllUsers= driver.findElement(By.xpath("//div[@class='login_password']")).getText();
		
		String [] passwordUser=passwordAllUsers.split(":");
		driver.findElement(By.id("user-name")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(passwordUser[1]);
		
		driver.findElement(By.id("login-button")).click();
		Thread.sleep(3000);

		List<WebElement> addToCartBtn=driver.findElements(By.className("btn_inventory"));
		int actualResultCounter=0;
		for (int i=0;i<addToCartBtn.size();i++)
		{
			addToCartBtn.get(i).click();
			actualResultCounter++;
		}
		String numberOfSelectedItems= driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText();
		
		Assert.assertEquals(Integer.toString(actualResultCounter), numberOfSelectedItems);
		
		//driver.quit();

	}

}
