package com.mes;

import org.junit.platform.suite.api.*;

import static io.cucumber.core.options.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("com.mes")
@SelectClasspathResource("com.mes.steps")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,value = "pretty")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,value = "json:target/cucumber-report/cucumber.json, html:target/cucumber-report/cucumber.html")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.mes.steps")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/features")
public class RunCucumberTest {
}