package BaseClass;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
<<<<<<< HEAD
@CucumberOptions(features = { "." }, glue = { "stepDefination" }, tags = ("@FreeLogin"),
=======
@CucumberOptions(features = { "." }, glue = { "stepDefination" }, //tags = ("@EbooksGoogle"),
>>>>>>> ea9835a07acbe753ac386794c15c6d90b0f106bc
plugin = { "pretty","html:target/site/cucumber-pretty", "json:target/cucumber/cucumber.json", "usage:target/usage.jsonx",
		"junit:target/cucumber.xml" }

)

public class TestRun {

	@BeforeClass
	public static void beforeClass() throws Exception {
		SetUPClass.before_Class();
	}

	@AfterClass
	public static void afterClass() throws Exception {
		SetUPClass.after_Class();
	}

}
