package com.qa.opencart.tests;

import java.util.List;

import org.apache.hc.core5.reactor.Command.Priority;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.Basetest;
import com.qa.opencart.page.SearchResultPage;
import com.qa.opencart.page.productinfopage;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Error;

public class AccountsPageTest extends Basetest {
	SoftAssert softassert = new SoftAssert();
	@BeforeClass
	public void accsetup() {
		accountpage = loginpage.dologin(prop.getProperty("username").trim(), prop.getProperty("Password").trim());
	}

	@Test(priority = 1)
	public void homepageTitle() {
		String title = accountpage.getHomePageTitle();
		Assert.assertEquals(title, Constants.ACC_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void accpagelogotest() {
		Assert.assertTrue(accountpage.islogoExist());
	}

	@Test(priority = 3)
	public void accoutpagesectioncounttest() {
		Assert.assertEquals(accountpage.getaccountspageHeadercount(), Constants.ACC_PAGE_SECTIONS_COUNT);
	}

	@Test(priority = 4)
	public void accpagesectionstest() {
		List<String> actualAccSeclist = accountpage.getaccountpageheaderlist();
		Assert.assertEquals(actualAccSeclist, Constants.expecteAccSectionList(), Error.ACCOUNT_SCECTION_ERROR);
	}

	@Test(priority = 5)
	public void searchTest() {
		SearchResultPage = accountpage.dosearch("macbook");
		Assert.assertTrue(SearchResultPage.getProductresultscount() > 0, Error.SEARCH_NOT_SUCCESSFUL);
	}

	@Test(priority = 6)
	public void selectProductTest() {
		SearchResultPage = accountpage.dosearch("macbook");
		productinfopage=SearchResultPage.selectProductfromresults("MacBook Pro");
		String actualHeader = productinfopage.getProductionHeaderText();
		softassert.assertEquals(actualHeader,"MacBook Pro",Error.PRODUCT_HEADER_NOT_FOUND);
		softassert.assertEquals(productinfopage.getProductionImagesCount(), Constants.PRODUCT_IMAGE_COUNT);
		softassert.assertAll();
	}

}
