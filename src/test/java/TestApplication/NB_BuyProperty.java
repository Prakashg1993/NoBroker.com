package TestApplication;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import java.io.IOException;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Container.Intialize;
import PageObjects.BuyResultPage;
import PageObjects.LaunchPage;
import PageObjects.SearchPage;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import junit.framework.Assert;

public class NB_BuyProperty extends Intialize {
	WebDriverWait wait;
	TouchAction touch;
	LaunchPage launch;
	SearchPage searchPage;
	BuyResultPage BuyResult;

	@Test(priority=1)
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
			System.out.println("Tap on Buy Tap");
			touch.tap(tapOptions().withElement(element(launch.BuyTab))).perform();
			
			touch.tap(tapOptions().withElement(element(launch.Search))).perform();

		} catch (Exception e) {
			System.out.println("Exception occured, stopping the server");
			e.printStackTrace();
			StopServer();

		}

	}
	@Test(priority=2)
	public void SearchBuy() throws InterruptedException {
		try {
			String BHK[] = { "two", "three" };

			System.out.println("Set Location to Bangalore");
			touch.tap(tapOptions().withElement(element(searchPage.SetLocation))).perform();
			// driver.findElementByAndroidUIAutomator("new UiScrollable(new
			// UiSelector()).scrollIntoView(text(\"Bangalore\"))");
			touch.tap(tapOptions().withElement(element(searchPage.Bangalore))).perform();
			touch.tap(tapOptions().withElement(element(searchPage.SearchBuy))).perform();
			wait.until(ExpectedConditions.visibilityOf(searchPage.SearchBuy));

			searchPage.SearchBuy.sendKeys("Marathahalli");
			System.out.println("Search Value Marathahalli");
			((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.PAGE_DOWN));
			((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

			wait.until(ExpectedConditions.visibilityOf(searchPage.SearchBuy));
			searchPage.SearchBuy.sendKeys("HSR Layout");
			System.out.println("Search value HSR Layout");
			((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.PAGE_DOWN));
			((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

			wait.until(ExpectedConditions.visibilityOf(searchPage.NearByCheckBox));
			if (searchPage.NearByCheckBox.isSelected() == false)
				searchPage.NearByCheckBox.click();
			int i = 0;
			System.out.println("Select Near By Loc and BHK");
			for (String arr : BHK) {
				if (searchPage.BHKCheckBox(arr).get(i).getText().equals(arr))
					searchPage.BHKCheckBox(arr).get(i).click();
				i++;
			}

			searchPage.SEARCH.click();
		} catch (Exception e) {

			System.out.println("Exception occured, stopping the server");
			e.printStackTrace();
			StopServer();
		}
	}
	@Test(priority=3)
	public void SelectResult() {

		try {
			System.out.println("Select Search result for Buy");
			BuyResult.SelectResult.get(1).click();
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Report what wasn’t correct in this property\"))");
			BuyResult.Wronginfo.click();
			System.out.println("Login to log a wrong info");
			BuyResult.PhoneNumber.sendKeys("1237567899");			
			BuyResult.iHavepwd.click();
			BuyResult.EnterPWD.sendKeys("nobroker123");
			BuyResult.Continue.click();
			
			System.out.println("select all check boxes in wrong info page");
			for(int i=0;i<BuyResult.chkBOX.size();i++) {
				BuyResult.chkBOX.get(i).click();
			}
			BuyResult.Report.click();
			
			System.out.println("Correct config setting");
			touch.tap(tapOptions().withElement(element(BuyResult.CrtConfig))).perform();
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"4+ BHK\"))").click();
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Add a note\"))").click();
			
			BuyResult.Savechange.click();
			String s=BuyResult.SuccessMSG.getText();
			System.out.println("Visible msg is: "+s);
			Assert.assertEquals("Thank you for the feedback", s);
			

		} catch (Exception e) {

			System.out.println("Exception occured, stopping the server");
			e.printStackTrace();
			StopServer();
		}
	}

}
