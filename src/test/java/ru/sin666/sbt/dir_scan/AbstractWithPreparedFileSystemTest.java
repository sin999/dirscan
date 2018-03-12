package ru.sin666.sbt.dir_scan;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.nio.file.Path;
import java.util.Set;

public abstract class AbstractWithPreparedFileSystemTest {


    private static final int FILE_COUNT = 10;
    private static final int FOLDER_COUNT = 10;
    private static final String TEST_FILE_NAME_TEMPLATE = "test file_%d.txt";
    private static final String TEST_FOLDER_NAME_TEMPLATE = "test folder_%d";

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();


    protected Path testDir;

    @Before
    public void beforeAbstractWithPreparedFileSystemTest() {
        testDir = temporaryFolder.getRoot().toPath();
        prepareFiles(testDir);

    }

    protected int getFilesCount() {
        return FILE_COUNT;
    }

    protected String getTestFileNameTemplate() {
        return TEST_FILE_NAME_TEMPLATE;
    }

    protected int getFoldersCount() {
        return FOLDER_COUNT;
    }

    protected String getTestFolderNameTemplate() {
        return TEST_FOLDER_NAME_TEMPLATE;
    }

    private void prepareFiles(Path targetFolder) {
        Set<Path> folders = TestUtils.createFolders(targetFolder,
                getTestFolderNameTemplate(),
                getFoldersCount());

        for (Path folder : folders) {
            String fileName = folder.getFileName() + "_" + getTestFileNameTemplate();
            TestUtils.createRegularFiles(folder,
                    fileName,
                    getFilesCount());
        }
    }
}
