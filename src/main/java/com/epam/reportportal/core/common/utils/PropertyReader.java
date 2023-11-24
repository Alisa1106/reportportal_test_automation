package com.epam.reportportal.core.common.utils;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Log4j2
public final class PropertyReader {

    public static Properties readProperties(String fileName) {
        Properties properties = new Properties();
        InputStream stream = null;
        InputStreamReader reader = null;
        try {
            String filePath = String.format("src/main/resources/%s.properties", fileName);
            stream = new FileInputStream(filePath);
            reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            properties.load(reader);
        } catch (IOException e) {
            log.fatal(String.format("ERROR: %s", e.getMessage()));
        } finally {
            try {
                assert stream != null;
                stream.close();
            } catch (IOException e) {
                log.fatal(String.format("ERROR: %s", e.getMessage()));
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.fatal(String.format("ERROR: %s", e.getMessage()));
                }
            }
        }
        return properties;
    }

    public static String getProperties(String filename, String propertyName) {
        Properties properties = PropertyReader.readProperties(filename);
        return properties.getProperty(propertyName);
    }
}
