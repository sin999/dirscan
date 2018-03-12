package ru.sin666.sbt.dir_scan.impls.fillers;

import ru.sin666.sbt.dir_scan.Filler;
import ru.sin666.sbt.dir_scan.utils.TLCSimpleDateFormat;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;

/**
 * Простейший заполнитель строки,
 * Достаточный для требований задачи
 */
public class BasicFiller implements Filler {


    private final SimpleDateFormat df;

    public BasicFiller(String dateFormatPattern){
        df= new TLCSimpleDateFormat(dateFormatPattern);
    }

    @Override
    public String fillPattern(String pattern, Path file, BasicFileAttributes basicFileAttributes) {
            String result = String.format(pattern, file,
                    df.format(basicFileAttributes.lastModifiedTime().toMillis()),
                    basicFileAttributes.size());
        return result;
    }
}
