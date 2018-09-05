package com.codecool.shop.config;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataReader {

    private Gson gson = new Gson();
    private String path;

    public DataReader(String path) {
        this.path = path;
    }

    public <T> T getData(Class<T> classOfT) {
        try (Stream<String> reader = Files.lines(Paths.get(path))) {

            String input = reader.collect(Collectors.joining());
            return gson.fromJson(input, classOfT);

        } catch (IOException e) {
            System.out.println("Could not open file.");
            e.printStackTrace();
        }
        return null;
    }
}
