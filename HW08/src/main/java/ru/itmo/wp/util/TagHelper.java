package ru.itmo.wp.util;

import java.util.HashSet;

public class TagHelper {
    public static HashSet<String> splitByWhitespaces(String mergedTags) {
        String[] tags = mergedTags.split("\\s+");
        HashSet<String> res = new HashSet<>();
        for (String tag : tags) {
            if (!tag.isEmpty())
                res.add(tag);
        }
        return res;
    }
}
