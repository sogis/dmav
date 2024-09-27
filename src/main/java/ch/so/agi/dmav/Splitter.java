package ch.so.agi.dmav;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

public class Splitter {

    public boolean run(Path inputFile, String fosnr, Path outputDir) {
        
        // XSL-Tranformation aus Resourcen
        // laden.
        Path tmpdir = null;
        Path xslFile = null;
        try {
            tmpdir = Files.createTempDirectory("dmav_");
            xslFile = Utils.loadFile("split.xsl", tmpdir);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // XSL-Transformation durchführen
        Path outputXmlFile = outputDir.resolve("DMAV_split_logging.xtf");
        
        Processor proc = new Processor(false);
        XsltCompiler comp = proc.newXsltCompiler();
        XsltTransformer trans = null;
        try {
            XsltExecutable exp = comp.compile(new StreamSource(xslFile.toFile()));
            XdmNode source = proc.newDocumentBuilder().build(new StreamSource(inputFile.toFile()));
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
//        try {
//            Utils.deleteDirectory(tmpdir);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
        
        return true;
    }
}
