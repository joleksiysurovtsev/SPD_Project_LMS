package com.lms.spd.repository.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.spd.models.interfaces.Literature;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class ParserLiteraturesJSON {

    private static String dirName = "src/main/resources/json/";
    private static String filename = "Literatures.json";

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().setTimeZone(TimeZone.getDefault());

    /**
     * set the path to the file directory with which we will work
     */
    public static void seturl(String url) {
        ParserLiteraturesJSON.dirName = url;
    }

    public static void parseLiteraturesInJSON(List<Literature> literature) {
        //if there is no file then create it
        checkDirectoryAndFileExist();

        try (BufferedWriter myWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dirName + filename), StandardCharsets.UTF_8))) {
            StringBuilder stringBuilderEntity = new StringBuilder("");
            for (Literature lit : literature) {
                stringBuilderEntity.append(OBJECT_MAPPER.writeValueAsString(lit)).append("\n");
            }
            myWriter.write(stringBuilderEntity.toString());
            myWriter.flush();
        } catch (IOException e) {
            System.out.println("Failed to write to file");
        }
    }

    public static List<Literature> parseLiteraturesFromJSON() {
        //check if the file exists if not then create it
        if (!checkDirectoryAndFileExist()) {
            return new ArrayList<>();
        }
        List<Literature> readLectures = new ArrayList<>();
        try (var stream = Files.lines(Path.of(dirName + filename))) {
            stream.forEach(lines -> readLectures(readLectures, lines));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readLectures;
    }

    private static void readLectures(List<Literature> literature, String s) {
        try {
            literature.add(OBJECT_MAPPER.readValue(s, Literature.class));
        } catch (IOException e) {
            System.out.println("It is impossible to count lectures");
        }
    }

    private static boolean checkDirectoryAndFileExist() {
        boolean flag = true;
        try {
            if (Files.notExists(Path.of(dirName))) {
                Files.createDirectories(Path.of(dirName));
                flag = false;
            }
            if (Files.notExists(Path.of(dirName + filename))) {
                Files.createFile(Path.of(dirName + filename));
                flag = false;
            }
        } catch (IOException e) {
            System.out.println("unable to create file");
        }
        return flag;
    }

    private ParserLiteraturesJSON() {
    }
}
