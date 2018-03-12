package ru.sin666.sbt.dir_scan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toSet;

public class TestUtils {


    public static Set<Path> createFolders(Path path, String nameTemplate, int count) {
        return createFiles(path, nameTemplate, count, true);
    }

    public static Set<Path> createRegularFiles(Path path, String nameTemplate, int count) {

        return createFiles(path, nameTemplate, count, false);
    }

    public static Set<Path> createFiles(final Path path, final String nameTemplate, final int count, final boolean isFolder) {
        return IntStream.rangeClosed(1, count)
                .boxed()
                .map(i -> String.format(nameTemplate, i))
                .map(s -> createFile(path, s, isFolder))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toSet());
    }

    public static Optional<Path> createFile(Path targetFolder, String fileName, boolean isFolder) {

        String folderName = targetFolder.toString() + "/" + fileName;
        Path path = Paths.get(folderName);
        try {
            return Optional.of(isFolder ?  Files.createDirectory(path) : Files.createFile(path));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
