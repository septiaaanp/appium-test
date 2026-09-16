package hooks;

import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.net.MalformedURLException;

/**
 * Hooks - Cucumber lifecycle hooks.
 * @Before: initialise Appium driver.
 * @After: quit driver.
 */
public class Hooks {

    @Before
    public void setUp() throws MalformedURLException {
        DriverManager.initDriver();
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
