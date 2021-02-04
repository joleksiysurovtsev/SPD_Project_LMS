package com.lms.spd.repository.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.utils.Util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public final class ParserLecturesJSON {

    private static String dirName = "src/main/resources/json/";
    private static final String FILE_NAME = "Lectures.json";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().setTimeZone(TimeZone.getDefault());

    public static String getDirName() {
        return dirName;
    }

    /**
     * set the path to the file directory with which we will work
     */
    public static void setURL(String url) {
        ParserLecturesJSON.dirName = url;
    }

    /**
     * method for writing lectures to JSON file
     */
    public static void parseLecturesInJSON(List<Lecture> lectures) {
        //check if the file exists if not then create it
        checkDirectoryAndFileExist();

        //creating a stream from a file
        try (BufferedWriter myWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(dirName + FILE_NAME), StandardCharsets.UTF_8))) {
            StringBuilder stringBuilderEntity = new StringBuilder("");
            for (Lecture lecture : lectures) {
                stringBuilderEntity.append(OBJECT_MAPPER.writeValueAsString(lecture)).append("\n");
            }
            myWriter.write(stringBuilderEntity.toString());
            myWriter.flush();
        } catch (IOException e) {
            Util.GLOBAL_LOGGER.info("Failed to write to file");
        }
    }

    public static List<Lecture> parseLecturesFromJSON() {
        //check if the file exists if not then create it
        if (!checkDirectoryAndFileExist()) {
            return new ArrayList<>();
        }
        List<Lecture> lectures = new ArrayList<>();
        try (var stream = Files.lines(Path.of(dirName + FILE_NAME))) {
            stream.forEach(lines -> readLectures(lectures, lines));
        } catch (IOException e) {
            Util.GLOBAL_LOGGER.info(Arrays.toString(e.getStackTrace()));
        }
        return lectures;
    }

    private static void readLectures(List<Lecture> lect, String s) {
        try {
            lect.add(OBJECT_MAPPER.readValue(s, Lecture.class));
        } catch (IOException e) {
            Util.GLOBAL_LOGGER.info("It is impossible to count lectures");
        }
    }

    private static boolean checkDirectoryAndFileExist() {
        boolean ifDirOrFileNotExist = true;
        try {
            if (Files.notExists(Path.of(dirName))) {
                Files.createDirectories(Path.of(dirName));
                ifDirOrFileNotExist = false;
            } else {
                if (Files.notExists(Path.of(dirName + FILE_NAME))) {
                    Files.createFile(Path.of(dirName + FILE_NAME));
                    ifDirOrFileNotExist = false;
                }
            }
        } catch (IOException e) {
            Util.GLOBAL_LOGGER.info("unable to create file");
        }
        return ifDirOrFileNotExist;
    }

    private ParserLecturesJSON() {
    }
}
