package ru.sin666.sbt.dir_scan;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class FolderScannerTest {

    private static final int FILE_COUNT = 10;
    private static final int FOLDER_COUNT = 10;
    private static final String TEST_FILE_NAME_TEMPLATE = "test file_%d.txt";
    private static final String TEST_FOLDER_NAME_TEMPLATE = "test folder_%d";


    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();


    private Path testDir;


    @Before
    public void initFolderScannerTest() {
        testDir = temporaryFolder.getRoot().toPath();
        TestUtils.createRegularFiles(testDir, TEST_FILE_NAME_TEMPLATE, FILE_COUNT);
        TestUtils.createFolders(testDir, TEST_FOLDER_NAME_TEMPLATE, FOLDER_COUNT);


    }

//    @Test
//    public void testFileCreation() throws IOException {
//        int count = 0;
//        for (Path path : Files.newDirectoryStream(testDir, (path) -> Files.isRegularFile(path))) count++;
//        assertEquals("", FILE_COUNT, count);
//    }

//    @Test
//    public void testFileList() throws IOException {
//        for (Path path : Files.newDirectoryStream(testDir)) System.out.println(path);
//    }


//    @Test
//    public void prepareFolder(){
//        Path folder = Paths.get("/Users/sin/test");
//        int count = 100000;
//        TestUtils.createRegularFiles(folder, folder.getFileName() + "_" + "test_file_%d.txt", count);
//    }
}
