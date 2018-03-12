package ru.sin666.sbt.dir_scan.utils;


import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public enum ArgumentType {
    INCLUDE_FOLDERS("+"),
    EXCLUDE_FOLDERS("-"),
    OUTPUT_FILE("-f");

    private static final Map<String, ArgumentType> map = Arrays.stream(values())
            .collect(
                    toMap(ArgumentType::getSign, Function.identity()
                    ));

    private String sign;

    ArgumentType(String sign) {
        this.sign = sign;
    }

    public static Optional<ArgumentType> getBySign(String sign) {
        return isTypeSign(sign) ? Optional.of(map.get(sign)) : Optional.empty();
    }

    public static boolean isTypeSign(String sign) {
        return map.containsKey(sign);
    }

    public String getSign() {
        return sign;
    }
}
