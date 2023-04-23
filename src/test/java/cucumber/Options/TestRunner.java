package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//features=path of feature files, glue=Step Definition package name under curly braces {}, need not to give any  path.
@RunWith(Cucumber.class) //Also we have to tell we will run with Cucumber Junit runner
@CucumberOptions(features="src/test/java/features",
				 glue={"stepDefinitions"},
						 plugin = { "pretty","json:target/cucumber/Cucumber.json",
									"junit:target/cucumber/Cucumber.xml",
									"html:target/cucumber/reports.html"},
                 monochrome = true)  
public class TestRunner {

}
