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
		System.out.println(titleOfPage);
		Assert.assertEquals(titleOfPage, "My Account", "Failed to Login");
		wd.findElement(By.cssSelector("ul[class='nav navbar-nav'] >li:nth-of-type(6) a")).click();
		String titleOf2ndPage = wd.getTitle();
		Assert.assertEquals(titleOf2ndPage, "Phones & PDAs", "Something went Wrong");
//		wd.findElement(By.cssSelector("#content>div.row:nth-of-type(2)>div:first-of-type>div>div:last-of-type a"))
//				.click();
		wd.findElement(By.cssSelector("#content>div:nth-of-type(2) div:nth-of-type(3) >div div:nth-of-type(2) a"))
		.click();
		String titleOf3rdPage = wd.getTitle();
		//Assert.assertEquals(titleOf3rdPage, "HTC Touch HD", "Something went Wrong");
		Assert.assertEquals(titleOf3rdPage, "Palm Treo Pro", "Something went Wrong");
		wd.findElement(By.cssSelector("button#button-cart")).click();
		sleep();
		String readText = wd.findElement(By.cssSelector("#cart-total")).getText();
		Assert.assertEquals(readText, "1 item(s) - $279.99", "There is More than 1 item in cart");
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
		Select sc = new Select(wd.findElement(By.cssSelector("#input-payment-country")));
		sc.selectByValue("38");
		WebElement state = wd.findElement(By.cssSelector("select[name='zone_id']"));
		Select sc1 = new Select(wd.findElement(By.cssSelector("select[name='zone_id']")));
		sc1.selectByValue("609");
		wd.findElement(By.cssSelector("input[value='Continue']")).click();
		WebElement radiobtn = wd
				.findElement(By.cssSelector("div[class='panel-collapse collapse in'] form>div:first-of-type input"));
		if (!radiobtn.isSelected()) {
			radiobtn.click();
		}
		wd.findElement(By.cssSelector("#button-shipping-address:last-of-type")).click();
		WebElement flatRate = wd.findElement(By.cssSelector("input[value='flat.flat']"));
		if (!flatRate.isSelected()) {
			flatRate.click();
		}
		WebElement textArea = wd.findElement(By.cssSelector("textarea[name='comment']"));
		textArea.sendKeys("Okeetyyy");
		wd.findElement(By.cssSelector("#button-shipping-method:last-of-type")).click();
		WebElement radiobtnCashDeliv = wd.findElement(By.cssSelector("input[value='cod']"));
		if (!radiobtnCashDeliv.isSelected()) {
			radiobtnCashDeliv.click();
		}
		WebElement checkBocTerm = wd.findElement(By.cssSelector("input[name='agree']"));
		if (!checkBocTerm.isSelected()) {
			checkBocTerm.click();
		}
		wd.findElement(By.cssSelector("#button-payment-method:last-of-type")).click();
		WebElement productName = wd.findElement(By.cssSelector("div.table-responsive a"));
		Assert.assertEquals(productName.getText(), "Palm Treo Pro", "Oops Wrong item in cart");
		WebElement productQuantity = wd
				.findElement(By.cssSelector("div.table-responsive >table>tbody td:nth-of-type(3)"));
		Assert.assertEquals(productQuantity.getText(), "1", "More than 1 item found");
		wd.findElement(By.cssSelector("#button-confirm:first-of-type")).click();
		sleep();
		WebElement confirmText = wd.findElement(By.cssSelector("#content h1"));
		Assert.assertEquals(confirmText.getText(), "Your order has been placed!", "SomeThing Went Wrong");

	}

	@AfterMethod
	public void tearDown() {
		wd.close();
	}

	public void sleep() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
