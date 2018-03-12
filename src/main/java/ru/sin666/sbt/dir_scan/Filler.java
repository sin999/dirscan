package ru.sin666.sbt.dir_scan;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Контракт на функциональность по заполнению
 * плейс-холдеров  шаблона значениями метаинформации переданного файла и его аттрибутов
 */
public interface Filler {

    /**
     * @param pattern   // Шаблон строки, содержащий плейс-холдеры
     * @param file      // источник мета-инфорации
     * @param basicFileAttributes //  аттрибуты файла
     * @return          //  результат замены
     */
    String fillPattern(String pattern, Path file,  BasicFileAttributes basicFileAttributes);
}
