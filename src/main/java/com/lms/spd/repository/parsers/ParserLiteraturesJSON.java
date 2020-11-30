package com.lms.spd.repository.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.spd.models.interfaces.Literature;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class ParserLiteraturesJSON {
    private static File file = new File("src/main/resources/json/Literatures.json");
    private static final ObjectMapper mapper = new ObjectMapper().setTimeZone(TimeZone.getDefault());

    public static File getFile() {
        return file;
    }

    public static void seturl(String url) {
        ParserLiteraturesJSON.file = new File(url);
    }

    public static void parseLiteraturesInJSON(List<Literature> lit) {
        //if there is no file then create it
        if (!file.exists()) {
            try {
                Files.createFile(file.toPath());
            } catch (IOException e) {
                System.err.println("Unable to create file");
            }
        }
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file.getPath()))) {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8));
            for (Literature literature : lit) {
                bufferedWriter.write(mapper.writeValueAsString(literature)+"\n");
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            System.out.println("File write error");
        }
    }


    public static List<Literature> parseLiteraturesFromJSON() {
        List<Literature> lect = new ArrayList<>();
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ReaderWrapper mywrapper = new ReaderWrapper(new InputStreamReader(new FileInputStream(file)))) {
            String line = mywrapper.readLine();
            while (line != null) {
                lect.add(mapper.readValue(line, Literature.class));
                line = mywrapper.readLine();
            }
        } catch (IOException e) {
            System.err.println("Could not read the file");
        }
        return lect;
    }
}
