package ru.sin666.sbt.dir_scan.impls;

import ru.sin666.sbt.dir_scan.FileProcessor;
import ru.sin666.sbt.dir_scan.FileStreamProcessor;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

public class FileStreamProcessorParallelImpl implements FileStreamProcessor {

    private final FileProcessor fileProcessor;

    public FileStreamProcessorParallelImpl(FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    @Override
    public void process(Stream<Path> fileStream, PrintWriter printWriter) {

        fileStream
                .filter(Files::isRegularFile)
                .parallel()
                .map(fileProcessor::process)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEachOrdered(printWriter::print);
    }
}
