package ch.so.agi.dmav;

import org.interlis2.validator.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import ch.ehi.basics.settings.Settings;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SplitterTest {

    @Test
    public void splitOk(@TempDir Path tempDir) throws IOException {
        // Run test
        Splitter splitter =  new Splitter();
        boolean ret = splitter.run(Paths.get("src/test/data/splitter/DMAV.449.xtf"), "449", tempDir);
        
        // Validate result
        assertTrue(ret);
        
        {
            Path xtfFile = tempDir.resolve("DMAV_PLZ_Ortschaft_V1_0.449.xtf");
            Settings settings = new Settings();
            Path logFile = tempDir.resolve("ilivaliator.log").toAbsolutePath();
            settings.setValue(Validator.SETTING_LOGFILE, logFile.toString());
            
            boolean valid = Validator.runValidation(xtfFile.toString(), settings);
            assertTrue(valid);
            
            String content = Files.readString(logFile);
            assertFalse(content.contains("3 objects in CLASS DMAV_FixpunkteAVKategorie2_V1_0.FixpunkteAVKategorie2.LFP2"));
            assertTrue(content.contains("3 objects in CLASS DMAV_PLZ_Ortschaft_V1_0.PLZ_Ortschaft.PLZ"));
        }

        {
            Path xtfFile = tempDir.resolve("DMAV_FixpunkteAVKategorie2_V1_0.449.xtf");
            Settings settings = new Settings();
            Path logFile = tempDir.resolve("ilivaliator.log").toAbsolutePath();
            settings.setValue(Validator.SETTING_LOGFILE, logFile.toString());
            
            boolean valid = Validator.runValidation(xtfFile.toString(), settings);
            assertTrue(valid);
            
            String content = Files.readString(logFile);
            assertTrue(content.contains("3 objects in CLASS DMAV_FixpunkteAVKategorie2_V1_0.FixpunkteAVKategorie2.LFP2"));
            assertFalse(content.contains("3 objects in CLASS DMAV_PLZ_Ortschaft_V1_0.PLZ_Ortschaft.PLZ"));
        }

    }
}
