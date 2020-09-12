package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LaunchPage {
	AppiumDriver<WebElement> driver;
	
	public LaunchPage(AppiumDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}


	
	@AndroidFindBy(id = "com.nobroker.app:id/yesPhoneState")
	public WebElement AllowContinue;
	
	
	@AndroidFindBy(id="com.android.packageinstaller:id/permission_allow_button")
	public WebElement Allow;
	
	@AndroidFindBy(id = "com.nobroker.app:id/buyLayout")
	public WebElement BuyTab;

	@AndroidFindBy(xpath  = "//*[@text='Search up to 3 Localities or Landmarks']")
	public WebElement Search;
	
	
}
