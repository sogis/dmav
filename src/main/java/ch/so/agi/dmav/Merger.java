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
            
            // substituieren
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        
        return true;
    }    
}
