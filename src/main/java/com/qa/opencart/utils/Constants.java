package com.qa.opencart.utils;

import java.awt.List;
import java.util.ArrayList;

public class Constants {

	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACC_PAGE_TITLE = "My Account";
	public static final int ACC_PAGE_SECTIONS_COUNT = 4;
	public static final int PRODUCT_IMAGE_COUNT = 4;
	public static java.util.List<String> expecteAccSectionList() {
		java.util.List<String> list =  new ArrayList<String>();
		list.add("My Account");
		list.add("My Orders");
		list.add("My Affiliate Account");
		list.add("Newsletter");
		return list;
	}
	
}
