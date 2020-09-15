package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SearchPage {

	AppiumDriver<WebElement> driver;

	public SearchPage(AppiumDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.nobroker.app:id/spinnergo")
	public WebElement SetLocation;
	@AndroidFindBy(xpath = "//*[@text='Bangalore']")
	public WebElement Bangalore;
	@AndroidFindBy(id = "com.nobroker.app:id/localityAutoCompleteTxt")
	public WebElement SearchBuy;
	@AndroidFindBy(id = "com.nobroker.app:id/nearByRadio")
	public WebElement NearByCheckBox;
	@AndroidFindBy(xpath = "//*[@text()='SEARCH']")
	public WebElement SEARCH;

	
	public List<WebElement> BHKCheckBox(String data) {
		return driver.findElements(By.id("com.nobroker.app:id/bhk" + data));
	}

}
