package com.passnfly.utilities;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.*;
import java.util.*;

import static com.github.webdriverextensions.Bot.takeFullPageScreenshot;
import static com.github.webdriverextensions.Bot.takeScreenshot;

public class Utils {

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd_HH-mm-ss";

    private static final String ENABLE_USER_HOMEDIR = "1";

    private static final String PROPERTIES_PATH = "./src/main/resources/config.properties";
    private static final String PROPERTY_ENABLE_USERHOMEDIR = "fileExamples.download.enable.userHomeDir";
    private static final String PROPERTY_DOWNLOAD_PATH = "fileExamples.download.path";

    /**
     * This function creates a DecimalFormat instance configured to parse / format --> Strings / Numbers
     * with this aspect: 16.290,49
     *
     * @return a DecimalFormat instance preconfigured with spanish notation.
     */
    private static DecimalFormat createDecimalFormat() {
        // Creating a DecimalFormat that fits our requirements --> '16.239,49'
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        String pattern = "#,##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
        return decimalFormat;
    }

    public static Double parseStringNumberToDouble(String number) {

        BigDecimal bigDecimal = BigDecimal.ZERO;
        try {
            // parse the string
            bigDecimal = (BigDecimal) createDecimalFormat().parse(number);
        } catch (ParseException e) {
            //do nothing
        }

        return bigDecimal.doubleValue();
    }

    public static String formatBigDecimalToString(BigDecimal num) {
        return createDecimalFormat().format(num);
    }

    public static String formatDate(Calendar calendar) {
        return formatDate(calendar.getTime(), DEFAULT_DATE_PATTERN);
    }

    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_DATE_PATTERN);
    }

    public static String formatDate(Date date, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public static String formatDateTime(Date date) {
        return formatDate(date, DEFAULT_DATETIME_PATTERN);
    }

    public static Date getTodayDate() {
        return Calendar.getInstance().getTime();
    }

    public static Date getDaysLater(int numDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, numDays);
        return calendar.getTime();
    }

    public static void captureScreenshot(WebDriver driver) {
        String filename = String.format("/Users/Toni/Proyectos/passnfly/tmp/screenshot-%s.png", formatDateTime(new Date()));
        takeScreenshot(filename);
    }

    public static void captureFullPageScreenshot(WebDriver driver) {
        String filename = String.format("/Users/Toni/Proyectos/passnfly/tmp/screenshot-%s.png", formatDateTime(new Date()));
        takeFullPageScreenshot(filename);
    }

    public static By createCssSelector(String cssSelector) {
        return By.cssSelector(cssSelector);
    }

    public static int randomIntBetween(int randomNumberOrigin, int randomNumberBound) {
        return new Random().ints(randomNumberOrigin, randomNumberBound).findAny().getAsInt();
    }

    public static String downloadFileFromUrl(String strUrl) throws IOException {
        URL url = new URL(strUrl);
        String completeFileName = buildCompleteFileName(getUrlFileName(url));

        /*
            Create a new trust manager that trust all certificates:

            FOR DEVELOPMENT PURPOSES ONLY!!!!!
         */
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

        // Activate the new trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            //TODO: manage exception
            e.printStackTrace();
        }

        InputStream in = url.openStream();
        Files.copy(in, Paths.get(completeFileName), StandardCopyOption.REPLACE_EXISTING);

        return completeFileName;
    }

    private static String getUrlFileName(URL url) {
        if (url != null && !StringUtils.isEmpty(url.getPath())) {
            return url.getPath().substring(url.getPath().lastIndexOf("/") + 1);
        } else {
            return ".pdf";
        }
    }

    private static String buildCompleteFileName(String suffix) {
        String path = getConfiguredPath();
        String lUUID = String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
        String filename = String.format("%d_%s_%s", System.currentTimeMillis(), lUUID, suffix);

        return String.format("%s/%s", path, filename);
    }

    private static String getConfiguredPath() {
        Properties properties = null;

        try {
            properties = loadProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String path = "";
        if (properties != null && ENABLE_USER_HOMEDIR.equals(properties.getProperty(PROPERTY_ENABLE_USERHOMEDIR))) {
            //if user_home_dir property is enabled --> We are going to get the path from properties file
            path = properties.getProperty(PROPERTY_DOWNLOAD_PATH);
        } else {
            //Default path construction
            path = System.getProperty("user.home") + "/Downloads";
        }

        return path;
    }

    /***
     * Function to load the properties file.
     *
     * @throws FileNotFoundException When properties file can not be found or read
     */
    private static Properties loadProperties() throws IOException {
        InputStream input = new FileInputStream(PROPERTIES_PATH);
        Properties prop = new Properties();
        prop.load(input);
        return prop;
    }
}
