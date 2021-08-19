package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;


public class ResourceUtil {



    public static Properties properties(){
        try {
        FileInputStream reader;
        Properties prop=new Properties();
        reader = new FileInputStream("src/test/application.properties");
        prop.load(reader);
        return prop;
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }

}
