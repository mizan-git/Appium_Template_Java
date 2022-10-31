package testModule;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.MobileBy;
import utility.BaseClass;
@Listeners(utility.Listener.class)
public class Clock_TestCase extends BaseClass {
	public Clock_TestCase() throws Throwable {
		super();
		// TODO Auto-generated constructor stub
	}

	SoftAssert asrt = new SoftAssert();

	@Test(priority = 0, description = "User should be able to open Alarm ")
	public void VefifyAlarmOfClock() {
		test = report.createTest(new Throwable().getStackTrace()[0].getMethodName());

		OpenAlarm();
		String title = getTitle();
		asrt.assertEquals(title, "Alarm", "Error: Alarm title not showing.");
		asrt.assertAll();

	}

	@Test(priority = 1, description = "User should be able to open Timer ")
	public void VefifyTimerOfClock() {
		test = report.createTest(new Throwable().getStackTrace()[0].getMethodName());

		OpenTimer();
		String title = getTitle();
		asrt.assertEquals(title, "Timer", "Error: Timer title not showing.");
		asrt.assertAll();

	}

	@Test(priority = 2, description = "User should be able to open Stopwatch ")
	public void VefifyStopwatchOfClock() {
		test = report.createTest(new Throwable().getStackTrace()[0].getMethodName());

		OpenStopwatch();
		String title = getTitle();
		asrt.assertEquals(title, "Stopwatch", "Error: Stopwatch title not showing.");
		asrt.assertAll();

	}

	By alarm = MobileBy.AccessibilityId("Alarm");
	By clock = MobileBy.AccessibilityId("Clock");
	By timer = MobileBy.AccessibilityId("Timer");
	By stopwatch = MobileBy.AccessibilityId("Stopwatch");
	By title = MobileBy.id("com.google.android.deskclock:id/action_bar_title");

	private void OpenAlarm() {
		driver.findElement(alarm).click();

	}

	private void OpenTimer() {
		driver.findElement(timer).click();

	}

	private void OpenStopwatch() {
		driver.findElement(stopwatch).click();

	}

	private String getTitle() {

		String Title = driver.findElement(title).getText();
		return Title;
	}

}
