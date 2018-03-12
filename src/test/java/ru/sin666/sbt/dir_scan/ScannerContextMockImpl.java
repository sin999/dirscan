package ru.sin666.sbt.dir_scan;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.emptySet;
import static java.util.Collections.unmodifiableSet;

public class ScannerContextMockImpl implements ScannerContext {
    private final Set<Path> foldersToInclude;
    private final Set<Path> foldersToExclude;

    public ScannerContextMockImpl(Set<String> foldersToInclude, Set<String> foldersToExclude) {
        this.foldersToInclude = convertToPaths(foldersToInclude);
        this.foldersToExclude = convertToPaths(foldersToExclude);
    }

    @Override
    public Set<Path> foldersToInclude() {
        return foldersToInclude;
    }

    @Override
    public Set<Path> foldersToExclude() {
        return foldersToExclude;
    }


    private Set<Path> convertToPaths(Set<String> stringPaths) {
        Set<Path> result = stringPaths != null ?
                stringPaths.stream()
                        .map(Paths::get)
                        .collect(Collectors.toSet())
                : emptySet();
        return unmodifiableSet(result);
    }
}
