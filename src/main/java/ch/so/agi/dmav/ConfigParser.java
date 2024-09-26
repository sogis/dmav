package ch.so.agi.dmav;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class ConfigParser {
    public static Map<String,String> read(Path configFile) throws IOException {        
        Properties properties = new Properties();

        try (FileInputStream inputStream = new FileInputStream(configFile.toString())) {
            properties.load(inputStream);

            Map<String, String> map = new HashMap<>();
            for (String key : properties.stringPropertyNames()) {
                map.put(key, properties.getProperty(key));
            }

            return map;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
    } 
}
