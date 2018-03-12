package ru.sin666.sbt.dir_scan;

import java.nio.file.Path;
import java.util.Optional;

/**
 * Контракт на создание строки с информацией о файле, аргументе
 * если по указанному пути файла нет, или он недоступен
 * возвращается пустой контейнер
 *
 */
public interface FileProcessor {
    /**
     *
     * @param file путь к файлу
     * @return строка с информацей о файле по переданному пути
     */
    Optional<String> process(Path file);
}
