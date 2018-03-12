package ru.sin666.sbt.dir_scan.impls.fillers;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.sin666.sbt.dir_scan.Filler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BasicFillerTest {
    private static final String DEFAULT_DATE_FORMAT = "yyyy.MM.dd";
    private final String PATTERN = "[\nfile = %s \ndate =  %s \nsize = %s]";
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();;
    private String TEST_FILE_NAME= "test_file.txt";
    private Path file;
    private Filler filler;
    private BasicFileAttributes basicFileAttributes;


    @Before
    public void beforeBasicFillerTest() throws IOException {
        file = temporaryFolder.newFile(TEST_FILE_NAME).toPath();
        basicFileAttributes = Files.readAttributes(file,BasicFileAttributes.class);
        filler = new BasicFiller(DEFAULT_DATE_FORMAT);
    }

    @Test
    public void testPatternFilling(){
        String result = filler.fillPattern(PATTERN,file,basicFileAttributes);
        assertFalse(result.isEmpty());
        assertEquals("", 4, result.split("\n").length);
    }

}
