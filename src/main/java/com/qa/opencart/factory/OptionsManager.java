package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getchromoption() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incongnito")))co.addArguments("--incognito");
		return co;
	}
	public FirefoxOptions getfirefoxoption() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) fo.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incongnito")))fo.addArguments("--incognito");
		return fo;
	}

}
