import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameters({
        @ConfigurationParameter(
                key = io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME,
                value = "pretty, html:target/cucumber.html, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"),
        @ConfigurationParameter(
                key = io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME,
                value = "steps")})
public class TestRunner {
}
