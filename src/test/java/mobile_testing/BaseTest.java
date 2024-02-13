package mobile_testing;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class BaseTest {
	public static AndroidDriver<AndroidElement> driver ;
	@BeforeClass
	public static AndroidDriver<AndroidElement> capability() throws MalformedURLException{
	DesiredCapabilities dc = new DesiredCapabilities();
	
	dc.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator-5554");
	dc.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
	dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"io.appium.android.apis");
	dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"io.appium.android.apis.ApiDemos");
	
	dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,AutomationName.ANDROID_UIAUTOMATOR2);
	dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,AutomationName.APPIUM);
	
	//To start appium server programmatically  
	AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\User\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
			.withIPAddress("0.0.0.0").usingPort(4723).build();
	service.start();
	
	 driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"),dc);
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	return driver;

	
	}
//	@AfterClass
//	public void tearDown() {
//		driver.close();
//	}

}
