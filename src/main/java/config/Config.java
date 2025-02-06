package config;

import utils.PropertyReader;

public class Config {
    private static final PropertyReader reader = new PropertyReader();

    public final static String BROWSER = System.getProperty("browser", getProperty("browser"));
    public final static Boolean IS_REMOTE = Boolean.parseBoolean(System.getProperty("remote", getProperty("remote")));
    public final static Boolean IS_HEADLESS = Boolean.parseBoolean(System.getProperty("headless",  getProperty("headless")));
    public final static String GRID_HUB = getProperty("grid.hub");
    public final static String BASE_URI = getProperty("base.uri");
    public final static String HOME_PAGE = getProperty("home.page");

    private static String getProperty(String key) {
        return reader.getProperty(key);
    }
}
