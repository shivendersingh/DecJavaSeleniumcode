package com.qa.opencart.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtils;

import bsh.This;

public class productinfopage {
	ElementUtils elementutils;
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By successMessg = By.cssSelector("div.alert.alert-success.alert-dismissible");

	public productinfopage(WebDriver driver) {
		elementutils = new ElementUtils(driver);
	}

	public String getProductionHeaderText() {
		System.out.println(elementutils.doGetElementText(productHeader).trim());
		return elementutils.doGetElementText(productHeader).trim();
	}

	public int getProductionImagesCount() {
		return elementutils.getElements(productImages).size();
	}

	public Map<String, String> getProductInformation() {
		Map<String, String> productionInfoMap = new HashMap<String, String>();
		productionInfoMap.put("name", getProductionHeaderText());
		List<WebElement> productMetaDataList = elementutils.getElements(productMetaData);
		System.out.println("total product metadata:" + productionInfoMap.size());

		for (WebElement e : productMetaDataList) {
			String meta[] = e.getText().split(":");
			String metakey = meta[0].trim();
			String metavalue = meta[1].trim();
			productionInfoMap.put(metakey, metavalue);
			System.out.println(productionInfoMap);
		}
		// price:
		List<WebElement> productPriceList = elementutils.getElements(productPriceData);
		productionInfoMap.put("Price", productPriceList.get(0).getText().trim());
		System.out.println("Value: "+productionInfoMap);
		productionInfoMap.put("exTaxPrice", productPriceList.get(1).getText().split(":")[1].trim());
		return productionInfoMap;
	}

	public void selectQuantity(String qty) {
		elementutils.doSendKeys(quantity, qty);
	}

	public void addtoCart() {
		elementutils.doClick(addToCartBtn);
	}

	public String getSucessMessage() {
		return elementutils.doGetElementText(successMessg);
	}
}
