package ru.sin666.sbt.dir_scan.impls;

import ru.sin666.sbt.dir_scan.FileStreamProcessor;
import ru.sin666.sbt.dir_scan.FolderScanner;
import ru.sin666.sbt.dir_scan.ScannerContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.logging.Level.WARNING;


public class FolderScannerSimpleImpl implements FolderScanner {

    private static Logger logger = Logger.getLogger(FolderScannerSimpleImpl.class.getName());

    private FileStreamProcessor fileStreamProcessor;

    public FolderScannerSimpleImpl(FileStreamProcessor fileStreamProcessor) {
        this.fileStreamProcessor = fileStreamProcessor;
    }


    @Override
    public void processFolders(final ScannerContext context, final PrintWriter printWriter) {
         context.foldersToInclude()
                .stream()
                .filter(path -> !context.foldersToExclude().contains(path))
                .filter(Files::isDirectory)
                .forEachOrdered(path -> processFolder(path, printWriter));
    }



    private void processFolder(Path path, PrintWriter printWriter) {
        Stream<Path> pathStream;
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
            pathStream = StreamSupport.stream(directoryStream.spliterator(), false);
            fileStreamProcessor.process(pathStream, printWriter);
        } catch (IOException e) {
            logger.log(WARNING,"Couldn't read folder " + path);
        }
    }

}
