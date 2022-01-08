package runners;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/restAPI",
        glue = "stepDefinitions",
        monochrome = true,
        strict = true
)

public class TestRunner {
}
