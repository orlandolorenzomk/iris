package com.iris.nexus.shared.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {

    public String normalizeName(String name) {
        return name.toLowerCase()
                .replace("-", " ")
                .replaceAll("[^a-z0-9 ]", "")
                .trim()
                .replaceAll(" +", " ");
    }
}
