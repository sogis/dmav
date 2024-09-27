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

        @Option(names = "--fosnr", description = "The fos number of the municipality (FOSNR)", required = true)
        private String fosnr;

        @Option(names = "--out", description = "Output directory path", required = true)
        private Path outputDir;

        @Override
        public Integer call() throws Exception {            
            Merger merger = new Merger();
            boolean ret = merger.run(config, fosnr, outputDir);            
            
            return ret ? 1 : 0;
        }
    }
    
    @Command(name = "split", description = "Split files based on input and options")
    static class SplitCommand implements Callable<Integer> {

        @Option(names = "--fosnr", description = "The fos number of the municipality (FOSNR)", required = true)
        private String fosnr;

        @Option(names = "--input", description = "Input file to split", required = true)
        private Path inputFile;

        @Option(names = "--out", description = "Output directory path", required = true)
        private Path outputDir;

        @Override
        public Integer call() throws Exception {
            Splitter splitter = new Splitter();
            boolean ret = splitter.run(inputFile, fosnr, outputDir);

            return ret ? 1 : 0;
        }
    }
}
