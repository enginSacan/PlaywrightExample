package com.mes.steps;

import com.microsoft.playwright.*;
import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import static com.mes.steps.PlaywrightManager.*;

public class TestContext {
    private static final Logger logger = LogManager.getLogger(TestContext.class);
    @Before
    public static void launchBrowser() {
        initializePlaywright();
        newPage();
    }

    @After
    public void afterEach(Scenario scenario){
        if(scenario.isFailed()){
            final byte[] screenshot = getPage().screenshot(new Page.ScreenshotOptions()
                    .setFullPage(true)
                    .setPath(Paths.get("./target/cucumber-report/screenshot.png")));
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        try {
            File logFile = new File("./target/cucumber-report/UI-test.log");
            String logContents = FileUtils.readFileToString(logFile, StandardCharsets.UTF_8);
            scenario.attach(logContents, "text/plain", "logs");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        closePage();
    }
    @AfterAll
    public static void closeBrowser() {
        closeAll();
    }

}
