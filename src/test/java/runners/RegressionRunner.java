package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features ="src/test/resources/features",
        glue = "steps",
        dryRun = false,
        monochrome = true,
        tags = "@regression" ,
        plugin = {"pretty", "html:target/cucumber.html"}
)
// In practice, we will have multiple Runner classes

public class RegressionRunner {
}
