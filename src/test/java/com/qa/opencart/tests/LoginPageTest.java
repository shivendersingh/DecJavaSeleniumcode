package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.opencart.base.Basetest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Story("US-101: design the login page for demo opencart app with login, title and forgot pwd link")

@Epic("Epic-100: design login page feature")
public class LoginPageTest extends Basetest {
	@Description("Login page title test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void loginpageTitletest() {
		String title = loginpage.getloginPageTitle();
		System.out.println("Login page title:- " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	@Description("forget pwd link test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginpage.isForgotPwdLinkExist());
	}
	@Description("login test with correct credentials...")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 3)
	public void loginTest() {
		loginpage.dologin(prop.getProperty("username"), prop.getProperty("Password"));
		Assert.assertEquals(loginpage.getloginPageTitle(), Constants.ACC_PAGE_TITLE);
	}
}
