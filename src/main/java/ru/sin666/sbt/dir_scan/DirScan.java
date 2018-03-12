package ru.sin666.sbt.dir_scan;

import ru.sin666.sbt.dir_scan.impls.*;
import ru.sin666.sbt.dir_scan.impls.fillers.BasicFiller;
import ru.sin666.sbt.dir_scan.impls.fillers.BatchFiller;
import ru.sin666.sbt.dir_scan.utils.JobLauncher;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DirScan {

    private static final String DEFAULT_OUTPUT_ENCODING = "UTF-8";
    private static final String DEFAULT_DATE_FORMAT = "yyyy.MM.dd";
    private static final String PATTERN = "[\nfile = %s \ndate =  %s \nsize = %s]";
    private static final String DOT = ".";
    private static final long DOT_INTERVAL_MSEC = 6 * 1000;
    private static final String PIPE_CHAR = "|";
    private static final long PIPE_CHAR_INTERVAL_MSEC = 3 * 60 * 1000;
    private static final String DEFAULT_OUTPUT_FILE_NAME_PATTERN = " dir_scan_result_%s-%s.txt";


    private static Logger logger = Logger.getLogger(DirScan.class.getName());

    private JobLauncher jobLauncher;
    private ScannerContext scannerContext;
    private FolderScanner folderScanner;

    public static void main(String[] args) {
        ScannerContext context = new ScannerContextImpl(args);
        (new DirScan(context)).run();

    }


    private DirScan(ScannerContext scannerContext) {
        this.scannerContext = scannerContext;
        jobLauncher = new JobLauncher();
        folderScanner = createFolderScanner();
    }

    private void run() {
        launchScheduledTasks(jobLauncher);

        Path outputPath = createOutputPath(createDefaultFileName(DEFAULT_OUTPUT_FILE_NAME_PATTERN));

        try (PrintWriter output = createOutput(outputPath, DEFAULT_OUTPUT_ENCODING)) {

            folderScanner.processFolders(scannerContext, output);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Couldn't open output file " + outputPath);
        }

        jobLauncher.stopAll();
    }


    private PrintWriter createOutput(Path outputPath, String encoding) throws IOException {
        return new PrintWriter(Files.newBufferedWriter(
                outputPath,
                Charset.forName(encoding),
                StandardOpenOption.CREATE_NEW));
    }

    private FolderScanner createFolderScanner() {
        Filler filler = new BatchFiller(new BasicFiller(DEFAULT_DATE_FORMAT));
        FileProcessor fileProcessor = new FileProcessorSimpleImpl(filler, PATTERN);
        FileStreamProcessor fileStreamProcessor = new FileStreamProcessorParallelImpl(fileProcessor);
        return new FolderScannerSimpleImpl(fileStreamProcessor);
    }

    private void launchScheduledTasks(JobLauncher jobLauncher) {
        jobLauncher.launchJob(DOT_INTERVAL_MSEC, () -> System.out.println(DOT));
        jobLauncher.launchJob(PIPE_CHAR_INTERVAL_MSEC, () -> System.out.println(PIPE_CHAR));
    }

    private Path createOutputPath(String fileName) {
        Path currentRelativePath = Paths.get("");
        String currentFolder = currentRelativePath.toAbsolutePath().toString();
        return Paths.get(currentFolder + "/" + fileName);
    }

    private String createDefaultFileName(String pattern) {
        return String.format(pattern,
                Long.toHexString(Thread.currentThread().getId()),
                Long.toHexString(System.currentTimeMillis()));
    }

}
