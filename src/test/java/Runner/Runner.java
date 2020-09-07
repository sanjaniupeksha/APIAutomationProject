package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

    @RunWith(Cucumber.class)
    @CucumberOptions(
            plugin = {"pretty", "html:target/cucumber.html","json:target/cucumberReport.json"},
            monochrome = true,
            features = {"src/test/java/Features"},
            glue={"Steps"}
    )
    public class Runner {

    }
