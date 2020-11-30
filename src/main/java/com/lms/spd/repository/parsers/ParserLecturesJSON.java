package com.lms.spd.repository.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.spd.models.interfaces.Lecture;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class ParserLecturesJSON {
    private static File file = new File("src/main/resources/json/Lectures.json");
    private static ObjectMapper mapper = new ObjectMapper().setTimeZone(TimeZone.getDefault());

    public static File getFile() {
        return file;
    }

    /**
     * set the path to the file with which we will work
     */
    public static void seturl(String url) {
        ParserLecturesJSON.file = new File(url);
    }

    /**
     * method for writing lectures to JSON file
     */
    public static void parseLecturesInJSON(List<Lecture> lectures) {
        //check if the file exists if not then create it
        if (!file.exists()) {
            try {
                Files.createFile(file.toPath());
            } catch (IOException e) {
                System.err.println("unable to create file");
            }
        }
        //creating a stream from a file
        try (BufferedWriter myWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getPath()), StandardCharsets.UTF_8))) {
            for (Lecture lect : lectures) {
                myWriter.write(mapper.writeValueAsString(lect) + "\n");
                myWriter.flush();
            }
        } catch (IOException e) {
            System.err.println("failed to write to file");
        }
    }


    public static List<Lecture> parseLecturesFromJSON() {
        //check if the file exists if not then create it
        List<Lecture> lect = new ArrayList<>();
        if (!file.exists()) {
            try {
                Files.createFile(file.toPath());
            } catch (IOException e) {
                System.err.println("Unable to create file");
            }
        }
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            String line = reader.readLine();
            while (line != null) {
                lect.add(mapper.readValue(line, Lecture.class));
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lect;
    }
}
