package by.bsuir;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;


public class ApplicationProperties {

    private final static Logger LOGGER = LoggerFactory.getLogger(ApplicationProperties.class);

    private final Properties properties;

    public ApplicationProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        }
        catch (IOException e) {
            LOGGER.error("Can't read properties from app.properties");
        }
    }

    public String getMaxLength() {
        return properties.getProperty("MAX_LENGTH");
    }

    public String getOnlyLettersRegEx() {
        return properties.getProperty("ONLY_LETTERS");
    }

    public String getOnlyNumbersRegEx() {
        return properties.getProperty("ONLY_NUMBERS");
    }

    public String getValidPassRegEx() {
        return properties.getProperty("VALID_PASSWORD");
    }

    public String getAmountOfRecordsPerPage() {
        return properties.getProperty("RECORDS_ON_PAGE");
    }

    public String getAmountOfLessRecordsPerPage() {
        return properties.getProperty("LESS_RECORDS_ON_PAGE");
    }

    public String getAmountOfMoreRecordsPerPage() {
        return properties.getProperty("MORE_RECORDS_ON_PAGE");
    }
}