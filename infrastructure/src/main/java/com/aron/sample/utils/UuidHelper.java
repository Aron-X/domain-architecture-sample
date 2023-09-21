package com.aron.sample.utils;

import java.util.UUID;

public final class UuidHelper {

    private UuidHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static String uuid32() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
