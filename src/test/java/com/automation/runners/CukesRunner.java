package com.automation.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src/test/resources/features",
        glue = "com/automation/step_definitions",
        plugin = {
                "json:target/cucumber.json"
        },
        dryRun = false
)
public class CukesRunner {
}