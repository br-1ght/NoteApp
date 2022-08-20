package com.br1ght;


import java.util.Objects;
import java.util.Scanner;

public class NoteApp {
     NoteApp() {
        NoteBucket storedNotes = new NoteBucket();
        NoteExporter noteExporter = new NoteExporter(storedNotes);
        String menu = """
                1. View notes
                2. Create note
                3. Delete note
                4. Modify note
                5. Export notes to Json
                0. Exit""";
        MenuItems userInput;

        do {
            System.out.println(menu);
            System.out.println("Enter choice: ");
            Scanner inputScanner = new Scanner(System.in);
            userInput = MenuItems.getEnumFromString(inputScanner.nextLine());
            if (userInput == null){
                System.out.println("Invalid choice!");
                continue;
            }

            switch (userInput) {
                case VIEW -> System.out.println(storedNotes.viewNotes());
                case CREATE -> System.out.println(storedNotes.addNote(inputScanner));
                case DELETE -> {
                    System.out.println("Enter the note ID to delete a note: ");
                    String noteIDToDelete = inputScanner.nextLine();
                    System.out.println(storedNotes.deleteNote(noteIDToDelete));
                }
                case UPDATE -> {
                    if (storedNotes.bucket.isEmpty()) {
                        System.out.println("Cannot modify when there are no notes!");
                        continue;
                    }
                    System.out.println("Enter the note ID to update a note: ");
                    String noteIDToModify = inputScanner.nextLine();
                    Note chosenNote = storedNotes.getNote(noteIDToModify);
                    if (Validator.noteDoesNotExist(chosenNote)) {
                        System.out.println("Note does not exist! Returning to main menu...");
                        continue;
                    }
                    String modifyMenu = """
                            0. Exit
                            1. Modify author
                            2. Modify content""";
                    String modifyChoice;
                    do {
                        System.out.println(modifyMenu);
                        modifyChoice = inputScanner.nextLine();
                        switch (modifyChoice) {
                            case "1" -> storedNotes.modifyAuthor(chosenNote, inputScanner);
                            case "2" -> storedNotes.modifyContent(chosenNote, inputScanner);
                        }
                    } while (!modifyChoice.equals("0"));

                }
                case EXPORT -> noteExporter.exportToJson();
            }
        } while (!Objects.equals(userInput, MenuItems.EXIT));
    }
}