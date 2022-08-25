package com.br1ght.App;


import com.br1ght.ImportExport.NoteExporter;
import com.br1ght.Models.Note;
import com.br1ght.Models.NoteBucket;
import com.br1ght.Services.*;
import com.br1ght.Validators.Validator;

import java.util.Objects;
import java.util.Scanner;

public class NoteApp {
     public NoteApp() {
        NoteBucket storedNotes = new NoteBucket();
        NoteExporter noteExporter = new NoteExporter(storedNotes);
        Scanner inputScanner = new Scanner(System.in);

        String menu = """
                1. View notes
                2. Create note
                3. Delete note
                4. Modify note
                5. Export notes
                0. Exit""";
        MenuItems userInput;

        do {
            System.out.println(menu);
            System.out.print("Enter choice: ");
            userInput = MenuItems.getEnumFromString(inputScanner.nextLine());
            if (userInput == null){
                System.out.println("Invalid choice!");
                continue;
            }

            switch (userInput) {
                case VIEW -> new ViewService(storedNotes).run();

                case CREATE -> new CreateService(storedNotes, inputScanner).run();

                case DELETE -> {
                    if (Validator.noNotesCurrently(storedNotes)) {
                        System.out.println("Cannot delete a note if there are no notes!");
                        continue;
                    }

                    new DeleteService(storedNotes, inputScanner).run();
                }

                case UPDATE -> {
                    if (Validator.noNotesCurrently(storedNotes)) {
                        System.out.println("Cannot modify notes when there are no notes!");
                        continue;
                    }

                    System.out.print("Enter the note ID to update a note: ");
                    String noteIDToModify = inputScanner.nextLine();
                    Note chosenNote = storedNotes.getNote(noteIDToModify);

                    if (Validator.noteDoesNotExist(chosenNote)) {
                        System.out.println("Note does not exist! Returning to main menu...");
                        continue;
                    }

                    new UpdateService(storedNotes, inputScanner, chosenNote).run();

                }
                case EXPORT -> {
                    if (Validator.noNotesCurrently(storedNotes)) {
                        System.out.println("There are currently no notes. Returning to main menu.");
                        continue;
                    }

                    new ExportService(storedNotes, inputScanner, noteExporter).run();
                }
            }
        } while (!Objects.equals(userInput, MenuItems.EXIT));
    }
}
