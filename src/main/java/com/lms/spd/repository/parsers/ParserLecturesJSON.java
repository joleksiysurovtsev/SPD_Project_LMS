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

public final class ParserLecturesJSON {

    private static String dirName = "src/main/resources/json/";
    private static String fileName = "Lectures.json";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().setTimeZone(TimeZone.getDefault());

    public static String getDirName() {
        return dirName;
    }

    /**
     * set the path to the file directory with which we will work
     */
    public static void seturl(String url) {
        ParserLecturesJSON.dirName = url;
    }

    /**
     * method for writing lectures to JSON file
     */
    public static void parseLecturesInJSON(List<Lecture> lectures) {
        //check if the file exists if not then create it
        checkDirectoryAndFileExist();

        //creating a stream from a file
        try (BufferedWriter myWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dirName + fileName), StandardCharsets.UTF_8))) {
            StringBuilder stringBuilderEntity = new StringBuilder("");
            for (Lecture lecture : lectures) {
                stringBuilderEntity.append(OBJECT_MAPPER.writeValueAsString(lecture)).append("\n");
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
        try (var stream = Files.lines(Path.of(dirName + fileName))) {
            stream.forEach(lines -> readLectures(lectures, lines));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lectures;
    }

    private static void readLectures(List<Lecture> lect, String s) {
        try {
            lect.add(OBJECT_MAPPER.readValue(s, Lecture.class));
        } catch (IOException e) {
            System.out.println("It is impossible to count lectures");
        }
    }

    private static boolean checkDirectoryAndFileExist() {
        boolean ifDirorFileNotExist = true;
        try {
            if (Files.notExists(Path.of(dirName))) {
                Files.createDirectories(Path.of(dirName));
                ifDirorFileNotExist = false;
            }
            if (Files.notExists(Path.of(dirName + fileName))) {
                Files.createFile(Path.of(dirName + fileName));
                ifDirorFileNotExist = false;
            }
        } catch (IOException e) {
            System.out.println("unable to create file");
        }
        return ifDirorFileNotExist;
    }

    private ParserLecturesJSON() {
    }
}
