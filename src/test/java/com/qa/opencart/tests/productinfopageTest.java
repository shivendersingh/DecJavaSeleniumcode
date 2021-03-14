package com.qa.opencart.tests;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.Basetest;

public class productinfopageTest extends Basetest {
	SoftAssert softassert= new SoftAssert();
	@BeforeClass
	public void accsetup() {
		accountpage = loginpage.dologin(prop.getProperty("username").trim(), prop.getProperty("Password").trim());
	}

	@Test
	public void productInfoTest() {
		SearchResultPage =  accountpage.dosearch("MacBook");
		productinfopage=SearchResultPage.selectProductfromresults("MacBook Pro");
		Map<String, String> actualproductinfomap=   productinfopage.getProductInformation();
		softassert.assertTrue(actualproductinfomap.get("name").equals("MacBook Pro"));
		softassert.assertTrue(actualproductinfomap.get("Brand").equals("Apple"));
		softassert.assertTrue(actualproductinfomap.get("Price").equals("$2,000.00"));
		softassert.assertAll();	
	}
}
