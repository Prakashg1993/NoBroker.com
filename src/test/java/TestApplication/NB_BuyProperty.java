package TestApplication;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Container.Intialize;
import PageObjects.BuyResultPage;
import PageObjects.LaunchPage;
import PageObjects.SearchPage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.Assert;

public class NB_BuyProperty extends Intialize {
	WebDriverWait wait;
	TouchAction touch;
	LaunchPage launch;
	SearchPage searchPage;
	BuyResultPage BuyResult;

	@Test(priority = 1)
	public void LaunchHome() throws IOException, InterruptedException {
		try {
			AndroidDriver<AndroidElement> driver = InitializeDriver();
			System.out.println("Successfully connected to Android device");
			touch = new TouchAction(driver);
			wait = new WebDriverWait(driver, 10);

			launch = new LaunchPage(driver);

			launch.AllowContinue.click();
			for (int i = 0; i < 3; i++) {
				launch.Allow.click();
			}
			MobileElement searchBox = driver
					.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.nobroker.app:id/buyLayout\")");
			searchBox.click();

			Thread.sleep(3000);
			touch.tap(tapOptions().withElement(element(launch.Search))).perform();

		} catch (Exception e) {
			System.out.println("Exception occured, stopping the server");
			e.printStackTrace();
			StopServer();

		}

	}

	@Test(priority = 2)
	public void SearchBuy() throws InterruptedException {
		try {
			String BHK[] = { "Include nearby properties", "2 BHK", "3 BHK" };
			searchPage = new SearchPage(driver);
			System.out.println("Set Location to Bangalore");
			touch.tap(tapOptions().withElement(element(searchPage.SetLocation))).perform();
			// driver.findElementByAndroidUIAutomator("new UiScrollable(new
			// UiSelector()).scrollIntoView(text(\"Bangalore\"))");
			touch.tap(tapOptions().withElement(element(searchPage.Bangalore))).perform();
			Thread.sleep(2000);
			List<AndroidElement> checkBox = driver
					.findElementsByAndroidUIAutomator("new UiSelector().className(\"android.widget.CheckBox\")");

			for (int i = 0; i < checkBox.size(); i++) {

				for (String value : BHK) {
					System.out.println(checkBox.get(i).getText() + " value  " + value);
					if (checkBox.get(i).getText().equals(value)) {
						checkBox.get(i).click();
					}
				}
			}
			touch.tap(tapOptions().withElement(element(searchPage.SearchBuy))).perform();
			wait.until(ExpectedConditions.visibilityOf(searchPage.SearchBuy));
			searchPage.SearchBuy.sendKeys("Marathahalli");
			Thread.sleep(5000);

			System.out.println("Search Value Marathahalli");
			((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.PAGE_DOWN));
			((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
			Thread.sleep(3000);

//			wait.until(ExpectedConditions.visibilityOf(searchPage.SearchBuy));
//			searchPage.SearchBuy.sendKeys("HSR Layout");
//			Thread.sleep(5000);
//			System.out.println("Search value HSR Layout");
//			((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.PAGE_DOWN));
//			((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

			 searchPage.SEARCH.click();

		} catch (Exception e) {
			System.out.println("Exception occured, stopping the server");
			e.printStackTrace();
			StopServer();
		}
	}

	@Test(priority = 3)
	public void SelectResult() {

		try {
			Thread.sleep(5000);
			BuyResult = new BuyResultPage(driver);
			System.out.println("Select Search result for Buy");
			BuyResult.SelectResult.get(1).click();

			 driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().resourceId(\"android:id/content\")).getChildByText("
							+ "new UiSelector().className(\"android.widget.TextView\"), \"Wrong Info\")")).click();
		
			System.out.println("Login to log a wrong info");
			BuyResult.PhoneNumber.sendKeys("1237567899");
			Thread.sleep(3000);
			touch.tap(PointOption.point(120, 113)).perform();
			Thread.sleep(3000);
			touch.tap(PointOption.point(120, 113)).perform();
			BuyResult.iHavepwd.click();

			BuyResult.EnterPWD.sendKeys("nobroker123");
			driver.hideKeyboard();
			BuyResult.Continue.click();
			Thread.sleep(1000);
			System.out.println("select all check boxes in wrong info page");
			for (int i = 0; i < BuyResult.chkBOX.size(); i++) {
				BuyResult.chkBOX.get(i).click();
			}
			BuyResult.Report.click();

			System.out.println("Correct config setting");
	
			 touch.tap(tapOptions().withElement(element(BuyResult.CrtConfig))).perform();
			driver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(text(\"4+ BHK\"))").click();
			
			Thread.sleep(2500);
			MobileElement Note = driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().resourceId(\"com.nobroker.app:id/decor_content_parent\")).getChildByText("
							+ "new UiSelector().className(\"android.widget.EditText\"), \"Add a note\")"));

			Note.click();
			Note.sendKeys("test");

			BuyResult.Savechange.click();
			String s = BuyResult.SuccessMSG.getText();
			System.out.println("Visible msg is: " + s);
			Assert.assertEquals("Thank you for the feedback", s);

		} catch (Exception e) {

			System.out.println("Exception occured, stopping the server");
			e.printStackTrace();
			StopServer();
		}
	}

}
