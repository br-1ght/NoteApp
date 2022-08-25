package com.br1ght.Services;

import com.br1ght.ImportExport.NoteExporter;
import com.br1ght.Models.NoteBucket;

import java.util.Scanner;

public class ExportService extends BaseService {
    private final Scanner inputScanner;
    private final NoteExporter noteExporter;

    public ExportService(NoteBucket noteBucket, Scanner inputScanner, NoteExporter noteExporter) {
        super(noteBucket);
        this.inputScanner = inputScanner;
        this.noteExporter = noteExporter;
    }

    @Override
    public void run() {
        String exportMenu = """
                0. Exit
                1. Export to JSON
                2. Export to CSV""";
        do {
            System.out.println(exportMenu);
            String exportChoice = inputScanner.nextLine();

            if (exportChoice.equals("0")) {
                break;
            }

            System.out.println("Enter a custom filename (Leave empty if you want the date as the name): ");
            String fileName = inputScanner.nextLine();
            switch (exportChoice) {
                case "1" -> System.out.println(noteExporter.exportToJSON(fileName));
                case "2" -> System.out.println(noteExporter.exportToCSV(fileName));
            }

        } while (true);
    }
}
