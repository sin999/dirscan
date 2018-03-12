package ru.sin666.sbt.dir_scan;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.sin666.sbt.dir_scan.impls.FileProcessorSimpleImpl;
import ru.sin666.sbt.dir_scan.impls.fillers.BasicFiller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FilesStreamTest {
    private static final String DEFAULT_DATE_FORMAT = "yyyy.MM.dd";
    private final String PATTERN = "[\nfile = %s \ndate =  %s \nsize = %s]";
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private String TEST_FILE_NAME= "test_flie.txt";
    private Path file;
    private Filler filler;
    private BasicFileAttributes basicFileAttributes;
    private Stream<Path> fileStream;
    private FileProcessorSimpleImpl fileProcessor;
    private PrintStream printStream;

    @Before
    public void beforeBasicFillerTest() throws IOException {
        file = temporaryFolder.newFile(TEST_FILE_NAME).toPath();
        basicFileAttributes = Files.readAttributes(file,BasicFileAttributes.class);
        filler = new BasicFiller(DEFAULT_DATE_FORMAT);
        fileStream = IntStream.range(1,500).boxed().map(x -> file);
        fileProcessor = new FileProcessorSimpleImpl(new BasicFiller(DEFAULT_DATE_FORMAT), PATTERN);
        printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        });

    }
    // 5000000
    // Total time 36697 msec.
    // Total time 58947 msec.. без параллельности
//    @Test
//    public void testProcessObject(){
//        long startTime = System.currentTimeMillis();
//        DirScan.processFileStream(processFolders.parallel(), fileProcessor)
////        DirScan.processFileStream(processFolders, fileProcessor)
//                .forEachOrdered(printStream::print);
//        System.out.println("Total time "+(System.currentTimeMillis()-startTime)+" msec.");
//    }
}
