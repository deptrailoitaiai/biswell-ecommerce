package org.example.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class SlugUtil {

    private static final Pattern NON_ASCII = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

    public static String toSlug(String input) {
        if (input == null || input.isBlank()) return "";
        // đ/Đ không decompose qua NFD, xử lý trước
        String s = input.replace("đ", "d").replace("Đ", "D");
        String normalized = Normalizer.normalize(s, Normalizer.Form.NFD);
        String ascii = NON_ASCII.matcher(normalized).replaceAll("");
        return ascii.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .trim()
                .replaceAll("[\\s-]+", "-");
    }
}
