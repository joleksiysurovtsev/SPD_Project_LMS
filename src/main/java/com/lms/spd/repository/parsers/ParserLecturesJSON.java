package com.lms.spd.repository.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.spd.models.interfaces.Lecture;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class ParserLecturesJSON {

    private static String DIR_NAME = "src/main/resources/json/";
    private static String FILE_NAME = "Lectures.json";
    private static ObjectMapper mapper = new ObjectMapper().setTimeZone(TimeZone.getDefault());

    public static String getDirName() {
        return DIR_NAME;
    }

    /**
     * set the path to the file directory with which we will work
     */
    public static void seturl(String url) {
        ParserLecturesJSON.DIR_NAME = url;
    }

    /**
     * method for writing lectures to JSON file
     */
    public static void parseLecturesInJSON(List<Lecture> lectures) {
        //check if the file exists if not then create it
        checkDirectoryAndFileExist();

        //creating a stream from a file
        try (BufferedWriter myWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(DIR_NAME + FILE_NAME), StandardCharsets.UTF_8))) {
            StringBuilder stringBuilderEntity = new StringBuilder("");
            for (Lecture lect : lectures) {
                stringBuilderEntity.append(mapper.writeValueAsString(lect) + "\n");
            }
            myWriter.write(stringBuilderEntity.toString());
            myWriter.flush();
        } catch (IOException e) {
            System.err.println("Failed to write to file");
        }
    }

    public static List<Lecture> parseLecturesFromJSON() {
        //check if the file exists if not then create it
        if (!checkDirectoryAndFileExist()) {
            return new ArrayList<>();
        }
        List<Lecture> lectures = new ArrayList<>();
        try (var stream = Files.lines(Path.of(DIR_NAME + FILE_NAME))) {
            stream.forEach(lines -> readLectures(lectures, lines));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lectures;
    }

    private static void readLectures(List<Lecture> lect, String s) {
        try {
            lect.add(mapper.readValue(s, Lecture.class));
        } catch (IOException e) {
            System.out.println("It is impossible to count lectures");
        }
    }

    private static boolean checkDirectoryAndFileExist() {
        boolean flag = true;
        try {
            if (Files.notExists(Path.of(DIR_NAME))) {
                Files.createDirectories(Path.of(DIR_NAME));
                flag = false;
            }
            if (Files.notExists(Path.of(DIR_NAME + FILE_NAME))) {
                Files.createFile(Path.of(DIR_NAME + FILE_NAME));
                flag = false;
            }
        } catch (IOException e) {
            System.out.println("unable to create file");
        }
        return flag;
    }
}
