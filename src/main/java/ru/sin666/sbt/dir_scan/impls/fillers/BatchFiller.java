package ru.sin666.sbt.dir_scan.impls.fillers;

import ru.sin666.sbt.dir_scan.Filler;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Контейнер для заполнителей
 * применяет каждый из переданных заполнителей переданных в конструктор к
 * шаблону и файлу
 */
public class BatchFiller implements Filler{

    private final Collection<Filler> fillers;

    public BatchFiller(Filler... fillers){
        this.fillers = Collections.unmodifiableList(Arrays.asList(fillers));
    }


    @Override
    public String fillPattern(String pattern, Path file, BasicFileAttributes basicFileAttributes) {
        String resultString = pattern;
        for (Filler filler : fillers){
            resultString = filler.fillPattern(resultString, file, basicFileAttributes);
        }
        return resultString;
    }
}
