package ru.sin666.sbt.dir_scan.impls;

import org.junit.Before;
import org.junit.Test;
import ru.sin666.sbt.dir_scan.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class FolderStreamerSimpleImplTest extends AbstractWithPreparedFileSystemTest{

    private static final int FOLDERS_COUNT = 4;
    private static final int FILES_COUNT = 3;
    private int expected_files_count = FILES_COUNT * FOLDERS_COUNT + FOLDERS_COUNT + 1;// Все файлы + все директории + корневая директория
    private static  final String[] foldersToInclude = new String[]{
            "test folder_1",
            "test folder_2",
            "test folder_3",
            "test folder_4",
            "test folder_5"
    };

    private static final String[] foldersToExclude = new String[]{
            "test folder_3",
            "test folder_4"
    };


    private FolderScanner folderScanner;
    private ScannerContext scannerContext;
    private Set<String> foldersToIncludeSet;
    private Set<String> foldersToExcludeSet;

    private SpyFileStreamProcessor spyFileStreamProcessor;
    private PrintWriter printWriter;
    private ByteArrayOutputStream byteArrayOutputStream;

    @Before
    public void beforeFolderStreamerSimpleImplTest(){
        foldersToIncludeSet = Arrays.stream(foldersToInclude)
                .map(s ->testDir+"/"+s).collect(Collectors.toSet());
        foldersToExcludeSet = Arrays.stream(foldersToExclude)
                .map(s ->testDir+"/"+s).collect(Collectors.toSet());

        scannerContext = new ScannerContextMockImpl(foldersToIncludeSet, foldersToExcludeSet);

        spyFileStreamProcessor = new SpyFileStreamProcessor();

        folderScanner = new FolderScannerSimpleImpl(spyFileStreamProcessor);
        byteArrayOutputStream = new ByteArrayOutputStream();
        printWriter = new PrintWriter(byteArrayOutputStream);

    }

    @Test
    public void structureTest() throws IOException {
        long fileCount =
                Files.walk(testDir, Integer.MAX_VALUE)
                .count();
        assertEquals("",expected_files_count,fileCount);
    }


    @Test
    public void processFoldersTest(){
        folderScanner.processFolders(scannerContext, printWriter);
        assertEquals("",6,spyFileStreamProcessor.getResultCollection().size());
    }


    @Override
    protected int getFoldersCount() {
        return FOLDERS_COUNT;
    }

    @Override
    protected int getFilesCount() {
        return FILES_COUNT;
    }

    class SpyFileStreamProcessor implements FileStreamProcessor{
        private Collection<Path> resultCollection = new ArrayList<>();
        private PrintWriter printWriter;

        @Override
        public void process(Stream<Path> fileStream, PrintWriter printWriter) {
            this.resultCollection.addAll(fileStream.collect(Collectors.toSet()));
            this.printWriter = printWriter;
        }


        public Collection<Path> getResultCollection() {
            return resultCollection;
        }

    }


//    @Test
//    public void structureTestPrint() throws IOException {
//                Files.walk(testDir, Integer.MAX_VALUE)
//                .map(Path::getFileName)
//                .forEachOrdered(System.out::println);
//    }
}
