package ru.sin666.sbt.dir_scan.impls;

import ru.sin666.sbt.dir_scan.FileProcessor;
import ru.sin666.sbt.dir_scan.Filler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileProcessorSimpleImpl implements FileProcessor {

    private static Logger logger = Logger.getLogger(FileProcessorSimpleImpl.class.getName());


    private final Filler filler;
    private final String pattern;

    public FileProcessorSimpleImpl(Filler filler, String pattern) {
        this.filler = filler;
        this.pattern = pattern;
    }

    @Override
    public Optional<String> process(Path file) {
        BasicFileAttributes basicFileAttributes = null;
        try {
            basicFileAttributes = Files.readAttributes(file,BasicFileAttributes.class);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Cannot read attributes for file "+file.toString());
        }
        return basicFileAttributes == null? Optional.empty():
                Optional.of(filler.fillPattern(pattern,file,basicFileAttributes));
    }

}
