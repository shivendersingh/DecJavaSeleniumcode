package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author shivender
 *
 */

public class DriverFactory {
	Properties prop;
	FileInputStream ip;
	public static String highlight;
	OptionsManager optionmanger;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is use to initialize the driver on the basis of given browser
	 * value
	 * 
	 * @param browserName
	 * @return this return WebDriver
	 */

	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		highlight = prop.getProperty("highlight");
		optionmanger = new OptionsManager(prop);
		System.out.println("browser name:-" + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionmanger.getchromoption());
			tlDriver.set(new ChromeDriver(optionmanger.getchromoption()));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionmanger.getfirefoxoption());
			tlDriver.set(new FirefoxDriver(optionmanger.getfirefoxoption()));
		} else if (browserName.equalsIgnoreCase("safari")) {
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println("Browser is not found.. Please pass the correct browser name" + browserName);
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
	}

	/**
	 * 
	 * @return
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * this method is initialize the properties from .properties file
	 * 
	 * @return return Properties (prop)
	 */
	public Properties init_prop() {
		prop = new Properties();
		String env = System.getProperty("env");//read teh property from system
		if (env == null) {
			try {
				ip = new FileInputStream("./src/test/resources/Config/Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				switch (env) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/Config/qa.Config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/Config/dev.Config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/Config/stage.Config.properties");
					break;
				default:
					System.out.println("please pass the right env");
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public String getScreenshot() {
		File srcFile =((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		//File srcFile=new File(src);
		String path=System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		File destination=new File(path);
		try {
			FileUtils.copyFile(srcFile,destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}


}
