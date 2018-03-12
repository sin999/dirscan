package ru.sin666.sbt.dir_scan.impls;

import org.junit.Before;
import org.junit.Test;
import ru.sin666.sbt.dir_scan.AbstractWithPreparedFileSystemTest;
import ru.sin666.sbt.dir_scan.FileProcessor;
import ru.sin666.sbt.dir_scan.FileStreamProcessor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;


public class FileStreamProcessorParallelImplTest extends AbstractWithPreparedFileSystemTest {

    private final String TEST_FOLDER_NAME = "test folder_1";
    private final String TEST_FILE_NAME = "test folder_1/test folder_1_test file_1.txt";
    private final String TEST_STRING = "TEST_STRING\n";

    private FileStreamProcessor fileStreamProcessor;
    private SpyFileProcessor spyFileProcessor;
    private ByteArrayOutputStream byteArrayOutputStream;
    private Path testFile;
    private Path testFolder;
    private PrintWriter printWriter;

    @Before
    public void beforeFileStreamProcessorParallelImpl() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        spyFileProcessor = new SpyFileProcessor();
        fileStreamProcessor = new FileStreamProcessorParallelImpl(spyFileProcessor);
        testFile = Paths.get(testDir + "/" + TEST_FILE_NAME);
        testFolder = Paths.get(testDir + "/" + TEST_FOLDER_NAME);
        printWriter = new PrintWriter(byteArrayOutputStream);
    }

    @Test
    public void processPositiveTest() {
        fileStreamProcessor.process(Stream.of(testFile), printWriter);
        printWriter.close();
        assertEquals("", TEST_STRING, byteArrayOutputStream.toString());
        assertEquals("", testFile, spyFileProcessor.getPath());
    }

    @Test
    public void processPositiveTenFilesStreamTest() throws IOException {
        Stream<Path> stream = Files.find(testFolder, 10,
                (path, ba) -> Files.isRegularFile(path),
                FileVisitOption.FOLLOW_LINKS);
        fileStreamProcessor.process(stream, printWriter);
        printWriter.close();
        String result = byteArrayOutputStream.toString();
        assertEquals("", 10, result.split("\n").length);
    }


    class SpyFileProcessor implements FileProcessor {

        private Path path;

        @Override
        public Optional<String> process(Path file) {
            this.path = file;
            return Optional.of(TEST_STRING);
        }

        public Path getPath() {
            return path;
        }
    }
}
