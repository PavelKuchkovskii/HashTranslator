package org.kucher.hashtranslatorservice.service.utils;

import org.kucher.hashtranslatorservice.dao.entity.ResultHash;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HashConverterUtils {

    public static String convertList(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i));
            if(i != list.size()-1) {
                stringBuilder.append(";");
            }
        }
        return stringBuilder.toString();
    }

    public static List<ResultHash> convertString(String hashes) {
        return Stream.of(removeLastChar(hashes).split(";"))
                .map(ResultHash::new)
                .collect(Collectors.toList());
    }

    private static String removeLastChar(String str) {
        if (str == null || str.isBlank()) {
            return str;
        }
        return str.substring(0, str.length() - 1);
    }
}
