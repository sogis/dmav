package ch.so.agi.dmav;

import org.interlis2.validator.Validator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import ch.ehi.basics.settings.Settings;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MergerTest {

    private MockWebServer mockWebServer;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(8181);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void mergeLocalFilesOk(@TempDir Path tempDir) throws IOException {
        // Run test
        Merger merger = new Merger();
        Path myTempDir = Path.of("/Users/stefan/tmp/");
        boolean ret = merger.run(Paths.get("src/test/data/merger/myconfig_local.ini"), "449", tempDir);
    
        // Validate result
        assertTrue(ret);
        
        Path xtfFile = tempDir.resolve("DMAV.449.xtf");
        Settings settings = new Settings();
        Path logFile = tempDir.resolve("ilivaliator.log").toAbsolutePath();
        settings.setValue(Validator.SETTING_LOGFILE, logFile.toString());
        
        boolean valid = Validator.runValidation(xtfFile.toString(), settings);
        assertTrue(valid);
        
        String content = Files.readString(logFile);
        assertTrue(content.contains("3 objects in CLASS DMAV_FixpunkteAVKategorie2_V1_0.FixpunkteAVKategorie2.LFP2"));
        assertTrue(content.contains("3 objects in CLASS DMAV_PLZ_Ortschaft_V1_0.PLZ_Ortschaft.PLZ"));
    }
    
    @Test
    public void mergeExternalFilesOk(@TempDir Path tempDir) throws IOException {
        // Prepare
        byte[] fileContent = Files.readAllBytes(Paths.get("src/test/data/merger/DMAV_PLZ_Ortschaft_V1_0.449.xtf"));

        mockWebServer.enqueue(new MockResponse()
                .setBody(new String(fileContent)) 
                .addHeader("Content-Type", "application/xml")
                .setResponseCode(200));
        
        // Run test
        Merger merger = new Merger();
        boolean ret = merger.run(Paths.get("src/test/data/merger/myconfig_web.ini"), "449", tempDir);

        // Validate result
        assertTrue(ret);
        
        Path xtfFile = tempDir.resolve("DMAV.449.xtf");
        Settings settings = new Settings();
        Path logFile = tempDir.resolve("ilivaliator.log").toAbsolutePath();
        settings.setValue(Validator.SETTING_LOGFILE, logFile.toString());
        
        boolean valid = Validator.runValidation(xtfFile.toString(), settings);
        assertTrue(valid);
        
        String content = Files.readString(logFile);
        assertTrue(content.contains("3 objects in CLASS DMAV_FixpunkteAVKategorie2_V1_0.FixpunkteAVKategorie2.LFP2"));
        assertTrue(content.contains("3 objects in CLASS DMAV_PLZ_Ortschaft_V1_0.PLZ_Ortschaft.PLZ"));
    }

}
