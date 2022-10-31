package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseClass {

	public static WebDriverWait wait;
	public static AndroidDriver driver;
	public static ExtentReports report;
	public static ExtentTest test;
	ExtentSparkReporter spark;
	public static String screenshotName;

	public String projectPath = System.getProperty("user.dir");

	public Properties prop;
	public FileInputStream input;
	public String author;
	public static AppiumDriverLocalService appiumService;
	public String appiumServiceUrl;
	public String platformName;
	public String deviceName;
	//public String platformVersion;
	public String appPackage;
	public String appActivity;
	public String searchItem;

	public BaseClass() throws Throwable {

		try {
			loadProperties();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void loadProperties() throws Throwable {
		prop = new Properties();

		input = new FileInputStream(projectPath + "/Configurations/Configration.properties");
		prop.load(input);
		author = prop.getProperty("AutomationEngineer");
		platformName = prop.getProperty("platformName");
		deviceName = prop.getProperty("deviceName");
		//platformVersion=prop.getProperty("platformVersion");
		appPackage = prop.getProperty("appPackageName");
		appActivity = prop.getProperty("appActivityName");
		searchItem = prop.getProperty("searchItem");

		if (driver == null) {
			report = new ExtentReports();
			spark = new ExtentSparkReporter(projectPath + "/Reports/Appium_AutomationReport.html");
			// JSON config file
			final File reportConfigFile = new File(projectPath + "/Configurations/extentConfig.json");
			spark.loadJSONConfig(reportConfigFile);
			report.attachReporter(spark);

			
			appiumService = AppiumDriverLocalService.buildDefaultService();
			appiumService.start();
			appiumServiceUrl = appiumService.getUrl().toString();
			appiumService.clearOutPutStreams();
			Thread.sleep(2000);

			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability("platformName", platformName);
			dc.setCapability("deviceName", deviceName);
			//dc.setCapability("platformVersion", platformVersion);
			// dc.setCapability("noReset", "true");
			dc.setCapability("autoGrantPermissions", "true");
			// dc.setCapability("udid", "localhost:21503");
			// dc.setCapability("appPackage", "com.android.settings");
			dc.setCapability("appPackage", appPackage);
			// dc.setCapability("appActivity", "com.android.settings.Settings");
			dc.setCapability("appActivity", appActivity);

			driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
			
			
			
		
		}
	}

	

	public static String ScreenCapture() throws IOException {

		screenshotName = "Error_" + CurrentTime() + ".jpg";

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\Reports\\" + screenshotName));

		return screenshotName;

	}

	private static String CurrentTime() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy-hh_mm_ss");
		LocalDateTime CurrentTime = LocalDateTime.now();

		return (dateFormat.format(CurrentTime));
	}

	@AfterSuite()
	public void CloseBrowser() {
		// driver.close();
		// driver.quit();
		 appiumService.stop();
		 
	}

}
