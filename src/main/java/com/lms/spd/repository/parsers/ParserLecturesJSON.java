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

    public static File getFile() {
        return file;
    }


    public static void seturl(String url) {
        ParserLecturesJSON.file = new File(url);
    }

    private static File file = new File("src/main/resources/json/Lectures.json");
    private static ObjectMapper mapper = new ObjectMapper().setTimeZone(TimeZone.getDefault());

    public static void parseLecturesInJSON(List<Lecture> lectures)  {
        //если файла нет то создаём его
        if (!file.exists()) {
            try {
                Files.createFile(file.toPath());
            } catch (IOException e) {
                System.err.println("unable to create file");
            }
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file.getPath());
        } catch (FileNotFoundException e) {
            System.err.println("unable to create stream");
        }
        try {
            try (BufferedWriter myWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8))) {
                for (Lecture lect : lectures) {
                    String JSONString = mapper.writeValueAsString(lect);
                    myWriter.write(JSONString);
                    myWriter.write("\n");
                    myWriter.flush();
                }
            }
        } catch (IOException e) {
            System.err.println("failed to write to file");
        }
    }


    public static List<Lecture> parseLecturesFromJSON() {
        //если файла нет то создаём его
        if (!file.exists()) {
            System.err.println("No data file, creating new");
            try {
                Files.createFile(file.toPath());
            } catch (IOException e) {
                System.err.println("Unable to create file");
            }
        }
        List<Lecture> lect = new ArrayList<>();

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
