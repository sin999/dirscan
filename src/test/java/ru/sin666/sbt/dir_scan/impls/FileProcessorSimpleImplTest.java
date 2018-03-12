package ru.sin666.sbt.dir_scan.impls;

import org.junit.Before;
import org.junit.Test;
import ru.sin666.sbt.dir_scan.AbstractWithPreparedFileSystemTest;
import ru.sin666.sbt.dir_scan.FileProcessor;
import ru.sin666.sbt.dir_scan.Filler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Optional;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.*;

public class FileProcessorSimpleImplTest extends AbstractWithPreparedFileSystemTest {

    private final String TEST_PATTERN ="TEST PATTERN";
    private final String TEST_FILE_NAME = "test folder_1/test folder_1_test file_1.txt";

    private BasicFileAttributes basicFileAttributes;
    private Path goodTestFile;
    private Path badTestFile;

    private FileProcessor fileProcessor;
    private SpyFiller spyFiller;


    @Before
    public void beforeFileProcessorSimpleImplTest() throws IOException {
        spyFiller = new SpyFiller();
        fileProcessor = new FileProcessorSimpleImpl(spyFiller,TEST_PATTERN);
        goodTestFile = Paths.get(testDir+"/"+TEST_FILE_NAME);
        badTestFile = Paths.get(testDir+"/BAD_FILE"+TEST_FILE_NAME);
        basicFileAttributes = Files.readAttributes(goodTestFile, BasicFileAttributes.class);
    }

    @Test
    public void processPositiveTest(){
        Optional<String> result = fileProcessor.process(goodTestFile);
        assertTrue("",result.isPresent());
        assertEquals("", TEST_PATTERN, result.get());
        assertEquals("", TEST_PATTERN, spyFiller.getPattern());
        assertEquals("", goodTestFile, spyFiller.getFile());
//        assertEquals("", basicFileAttributes, spyFiller.getBasicFileAttributes());
        assertNotNull("",  spyFiller.getBasicFileAttributes());

    }

    @Test
    public void processNegativeTest(){
        Optional<String> result = fileProcessor.process(badTestFile);
        assertFalse("",result.isPresent());
        assertNull("", spyFiller.getPattern());
        assertNull("", spyFiller.getFile());
        assertNull("", spyFiller.getBasicFileAttributes());
    }


    class SpyFiller implements Filler {
        private String pattern;
        private Path file;
        private BasicFileAttributes basicFileAttributes;


        @Override
        public String fillPattern(String pattern, Path file, BasicFileAttributes basicFileAttributes) {
            this.basicFileAttributes =basicFileAttributes;
            this.file = file;
            this.pattern = pattern;
            return pattern;
        }

        public String getPattern() {
            return pattern;
        }

        public Path getFile() {
            return file;
        }

        public BasicFileAttributes getBasicFileAttributes() {
            return basicFileAttributes;
        }
    }


    @Test
    public void structureTest() throws IOException {
        long fileCount =
                Files.walk(testDir, Integer.MAX_VALUE)
                        .count();
        assertEquals("",111,fileCount);
    }
}
