package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.page.AccountPage;
import com.qa.opencart.page.SearchResultPage;
import com.qa.opencart.page.loginpage;
import com.qa.opencart.page.productinfopage;

public class Basetest {
	private WebDriver driver;
	public Properties prop;
	DriverFactory df;
	public loginpage loginpage;
	public AccountPage accountpage;
	public SearchResultPage SearchResultPage;
	public productinfopage productinfopage;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.init_prop();
		driver = df.init_driver(prop);
		loginpage = new loginpage(driver);
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
	    driver.quit();
	}
}
