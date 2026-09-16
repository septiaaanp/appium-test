package driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * DriverManager - singleton responsible for creating and holding the AndroidDriver instance.
 * All page objects and step definitions consume the driver via DriverManager.getDriver().
 */
public class DriverManager {

    private static AndroidDriver driver;

    private DriverManager() {}

    public static AndroidDriver getDriver() {
        return driver;
    }

    public static void initDriver() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Android Emulator");
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.indomaret.klikindomaret");
        options.setAppActivity("com.indomaret.klikindomaret.ui.SplashScreenActivity");
        options.setNoReset(false); // Ensures a fresh app state for every test run
        options.setAutoGrantPermissions(true);
        options.setNewCommandTimeout(Duration.ofSeconds(60));

        URL appiumServer = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(appiumServer, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
