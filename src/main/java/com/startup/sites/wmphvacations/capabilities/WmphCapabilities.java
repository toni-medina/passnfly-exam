package com.startup.sites.wmphvacations.capabilities;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WmphCapabilities extends DesiredCapabilities {

    public WmphCapabilities() {

        ChromeOptions options = new ChromeOptions();
        super.setJavascriptEnabled(true);
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        options.addArguments("--user-agent=" + "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
        setCapability(ChromeOptions.CAPABILITY, options);


/*
        Map<String, Object> preferences = new Hashtable<>();
        preferences.put("profile.default_content_settings.popups", 0);
        preferences.put("download.prompt_for_download", "false");
        String downloadsPath = System.getProperty("user.home") + "/Downloads";
        //preferences.put("download.default_directory", loadSystemPropertyOrDefault("fileDownloadPath", downloadsPath));
        preferences.put("plugins.plugins_disabled", new String[]{"Adobe Flash Player", "Chrome PDF Viewer"});

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", preferences);

        setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        setCapability(ChromeOptions.CAPABILITY, options);
 */

/*
        Properties properties = null;
        try {
            properties = loadProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
        String binaryPath = properties.getProperty("webdriver.chrome.binary");

        if (binaryPath == null) {
            throw new RuntimeException("Missing property : " + "webdriver.chrome.binary");
        }

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.setBinary(binaryPath);
        options.addArguments("--headless");
        options.addArguments("--user-agent=" + "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");

        setCapability(ChromeOptions.CAPABILITY, options);
*/
    }


/*
    /**
     * Function to read input value from properties file.
     * @return
     * /
    protected static String readInputFromPropertiesFile(String question) {
        String input = null;
        try {
            Properties prop = loadProperties();
            input = prop.getProperty(String.format("input.value.%s", question));
        } catch (IOException e) {
            //TODO: manage exception!
            e.printStackTrace();
        }
        return input;
    }

    /***
     * Function to load the properties file.
     *
     * @throws FileNotFoundException When properties file can not be found or read
     * /
    private static Properties loadProperties() throws IOException {
        InputStream input = new FileInputStream(PROPERTIES_PATH);
        Properties prop = new Properties();
        prop.load(input);
        return prop;
    }
    */
}
