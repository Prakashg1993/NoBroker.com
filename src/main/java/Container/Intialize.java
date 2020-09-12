package Container;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Intialize {

	public static AndroidDriver<AndroidElement> driver;
	public static AppiumDriverLocalService service;

	public static AppiumDriverLocalService startServer() {
		boolean flag = checkIfServerIsRunnning(4723);
		if (!flag) {

			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
			System.out.println("Appium Server Started");
		}
		return service;
	}

	public AndroidDriver<AndroidElement> InitializeDriver() throws IOException, InterruptedException {
//		startEmulator();
		Intialize.startServer();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "AppTest");
		capabilities.setCapability("appPackage", "com.nobroker.app");
		capabilities.setCapability("automationName", "UiAutomator1");
		capabilities.setCapability("appActivity", "com.nobroker.app.activities.NBSplashScreen");
		// capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability(MobileCapabilityType.APP,
				System.getProperty("user.dir") + "\\src\\test\\resources\\NoBroker.apk");

		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println("Driver set to android and returned driver");
		return driver;
	}

	public static boolean checkIfServerIsRunnning(int port) {
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();

		} catch (IOException e) {
			isServerRunning = true;
			System.out.println("Appium Server Is Already running");
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

//	public static void startEmulator() throws IOException, InterruptedException {
//		try {
//			Runtime.getRuntime().exec("c:\\emulatorStart.bat");
//			System.out.println("-----Emulator Starting----");
//			Thread.sleep(12000);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public static void StopServer() {

		service.stop();

	}

}
