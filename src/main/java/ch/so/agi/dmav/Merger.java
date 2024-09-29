package ch.so.agi.dmav;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.QName;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XdmAtomicValue;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XdmValue;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;
import net.sf.saxon.s9api.XsltTransformer;

public class Merger {

    public boolean run(Path configFile, String fosnr, Path outputDir) {
        
        // Speicherorte der Dateien aus Config-Datei lesen und BFS-Nummer
        // ersetzen.
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
        
        // Dateien in temporäres Verzeichnis kopieren. 
        // Ggf. vorgängig herunterladen.
        Path tmpdir = null;
        try {
            tmpdir = Files.createTempDirectory("dmav_");
            for (Map.Entry<String, String> entry : files.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                
                if (value.startsWith("http")) {
                    String fileURL = value;
                    Path targetPath = tmpdir.resolve(key+"."+fosnr+".xtf");
                    
                    try (InputStream in = new URL(fileURL).openStream()) {
                        Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                } else {
                    Path xtfFile = Paths.get(tmpdir.toAbsolutePath().toString(), key+"."+fosnr+".xtf");
                    Files.copy(Paths.get(value), xtfFile, StandardCopyOption.REPLACE_EXISTING);                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        // XML-Skeletion und XSL-Tranformation aus Resourcen
        // laden.
        Path xslFile = null;
        Path inputXmlFile = null;
        Path outputXmlFile = outputDir.resolve("DMAV."+fosnr+".xtf");
        try {
            xslFile = Utils.loadFile("merge.xsl", tmpdir);
            inputXmlFile = Utils.loadFile("DMAV_Skeleton.xtf", tmpdir);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        // XSL-Transformation durchführen
        Processor proc = new Processor(false);
        XsltCompiler comp = proc.newXsltCompiler();
        XsltTransformer trans = null;
        try {
            XsltExecutable exp = comp.compile(new StreamSource(xslFile.toFile()));
            XdmNode source = proc.newDocumentBuilder().build(new StreamSource(inputXmlFile.toFile()));
            Serializer outXml = proc.newSerializer(outputXmlFile.toFile());
            trans = exp.load();
            trans.setInitialContextNode(source);
            trans.setDestination(outXml);
            trans.setParameter(new QName("fosnr"), (XdmValue) XdmAtomicValue.makeAtomicValue(fosnr));
            trans.transform();
        } catch (SaxonApiException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (trans != null) {                
                trans.close();
            }
        }
        
        // Temp-Daten/-Verzeichnis aufräumen
        try {
            Utils.deleteDirectory(tmpdir);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }    
}
