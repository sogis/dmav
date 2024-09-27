package ch.so.agi.dmav;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class Utils {

    public static Path loadFile(String fileName, Path outputFolder) throws IOException  {
        Path outFile = Paths.get(outputFolder.toFile().getAbsolutePath(), fileName);
        InputStream is = Utils.class.getResourceAsStream("/"+fileName);
        Files.copy(is, outFile, StandardCopyOption.REPLACE_EXISTING);        
        return outFile;
    }
    
    public static void deleteDirectory(Path directory) throws IOException {
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

}
