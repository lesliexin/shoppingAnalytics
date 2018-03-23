package live.cibc.shoppinganalytics.Utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesParser {

    public static Properties getProperties(String propertiesName){
        try {
            InputStream inputStream = new FileInputStream(propertiesName);
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (Exception e) {
            System.out.println("couldn't make properties file" + propertiesName);
            return null;
        }
    }
}
