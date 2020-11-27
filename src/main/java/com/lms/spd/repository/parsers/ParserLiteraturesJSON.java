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

    public static File getFile() {
        return file;
    }


    public static void seturl(String url) {
        ParserLiteraturesJSON.file = new File(url);
    }


    private static File file = new File("src/main/resources/json/Literatures.json");
    private static final ObjectMapper mapper = new ObjectMapper().setTimeZone(TimeZone.getDefault());

    public static void parseLiteraturesInJSON(List<Literature> lit) {
        //если файла нет то создаём его
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
                String JSONString = mapper.writeValueAsString(literature);
                bufferedWriter.write(JSONString);
                bufferedWriter.write("\n");
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            System.out.println("File write error");
        }


    }


    public static List<Literature> parseLiteraturesFromJSON() {
        List<Literature> lect = new ArrayList<>();
        if (!file.exists()) {
            System.err.println("No data file");
            return new ArrayList<>();
        }
        try (ReaderWrapper mywrapper = new ReaderWrapper(new BufferedReader(new InputStreamReader(new FileInputStream(file))))) {
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
