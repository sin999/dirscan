package ru.sin666.sbt.dir_scan;


import java.io.PrintWriter;

/**
 *
 */
public interface FolderScanner {
    void processFolders(final ScannerContext context, final PrintWriter printWriter);
}
