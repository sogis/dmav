package ch.so.agi.dmav;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Merger {

    public boolean run(Path configFile, String fosnr) {
        
        Map<String,String> files = new HashMap<>();
        try {
            files = ConfigParser.read(configFile);
            
            for (Map.Entry<String, String> entry : files.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                String newValue = value.replace("${fosnr}", fosnr);
                files.put(key, newValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        System.out.println(files);
        
        
        return true;
    }    
}
