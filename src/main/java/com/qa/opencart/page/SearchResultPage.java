package com.qa.opencart.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtils;

public class SearchResultPage {
	private WebDriver driver;
	ElementUtils elementutils;
	By searchitemresult = By.cssSelector("div.product-layout div.product-thumb");
	By resultItems = By.cssSelector("div.product-thumb h4 a");

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		elementutils = new ElementUtils(driver);
	}

	public int getProductresultscount() {
		System.out.println(elementutils.getElements(searchitemresult).size());
		return elementutils.getElements(searchitemresult).size();
	}

	public productinfopage selectProductfromresults(String Productname) {
		List<WebElement> resultItemlist = elementutils.getElements(resultItems);
		System.out.println("total number of item displayed for :" + Productname + " : " + resultItemlist.size());
		for (WebElement e : resultItemlist) {
			if (e.getText().equals(Productname)) {
				e.click();
				break;
			}
		}
		return new productinfopage(driver);
	}
}
