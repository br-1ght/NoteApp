package com.br1ght.ImportExport;

import com.br1ght.Models.NoteBucket;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NoteExporter {
    private final NoteBucket noteBucket;

    public NoteExporter(NoteBucket noteBucket) {
        this.noteBucket = noteBucket;
    }
    ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    ObjectWriter writer = mapper.writer();

    private String getFileName(String specifiedFileName, FileTypes fileType) {
        String fileName = specifiedFileName + fileType.getValue();
        if (specifiedFileName.equals("")) {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
            fileName = LocalDateTime.now().format(dateFormat) + "-notes" + fileType.getValue();
        }
        return fileName;
    }

    public String exportToJSON(String specifiedFileName) {
        try {
            String fileName = getFileName(specifiedFileName, FileTypes.JSON);
            writer.writeValue(new File(fileName), noteBucket.getAllNotes());
            return "Export of notes " + fileName + " completed!";
        } catch (Exception e) {
            return "Unexpected error occurred!";
        }
    }

    public String exportToCSV(String specifiedFileName) {
        StringBuilder sb = new StringBuilder();
        String fileName = getFileName(specifiedFileName, FileTypes.CSV);
        List<String> noteEntries = noteBucket.getAllNotes()
                                    .stream()
                                    .map(note -> note.getNoteID().toString() + ", "
                                            + note.getAuthor() + ", "
                                            + note.getContent() + "\n")
                                    .toList();

        for (String noteEntry: noteEntries) {
            sb.append(noteEntry);
        }

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(sb.toString());
            fileWriter.close();
            return "Export of notes " + fileName + " completed!";
        } catch (IOException e) {
            return "Unexpected error occurred!";
        }
    }
}
