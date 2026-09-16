import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * TestRunner - Cucumber JUnit runner.
 * features: path to .feature files
 * glue: packages containing step definitions and hooks
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDef", "hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber-reports/report.json"
        },
        monochrome = true
)
public class TestRunner {
}
