package com.br1ght;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NoteExporter {
    private final NoteBucket noteBucket;

    NoteExporter(NoteBucket noteBucket) {
        this.noteBucket = noteBucket;
    }
    ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    ObjectWriter writer = mapper.writer();

    public String exportToJson(String specifiedFileName) {
        if (noteBucket.size() == 0) {
            return "There are currently no notes. Aborting export.";
        }
        try {
            String fileName = specifiedFileName + ".json";
            if (specifiedFileName.equals("")) {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
                fileName = LocalDateTime.now().format(dateFormat) + "-notes.json";
            }
            writer.writeValue(new File(fileName), noteBucket.getAllNotes());
            return "Export of notes " + fileName + " completed!";
        } catch (Exception e) {
            return "Unexpected error occurred!";
        }
    }
}
