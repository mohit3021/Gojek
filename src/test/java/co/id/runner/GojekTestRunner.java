package co.id.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/usr/features/api" }, glue = {
		"co.id.stepdefinations" }, plugin = {
				"com.cucumber.listener.ExtentCucumberFormatter:target/test1/cucumber-reports/report.json",
				"html:target/test1/cucumber-reports/cucumber.html",
				"com.cucumber.listener.ExtentCucumberFormatter:target/test1/cucumber-parallel/report.json",
				"json:target/test1/cucumber-parallel/cucumber.json",
				"html:target/test1/cucumber-parallel/cucumber.html" }, tags = "@compareapi", monochrome = true, strict = true, dryRun = false)
public class GojekTestRunner {


}
