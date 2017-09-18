/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunglv.sqlite.util;

import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author TUNGLV
 */
public class Config {

    static Logger logger = Logger.getLogger(Config.class.getName());
    public static final String[] CONFIG_LOCATIONS_FILE = new String[]{"config.properties", "config.properties"};
    private static Properties props = null;
    private static Config instance = new Config();

    static {
        try {
            props = new Properties();
            props.load(findResourceAsStream(CONFIG_LOCATIONS_FILE));
        } catch (Exception e) {
            logger.error("ERROR Loading config parameters: ", e);
        }
    }
    public static final String DB_NAME = get("db_name");

    public static InputStream findResourceAsStream(String[] fileLocations) {
        for (String fl : fileLocations) {
            try {
                InputStream inputStream = getResourceAsStream(fl);
                if (inputStream != null) {
                    return inputStream;
                }
            } catch (Exception e) {
                logger.error("Config.findResourceAsStream(): Error: " + e);
            }
        }
        return null;
    }

    public static InputStream getResourceAsStream(String filePath) {
        return instance.getClass().getClassLoader().getResourceAsStream(filePath);
    }

    public static String get(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            logger.error("Get Null config parameter " + key);
        } else {
            value = value.trim();
        }
        return value;
    }
}
