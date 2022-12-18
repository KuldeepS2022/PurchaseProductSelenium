package com.KuldeepSingh.purchaseFromNaveenAutomationLabs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PurchaseFromNaveenWebsite {
	WebDriver wd;

	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\deepg\\Downloads\\setup\\chromedriver.exe");
		wd = new ChromeDriver();
		wd.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void verifyUserPurchase() {
		WebElement userName = wd.findElement(By.cssSelector("input#input-email"));
		WebElement password = wd.findElement(By.cssSelector("input#input-password"));
		WebElement button = wd.findElement(By.cssSelector("input[value='Login']"));
		userName.sendKeys("PeterLois@familyguy.com");
		password.sendKeys("StewieisOG");
		button.click();
		String titleOfPage = wd.getTitle();
		SoftAssert sa=new SoftAssert();
		
		sa.assertEquals(titleOfPage, "My Account", "Failed to Login");
		wd.findElement(By.cssSelector("ul[class='nav navbar-nav'] >li:nth-of-type(6) a")).click();
		String titleOf2ndPage = wd.getTitle();
		sa.assertEquals(titleOf2ndPage, "Phones & PDAs", "Something went Wrong");
		wd.findElement(By.cssSelector("#content>div:nth-of-type(2) div:nth-of-type(3) >div div:nth-of-type(2) a"))
				.click();
		String titleOf3rdPage = wd.getTitle();
		sa.assertEquals(titleOf3rdPage, "Palm Treo Pro", "Something went Wrong");
		wd.findElement(By.cssSelector("button#button-cart")).click();
		sleep();
		String readText = wd.findElement(By.cssSelector("#cart-total")).getText();
		sa.assertEquals(readText, "1 item(s) - $279.99", "There is More than 1 item in cart");
		wd.findElement(By.cssSelector("#cart-total")).click();

		wd.findElement(By.cssSelector("ul[class=\"dropdown-menu pull-right\"] p>a:last-of-type")).click();
		sleep();
		wd.findElement(By.cssSelector("form[class=form-horizontal]>div:nth-of-type(3) input")).click();
		WebElement fNameBill = wd.findElement(By.cssSelector("#input-payment-firstname"));
		WebElement lNameBill = wd.findElement(By.cssSelector("#input-payment-lastname"));
		WebElement company = wd.findElement(By.cssSelector("#input-payment-company"));
		WebElement address1 = wd.findElement(By.cssSelector("#input-payment-address-1"));
		WebElement address2 = wd.findElement(By.cssSelector("#input-payment-address-2"));
		WebElement city = wd.findElement(By.cssSelector("#input-payment-city"));
		WebElement postCode = wd.findElement(By.cssSelector("#input-payment-postcode"));
		wd.findElement(By.cssSelector("form.form-horizontal>div:nth-of-type(3 ) input")).click();
		fNameBill.sendKeys("Peters");
		lNameBill.sendKeys("Griffin");
		company.sendKeys("HTC");
		address1.sendKeys("31 Spooner Street");
		city.sendKeys("Quahog");
		postCode.sendKeys("02907");
		WebElement country = wd.findElement(By.cssSelector("#input-payment-country"));
		selectByValue(country, "38");
		WebElement state = wd.findElement(By.cssSelector("select[name='zone_id']"));
		selectByValue(state, "609");
		wd.findElement(By.cssSelector("input[value='Continue']")).click();
		WebElement radiobtn = wd
				.findElement(By.cssSelector("div[class='panel-collapse collapse in'] form>div:first-of-type input"));
		clickIfNotClick(radiobtn);
		wd.findElement(By.cssSelector("#button-shipping-address:last-of-type")).click();
		WebElement flatRate = wd.findElement(By.cssSelector("input[value='flat.flat']"));
		clickIfNotClick(flatRate);
		WebElement textArea = wd.findElement(By.cssSelector("textarea[name='comment']"));
		textArea.sendKeys("Okeeyyy okeeyyy");
		wd.findElement(By.cssSelector("#button-shipping-method:last-of-type")).click();
		WebElement radiobtnCashDeliv = wd.findElement(By.cssSelector("input[value='cod']"));
		clickIfNotClick(radiobtnCashDeliv);
		WebElement checkBocTerm = wd.findElement(By.cssSelector("input[name='agree']"));
		clickIfNotClick(checkBocTerm);
		wd.findElement(By.cssSelector("#button-payment-method:last-of-type")).click();
		WebElement productName = wd.findElement(By.cssSelector("div.table-responsive a"));
		sa.assertEquals(productName.getText(), "Palm Treo Pro", "Oops Wrong item in cart");
		WebElement productQuantity = wd
				.findElement(By.cssSelector("div.table-responsive >table>tbody td:nth-of-type(3)"));
		sa.assertEquals(productQuantity.getText(), "1", "More than 1 item found");
		wd.findElement(By.cssSelector("#button-confirm:first-of-type")).click();
		sleep();
		WebElement confirmText = wd.findElement(By.cssSelector("#content h1"));
		sa.assertEquals(confirmText.getText(), "Your order has been placed!", "SomeThing Went Wrong");

	}

	@AfterMethod
	public void tearDown() {
		wd.close();
	}

	public void sleep() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickIfNotClick(WebElement element) {
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void selectByValue(WebElement element, String value) {
		Select sc = new Select(element);
		sc.selectByValue(value);
	}

}
