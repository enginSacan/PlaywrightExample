package com.mes.generic;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProjectProperties {
    private static final String FILE_NAME = "framework.properties";
    private static final String RESOURCES_ROOT_DIR= "./src/test/resources/";
    private static Properties properties;


    public static Properties getRestProperties() {
        File propertiesFile = FileUtils.getFile(RESOURCES_ROOT_DIR,FILE_NAME);
        if (properties == null) {
            properties = loadPropertiesFromFile(propertiesFile);

            if (System.getenv("browser") != null) {
                properties.setProperty("browser",System.getenv("browser"));
            }
        }
        return properties;
    }

    static Properties loadPropertiesFromFile (File propertiesFile) {
        Properties properties = new Properties();
        try {
            FileInputStream stream = FileUtils.openInputStream(propertiesFile);
            try{
                properties.load(stream);
            } catch (Throwable e1) {
                stream.close();
                throw e1;
            }

            stream.close();
            return properties;

        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

}
