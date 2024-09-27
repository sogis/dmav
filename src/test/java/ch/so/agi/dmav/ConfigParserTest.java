package ch.so.agi.dmav;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Paths;
import java.util.Map;

public class ConfigParserTest {

    @Test
    void parseConfigFileOk() throws Exception {
        Map<String,String> properties = ConfigParser.read(Paths.get("src/test/data/merger/myconfig_local.ini"));
        
        assertEquals("src/test/data/merger/DMAV_FixpunkteAVKategorie2_V1_0.${fosnr}.xtf", properties.get("DMAV_FixpunkteAVKategorie2_V1_0"));
        assertEquals("src/test/data/merger/DMAV_PLZ_Ortschaft_V1_0.${fosnr}.xtf", properties.get("DMAV_PLZ_Ortschaft_V1_0"));
        
        assertEquals(2, properties.size());
    }
}
