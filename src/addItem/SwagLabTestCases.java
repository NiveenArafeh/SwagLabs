package addItem;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SwagLabTestCases {
	public WebDriver driver;

	@BeforeTest
	public void LoginWebsite() throws InterruptedException {

		driver = runBrowser("chrome", driver);
		driver.get("https://www.saucedemo.com/");
		String userName = "standard_user";
		String passwordAllUsers = driver.findElement(By.xpath("//div[@class='login_password']")).getText();
		String[] passwordUser = passwordAllUsers.split(":");
		driver.findElement(By.id("user-name")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(passwordUser[1]);

		driver.findElement(By.id("login-button")).click();
		Thread.sleep(3000);

	}

	@Test(priority = 1)
	public void sortPriceOfItemsFromHighToLow() {
		// select an option from DDL using select from selenium
		
		Select selectSortOption = new Select(driver.findElement(By.xpath("//select[@class='product_sort_container']")));
		selectSortOption.selectByValue("hilo");

		List<WebElement> allPriceswith$ = driver.findElements(By.className("inventory_item_price"));
		String priceWithout$, priceWith$;
		double val;
		List<Double> allPrices = new ArrayList<>();

		for (int i = 0; i < allPriceswith$.size(); i++) {
			// System.out.println(allPriceswith$.get(i).getText());
			priceWith$ = allPriceswith$.get(i).getText();
			priceWithout$ = priceWith$.replace("$", " ");
			// System.out.println(priceWithout$.trim());
			val = Double.parseDouble(priceWithout$.trim());
			allPrices.add(val);

		}
		System.out.println("All Prices sorted from high price to Low price: ");
		System.out.println(allPrices);
		for (int k = 0; k < allPrices.size(); k++) {
			// System.out.println(allPrices.get(k));
			boolean checkHighPrice = allPrices.get(k) >= allPrices.get(allPrices.size() - 1);
			System.out.println("This price " + allPrices.get(k) + " is greater than or equal "
					+ allPrices.get(allPrices.size() - 1) + ":" + checkHighPrice);
			Assert.assertEquals(checkHighPrice, true);

		}
	}

	@Test(priority = 2)
	public void sortPriceOfItemsFromLowToHigh() {
		// select an option from DDL using select from selenium
		
		Select selectSortOption = new Select(driver.findElement(By.xpath("//select[@class='product_sort_container']")));
		selectSortOption.selectByValue("lohi");

		List<WebElement> allPriceswith$ = driver.findElements(By.className("inventory_item_price"));
		String priceWithout$, priceWith$;
		double val;
		List<Double> allPrices = new ArrayList<>();

		for (int i = 0; i < allPriceswith$.size(); i++) {
			// System.out.println(allPriceswith$.get(i).getText());
			priceWith$ = allPriceswith$.get(i).getText();
			priceWithout$ = priceWith$.replace("$", " ");
			// System.out.println(priceWithout$.trim());
			val = Double.parseDouble(priceWithout$.trim());
			allPrices.add(val);

		}
		System.out.println("=================================================");
		System.out.println("All Prices sorted from Low price to High price: ");

		System.out.println(allPrices);
		for (int k = 0; k < allPrices.size(); k++) {
			// System.out.println(allPrices.get(k));
			boolean checkLowPrice = allPrices.get(k) <= allPrices.get(allPrices.size() - 1);
			System.out.println("This price " + allPrices.get(k) + " is less than or equal "
					+ allPrices.get(allPrices.size() - 1) + ":" + checkLowPrice);

			Assert.assertEquals(checkLowPrice, true);

		}
	}

	@Test(priority = 3)
	public void addAllItemsToList() throws InterruptedException {

		List<WebElement> addToCartBtn = driver.findElements(By.className("btn_inventory"));
		int actualResultCounter = 0;
		for (int i = 0; i < addToCartBtn.size(); i++) {
			addToCartBtn.get(i).click();
			actualResultCounter++;
		}
		String numberOfSelectedItems = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span"))
				.getText();

		Assert.assertEquals(Integer.toString(actualResultCounter), numberOfSelectedItems);

	}

	public WebDriver runBrowser(String browser, WebDriver driver) {
		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		return driver;

	}

}
