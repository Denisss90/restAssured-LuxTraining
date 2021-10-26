package com.sqa.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileUtils {
    public static <T> T getModelFromJson(String path, Class<T> objectClass) {
        try {
            final String jsonFile = FileUtils.loadFile(path);
            ObjectMapper objectMapper = new ObjectMapper();
            return (T) objectMapper.readValue(jsonFile, objectClass);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static String loadFile(String path) {
        return String.join(
                "\n",
                Optional.ofNullable(
                        loadListFromFile(path)
                ).orElse(new ArrayList<>())
        );
    }

    public static List<String> loadListFromFile(String path) {
        try {
            return Files.readAllLines(Paths.get(path),
                    UTF_8
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
