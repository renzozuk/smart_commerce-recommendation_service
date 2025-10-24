package dev.renzozukeram.recommendation.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UuidFilter {

    public static final Pattern UUID_PATTERN =
            Pattern.compile("([0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12})");

    public static String extractUuid(String llmOutput) {
        if (llmOutput == null) {
            return null;
        }

        Matcher matcher = UUID_PATTERN.matcher(llmOutput);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }
}
