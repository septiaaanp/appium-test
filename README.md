# Klik Indomaret Appium Automation Test

Automation testing framework for the Klik Indomaret Android application, specifically focusing on the "Virtual Account Payment" scenario. Built with Java, Appium, Cucumber (BDD), and Maven.

## Tech Stack & Tools
- **Language:** Java 11+
- **Automation Framework:** Appium 8.x (UiAutomator2)
- **BDD Framework:** Cucumber 7.x
- **Build Tool:** Maven
- **Test Runner:** JUnit 4

## Architecture & Design Pattern
This project implements the **Page Object Model (POM)** design pattern to ensure code maintainability, readability, and reusability:
- **`pages/`**: Contains UI locators and interactions specific to each screen. Uses `@AndroidFindBy` for element initialization.
- **`stepDef/`**: Contains the Cucumber step definitions that map Gherkin syntax to Java code.
- **`BasePage.java`**: A generic parent class for all Page Objects. It initializes the `AppiumFieldDecorator` and provides highly reusable methods (e.g., `clickButtonByLabel`, `selectOptionByLabel`) to avoid hardcoded locators.
- **`driver/DriverManager.java`**: Implements the Singleton pattern to ensure only a single instance of `AndroidDriver` is active during the test execution. It assumes the application is already installed on the target device (`setNoReset(false)`).
- **`hooks/Hooks.java`**: Manages the Appium session lifecycle, ensuring the driver is properly initialized before each scenario and gracefully terminated afterward.

## Project Directory Structure
```text
appium-test/
├── pom.xml
└── src/
    ├── main/java/
    │   ├── driver/          # Singleton driver initialization
    │   ├── pages/           # Page Object classes (UI locators & actions)
    │   └── utils/           # Helper classes (e.g., PriceCalculator)
    └── test/
        ├── java/
        │   ├── hooks/       # Cucumber hooks for setup/teardown
        │   ├── stepDef/     # Step definition implementations
        │   └── TestRunner.java
        └── resources/
            └── features/    # BDD Gherkin scenarios (e.g., payment.feature)
```

## Prerequisites & Execution Setup
To run this project locally on a real device or emulator:

1. Ensure **Node.js** and **Appium Server** are installed.
2. Start the Appium server on default port `4723`:
   ```bash
   appium
   ```
3. Ensure your Android device/emulator is connected and recognized:
   ```bash
   adb devices
   ```
4. Install the **Klik Indomaret** application manually on your device.
5. Execute the test using Maven from the project root directory:
   ```bash
   mvn clean test
   ```
