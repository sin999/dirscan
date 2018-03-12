package ru.sin666.sbt.dir_scan.impls;

import ru.sin666.sbt.dir_scan.ScannerContext;
import ru.sin666.sbt.dir_scan.utils.ArgumentType;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.emptySet;
import static ru.sin666.sbt.dir_scan.utils.ArgumentType.EXCLUDE_FOLDERS;
import static ru.sin666.sbt.dir_scan.utils.ArgumentType.INCLUDE_FOLDERS;
import static java.util.Collections.unmodifiableSet;
import static java.util.Collections.unmodifiableMap;

public class ScannerContextImpl implements ScannerContext {

    private final Set<Path> foldersToInclude;
    private final Set<Path> foldersToExclude;

    public ScannerContextImpl(String[] args) {
        Map<ArgumentType, Set<String>> argumentsByTypeMap = parseArguments(args);
        foldersToInclude = convertToPaths(argumentsByTypeMap.get(INCLUDE_FOLDERS));
        foldersToExclude = convertToPaths(argumentsByTypeMap.get(EXCLUDE_FOLDERS));
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

    private static Map<ArgumentType, Set<String>> parseArguments(String[] args) {
        Map<ArgumentType, Set<String>> perKeyArgumentListMap = new HashMap<>();

        ArgumentType currentAction = INCLUDE_FOLDERS;
        for (String arg : args) {
            if (ArgumentType.isTypeSign(arg)) {
                currentAction = ArgumentType.getBySign(arg).get();
            } else {
                if (!perKeyArgumentListMap.containsKey(currentAction))
                    perKeyArgumentListMap.put(currentAction, new HashSet<>());
                perKeyArgumentListMap.get(currentAction).add(arg);
            }
        }
        return unmodifiableMap(perKeyArgumentListMap);
    }
}
