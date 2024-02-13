package mobile_testing;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class Test1 extends BaseTest  {
	@Test
	public void run() throws MalformedURLException {
//	public static void main(String[] args) throws MalformedURLException {
	AndroidDriver<AndroidElement> driver= capability();
	driver.findElement(By.xpath("(//android.widget.TextView)[11]")).click();
	driver.findElement(MobileBy.AccessibilityId("3. Preference dependencies")).click();
	driver.findElement(By.id("android:id/checkbox")).click();
	driver.findElement(By.xpath("//*[@text='WiFi settings']")).click();
	String pop=driver.findElement(By.id("android:id/alertTitle")).getText();
	Assert.assertEquals(pop, "WiFi settings");
	//copying text using clipboard
	driver.setClipboardText("vini");
	driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
	driver.findElement(By.id("android:id/button1")).click();
	//Key commands
	driver.pressKey(new KeyEvent(AndroidKey.BACK));
	driver.openNotifications();
	driver.findElement(MobileBy.AccessibilityId("Open quick settings.")).click();
	driver.findElement(MobileBy.AccessibilityId("Battery Saver")).click();
	driver.pressKey(new KeyEvent(AndroidKey.HOME));
	driver.findElement(MobileBy.AccessibilityId("Apps list")).click();
	
	}
	@Test
	public void views() throws MalformedURLException
	{
		AndroidDriver<AndroidElement> driver= capability();
		driver.findElement(MobileBy.AccessibilityId("Views")).click();
		driver.findElement(MobileBy.AccessibilityId("Expandable Lists")).click();
		driver.findElement(MobileBy.AccessibilityId("1. Custom Adapter")).click();
	    WebElement people = driver.findElement(By.xpath("//*[@text='People Names']"));
		
	    //For long press we are creating object of TouchAction
		TouchAction ta = new TouchAction(driver);
		ta.longPress(LongPressOptions.longPressOptions().withElement(new ElementOption().withElement(people))
		.withDuration(Duration.ofSeconds(10))).release().perform();
		String pop = driver.findElement(By.xpath("//*[@text='Sample menu']")).getText();
		Assert.assertEquals(pop, "Sample menu");
		
		}
	@Test
	public void alert() {
		driver.findElement(MobileBy.AccessibilityId("App")).click();
		driver.findElement(MobileBy.AccessibilityId("Alert Dialogs")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(MobileBy.AccessibilityId("OK Cancel dialog with a message")).click();
		driver.findElement(By.id("android:id/button1")).click();
		}
	@Test
	public void scroll()
	{
		driver.findElement(MobileBy.AccessibilityId("Views")).click();
		driver.findElement(MobileBy.AndroidUIAutomator
				("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"))"));
	}
	@Test
	public void swipe()
	{
		driver.findElement(MobileBy.AccessibilityId("Views")).click();
		driver.findElement(MobileBy.AccessibilityId("Gallery")).click();
		driver.findElement(MobileBy.AccessibilityId("1. Photos")).click();
		WebElement img1=driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
		//getting size of screen to swipe
		Dimension dim=driver.manage().window().getSize();
		int x=img1.getLocation().getX()+img1.getSize().getWidth()/2;
		int y=img1.getLocation().getY()+img1.getSize().getHeight()/2;
		int target_x=(int)(dim.width*0.25);
		//to swipe
		TouchAction ta=new TouchAction(driver);
		ta.press(PointOption.point(x,y)).waitAction().moveTo(PointOption.point(target_x, y)).release().perform();
		//for landscape
		driver.rotate(ScreenOrientation.LANDSCAPE);
		
	}
	@Test
	public void dragAndDrop() throws InterruptedException
	{
		driver.findElement(MobileBy.AccessibilityId("Views")).click();
		driver.findElement(MobileBy.AccessibilityId("Drag and Drop")).click();
		WebElement src = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
		((JavascriptExecutor)driver).executeScript("mobile: dragGesture", ImmutableMap.of("elementId",((RemoteWebElement)src)
				.getId(),"endX",619,"endY",560));
		Thread.sleep(3000);
	}
	
	
	}

