package ch.so.agi.dmav;

import java.nio.file.Path;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
        name = "dmavtool", 
        description = "A tool to merge and split DMAV transfer files.", 
        mixinStandardHelpOptions = true,
        subcommands = {
                App.MergeCommand.class, 
                App.SplitCommand.class}
        )
public class App implements Callable<Integer> {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }


    @Override
    public Integer call() throws Exception {
        System.err.println("Please use a subcommand: merge or split.");
        return 0;
    }

    @Command(name = "merge", description = "Merge files based on configuration and options")
    static class MergeCommand implements Callable<Integer> {

        @Option(names = "--config", description = "Path to the configuration file", required = true)
        private Path config;

        @Option(names = "--fosnr", description = "The file order number (FOSNR)", required = false)
        private String fosnr;

        @Option(names = "--out", description = "Output file path", required = true)
        private Path outputFile;

        @Override
        public Integer call() throws Exception {
            // Hier wäre die Logik zum Merger der Dateien basierend auf den Optionen
            System.out.println("Merging files with config: " + config +
                    ", FOSNR: " + fosnr + ", Output: " + outputFile);
            // Beispielhafte Implementierung
            return 0;
        }
    }
    
    @Command(name = "split", description = "Split files based on input and options")
    static class SplitCommand implements Callable<Integer> {

        @Option(names = "--fosnr", description = "The file order number (FOSNR)", required = true)
        private int fosnr;

        @Option(names = "--input", description = "Input file to split", required = true)
        private Path inputFile;

        @Override
        public Integer call() throws Exception {
            // Hier wäre die Logik zum Splitten der Datei basierend auf den Optionen
            System.out.println("Splitting file: " + inputFile +
                    ", FOSNR: " + fosnr);
            // Beispielhafte Implementierung
            return 0;
        }
    }
}
