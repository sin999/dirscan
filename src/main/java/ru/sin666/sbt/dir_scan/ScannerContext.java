package ru.sin666.sbt.dir_scan;

import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Set;

public interface ScannerContext {

    /**
     *  Список директорий, которые необходимо просканировать
     * @return
     */
    Set<Path> foldersToInclude();
    /**
     *  Список директорий, которые исключить из процесса сканирования
     * @return
     */
    Set<Path> foldersToExclude();

}
