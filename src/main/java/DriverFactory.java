import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public AndroidDriver setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");;
        desiredCapabilities.setCapability("appium:automationName", "UIAutomator2");


        URL remoteUrl = new URL("http://127.0.0.1:4723");

        AndroidDriver driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;

//        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}

