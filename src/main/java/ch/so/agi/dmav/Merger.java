package ch.so.agi.dmav;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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

    // Bei FixpunkteLV ist es nicht mehr exakt der Modellname, 
    // das diese Daten in zwei XTF bereitgestellt werden.
    // Der Einfachheit halber muss aber die XSL-Transformation
    // die Dateinamen kennen.
    // Und die Modelle brauche ich für das Laden der Ressourcen.
    Map<String, String> keysModels = new HashMap<>()
    {{
         put("DMAVSUP_UntereinheitGrundbuch_V1_0", "DMAVSUP_UntereinheitGrundbuch_V1_0");
         put("DMAV_Toleranzstufen_V1_0", "DMAV_Toleranzstufen_V1_0");
         put("OfficialIndexOfLocalities_V1_0", "OfficialIndexOfLocalities_V1_0");
         put("DMAV_HoheitsgrenzenLV_V1_0", "DMAV_HoheitsgrenzenLV_V1_0");
         put("DMAV_HoheitsgrenzenAV_V1_0", "DMAV_HoheitsgrenzenAV_V1_0");
         put("FixpunkteLV_V1_0_LFP", "FixpunkteLV_V1_0");
         put("FixpunkteLV_V1_0_HFP", "FixpunkteLV_V1_0");
         put("DMAV_Nomenklatur_V1_0","DMAV_Nomenklatur_V1_0");
         put("DMAV_Gebaeudeadressen_V1_0","DMAV_Gebaeudeadressen_V1_0");
         put("DMAV_Dienstbarkeitsgrenzen_V1_0","DMAV_Dienstbarkeitsgrenzen_V1_0");
         put("DMAV_Einzelobjekte_V1_0","DMAV_Einzelobjekte_V1_0");
         put("DMAV_FixpunkteAVKategorie3_V1_0","DMAV_FixpunkteAVKategorie3_V1_0");
         put("DMAV_Bodenbedeckung_V1_0","DMAV_Bodenbedeckung_V1_0");
         put("DMAV_Rohrleitungen_V1_0","DMAV_Rohrleitungen_V1_0");
         put("DMAV_Grundstuecke_V1_0","DMAV_Grundstuecke_V1_0");
         put("KGKCGC_FPDS2_V1_1","KGKCGC_FPDS2_V1_1");
         put("DMAV_DauerndeBodenverschiebungen_V1_0","DMAV_DauerndeBodenverschiebungen_V1_0");
    }};

    public boolean run(Path configFile, String fosnr, Path outputDir) {
        
        // Speicherorte der Dateien aus Config-Datei lesen und BFS-Nummer
        // ersetzen.
        Map<String,String> files = new HashMap<>();
        try {
            files = ConfigParser.read(configFile);
            
            for (Map.Entry<String, String> entry : files.entrySet()) {
                String key = entry.getKey().trim();
                String value = entry.getValue().trim();
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
            //tmpdir = Paths.get("/Users/stefan/tmp/merger");
            
            // Leere (empty baskets) XTF laden, damit jedes
            // Thema (Modell) vorhanden ist. Damit stimmt mein
            // statischer ilimodels-Header.
            for (String model : keysModels.keySet()) {
                loadAndRenameResource(model, tmpdir, fosnr);
            }

            for (Map.Entry<String, String> entry : files.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                
//                System.out.println("key: " +key);
//                System.out.println("value: " +value);
                
                if (value.startsWith("http")) {
                    String fileURL = value;
                    Path finalTargetPath = tmpdir.resolve(key+"."+fosnr+".xtf");

                    if (fileURL.endsWith(".zip")) {
                        URL url = new URL(fileURL);
                        String fileName = Paths.get(url.getPath()).getFileName().toString();
                        
                        Path zipTargetPath = tmpdir.resolve(fileName);

                        try (InputStream in = url.openStream()) {
                            System.err.println("Downloading: " + fileURL);
                            Files.copy(in, zipTargetPath, StandardCopyOption.REPLACE_EXISTING);
                            getXtfFromZip(zipTargetPath, finalTargetPath);
                        }
                        
                        if (!finalTargetPath.toFile().exists()) {
                            System.err.println(key + ": No XTF file found in ZIP file.");
                        }
                    } else {
                        try (InputStream in = new URL(fileURL).openStream()) {
                            Files.copy(in, finalTargetPath, StandardCopyOption.REPLACE_EXISTING);
                        }
                    }                    
                } else {
                    Path xtfFile = Paths.get(tmpdir.toAbsolutePath().toString(), key+"."+fosnr+".xtf");
                    if (value.endsWith(".zip")) {
                        getXtfFromZip(Paths.get(value), xtfFile);
                        if (!xtfFile.toFile().exists()) {
                            System.err.println(key + ": No XTF file found in ZIP file.");
                        }
                    } else {
                        Files.copy(Paths.get(value), xtfFile, StandardCopyOption.REPLACE_EXISTING);                                            
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
                        
        // XML-Skeleton und XSL-Tranformation aus Resourcen
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
        
//        try {
//            Thread.sleep(60000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

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
    
    private static void loadAndRenameResource(String resourceName, Path targetDir, String replacement) throws IOException {
        String resourceFileName = resourceName + ".empty.xtf";

        try (InputStream resourceStream = Merger.class.getClassLoader().getResourceAsStream(resourceFileName)) {
            if (resourceStream == null) {
                throw new FileNotFoundException("Resource file not found: " + resourceFileName);
            }

            String renamedFileName = resourceFileName.replace("empty", replacement);

            Path targetFilePath = targetDir.resolve(renamedFileName);
            Files.copy(resourceStream, targetFilePath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
    
    private static void getXtfFromZip(Path zipFile, Path xtfFile) throws IOException {
        Path tmpdir = Files.createTempDirectory("dmav_zip_");
        ZipInputStream zis = new ZipInputStream(Files.newInputStream(zipFile));
        ZipEntry zentry;
        while ((zentry = zis.getNextEntry()) != null) {                                
            File outputFile = new File(tmpdir.toFile().getAbsolutePath(), zentry.getName());
            
            if (zentry.isDirectory()) {
                outputFile.mkdirs();
            } else {
                outputFile.getParentFile().mkdirs();
                try (FileOutputStream fos = new FileOutputStream(outputFile);
                        BufferedOutputStream bos = new BufferedOutputStream(fos)) {

                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    while ((bytesRead = zis.read(buffer)) != -1) {
                        bos.write(buffer, 0, bytesRead);
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new IOException("Could not store file " + zipFile.getFileName().toString() + ". Please try again!");
                }
                if (outputFile.toString().toLowerCase().endsWith(".xtf")) {
                    Files.copy(outputFile.toPath(), xtfFile, StandardCopyOption.REPLACE_EXISTING);                                  
                    break;
                }
            }
            zis.closeEntry();
        }
        zis.close();
   
    }

}
