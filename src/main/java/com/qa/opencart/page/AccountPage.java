package com.qa.opencart.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtils;

public class AccountPage {
	ElementUtils elementUtils;
	private WebDriver driver;
	private By logo = By.cssSelector("#logo");
	private By AccHeader = By.cssSelector("#content h2");
	private By searchField = By.name("search");
	private By searchButton = By.cssSelector("div#search button");

	public AccountPage(WebDriver driver) {
		this.driver =driver;
		elementUtils = new ElementUtils(driver);
	}

	public String getHomePageTitle() {
		System.out.println("webhook Test");
		return elementUtils.waitForTitleIs(5, Constants.ACC_PAGE_TITLE);
	}

	public int getaccountspageHeadercount() {
		return elementUtils.getElements(AccHeader).size();
	}

	public List<String> getaccountpageheaderlist() {
		List<WebElement> accList = elementUtils.getElements(AccHeader);
		List<String> accSectionList = new ArrayList<String>();
		for (WebElement e : accList) {
			accSectionList.add(e.getText());
		}
		return accSectionList;
	}

	public SearchResultPage dosearch(String productname) {
		elementUtils.doSendKeys(searchField, productname);
		elementUtils.doClick(searchButton);
		elementUtils.doclear(searchField);
		return new SearchResultPage(driver);

	}
	public boolean islogoExist() {
	return elementUtils.doIsDisplayed(logo);
	}
}
