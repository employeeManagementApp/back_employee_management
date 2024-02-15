package com.javaadr.renderapi.Controller.Validator.utils;

public class StringValidator {
    public static boolean isNotNullAndNotBlank(String s) {
        return s != null && !s.isBlank();
    }
}
