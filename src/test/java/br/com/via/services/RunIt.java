package br.com.via.services;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@CucumberOptions(
        features = "src/test/features",
        glue = "br.com.via",
        plugin = {"pretty","summary",
                "html:target/cucumber-reports.html",
                "json:target/json_result.json"
        },
        tags = "not @manual and @t",
        dryRun = false
)
@RunWith(Cucumber.class)
public class RunIt {

}
