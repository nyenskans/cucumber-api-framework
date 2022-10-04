package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "APIsteps",
        // steps will not work here because the precondition in Hooks is not what we need (it's open/close browser),
        // instead we need to create a new package APIsteps
        dryRun = false,
        monochrome = true,
        tags = "@api",
        plugin = {"pretty"}
)
public class APIRunner {

}
