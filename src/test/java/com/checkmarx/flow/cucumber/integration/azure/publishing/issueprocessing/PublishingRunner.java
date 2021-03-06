package com.checkmarx.flow.cucumber.integration.azure.publishing.issueprocessing;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/cucumber/features/integrationTests/azure/publishing-issue-processing.feature",
        tags = "not @Skip")
public class PublishingRunner {
}
