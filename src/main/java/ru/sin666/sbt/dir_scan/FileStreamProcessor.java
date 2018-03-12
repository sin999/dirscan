package ru.sin666.sbt.dir_scan;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Котракт на рбработку потока файлов
 */
public interface FileStreamProcessor {
    void process(Stream<Path> fileStream, PrintWriter printWriter);
}
