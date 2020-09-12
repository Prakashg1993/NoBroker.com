package PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BuyResultPage {

	
	AppiumDriver<WebElement> driver;

	public BuyResultPage(AppiumDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "android.widget.FrameLayout")
	public List<WebElement> SelectResult;


	@AndroidFindBy(xpath  = "//android.widget.TextView[@id='com.nobroker.app:id/tv_report_wrong_info']")
	public WebElement Wronginfo;

	@AndroidFindBy(id = "com.nobroker.app:id/et_signup_phone")
	public WebElement PhoneNumber;
	@AndroidFindBy(id = "com.nobroker.app:id/rb_signup_pwd")
	public WebElement iHavepwd;
	@AndroidFindBy(xpath = "//android.widget.EditText[@id='com.nobroker.app:id/et_signup_pwd']")
	public WebElement EnterPWD;
	@AndroidFindBy(id = "com.nobroker.app:id/btn_signup")
	public WebElement Continue;
	

	@AndroidFindBy(className  = "android.widget.CheckBox")
	public List<WebElement> chkBOX;
	
	@AndroidFindBy(id = "com.nobroker.app:id/btn_report")
	public WebElement Report;

	
	@AndroidFindBy(className = "android.widget.TextView")
	public WebElement CrtConfig;
	@AndroidFindBy(id = "com.nobroker.app:id/btn_save")
	public WebElement Savechange;
	@AndroidFindBy(className = "android.widget.TextView")
	public WebElement SuccessMSG;
}
