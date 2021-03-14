package com.qa.opencart.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtils;

import io.qameta.allure.Step;

public class loginpage {
	// Page Objects - By Locators - O R
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPwd = By.xpath("//div[@class='form-group']/a[text()='Forgotten Password']");
	private WebDriver driver;
	private ElementUtils elementutils;

	// constructor:
	public loginpage(WebDriver driver) {
		this.driver = driver;
		elementutils = new ElementUtils(driver); 
	}

	// page actions:
	@Step("getting login page title..")
	public String getloginPageTitle() {
		return elementutils.waitForTitleIs(5, Constants.LOGIN_PAGE_TITLE);
	}
	@Step("checking forgot pwd link..")
	public boolean isForgotPwdLinkExist() {
		// return driver.findElement(forgotPwd).isDisplayed();
		return elementutils.doIsDisplayed(forgotPwd);
	}
	@Step("login with username:{0} and password: {1}")
	public AccountPage dologin(String un, String pwd) {
		System.out.println("login with: " + un + " : " + pwd);
		elementutils.doSendKeys(username, un);
		elementutils.doSendKeys(password, pwd);
		elementutils.doClick(loginButton);
		return new AccountPage(driver);
	}
}
