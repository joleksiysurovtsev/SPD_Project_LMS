package com.lms.spd.repository.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.spd.models.interfaces.Literature;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ParserLiteraturesJSON {

    private static final File file = new File("src/main/resources/json/Literatures.json");

    public static void parseLecturesInJSON(List<Literature> lit) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //если файла нет то создаём его
        if (!file.exists()) {
            try {
                Files.createFile(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file.getPath());
        try (BufferedWriter myWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8))) {
            for (Literature literature : lit) {
                String JSONString = mapper.writeValueAsString(literature);
                myWriter.write(JSONString);
                myWriter.write("\n");
                myWriter.flush();
            }
        }
    }


    public static List<Literature> parseLecturesFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        List<Literature> lect = new ArrayList<>();
        if (!file.exists()) {
            System.err.println("No data file");
            return new ArrayList<>();
        }

        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            String line = reader.readLine();
            while (line != null) {
                lect.add(mapper.readValue(line, Literature.class));
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lect;
    }
}
