package com.br1ght.App;


import com.br1ght.ImportExport.NoteExporter;
import com.br1ght.Models.Note;
import com.br1ght.Models.NoteBucket;
import com.br1ght.Validators.Validator;

import java.util.Objects;
import java.util.Scanner;

public class NoteApp {
     public NoteApp() {
        NoteBucket storedNotes = new NoteBucket();
        NoteExporter noteExporter = new NoteExporter(storedNotes);
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
            System.out.println("Enter choice: ");
            Scanner inputScanner = new Scanner(System.in);
            userInput = MenuItems.getEnumFromString(inputScanner.nextLine());
            if (userInput == null){
                System.out.println("Invalid choice!");
                continue;
            }

            switch (userInput) {
                case VIEW -> System.out.println(storedNotes.viewNotes());
                case CREATE ->{
                    System.out.println("Enter author name: ");
                    String author = inputScanner.nextLine();

                    System.out.println("Enter content: ");
                    String content = inputScanner.nextLine();

                    System.out.println(storedNotes.addNote(author, content));
                }

                case DELETE -> {
                    if (Validator.noNotesCurrently(storedNotes)) {
                        System.out.println("Cannot delete a note if there are no notes!");
                        continue;
                    }

                    System.out.println("Enter the note ID to delete a note: ");
                    String noteIDToDelete = inputScanner.nextLine();
                    System.out.println(storedNotes.deleteNote(noteIDToDelete));
                }

                case UPDATE -> {
                    if (Validator.noNotesCurrently(storedNotes)) {
                        System.out.println("Cannot modify notes when there are no notes!");
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
                            case "1" -> {
                                System.out.println("Enter new author (Leave empty if no change): ");
                                String authorToModify = inputScanner.nextLine();
                                System.out.println(storedNotes.modifyAuthor(chosenNote, authorToModify));
                            }

                            case "2" ->{
                                System.out.println("Enter new content (Leave empty if no change): ");
                                String contentToModify = inputScanner.nextLine();
                                System.out.println(storedNotes.modifyContent(chosenNote, contentToModify));
                            }
                        }
                    } while (!modifyChoice.equals("0"));

                }
                case EXPORT -> {
                    if (Validator.noNotesCurrently(storedNotes)) {
                        System.out.println("There are currently no notes. Returning to main menu.");
                        continue;
                    }
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
        } while (!Objects.equals(userInput, MenuItems.EXIT));
    }
}
