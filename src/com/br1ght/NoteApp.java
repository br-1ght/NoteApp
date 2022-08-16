package com.br1ght;


import java.util.Objects;
import java.util.Scanner;

public class NoteApp {
     NoteApp() {
        NoteBucket storedNotes = new NoteBucket();
        String menu = """
                1. View notes
                2. Create note
                3. Delete note
                4. Modify note
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
                case VIEW -> System.out.println(storedNotes);
                case CREATE -> System.out.println(storedNotes.addNote());
                case DELETE -> {
                    System.out.println("Enter the note ID to delete a note: ");
                    String noteIDToDelete = inputScanner.nextLine();
                    System.out.println(storedNotes.deleteNote(noteIDToDelete));
                }
                case UPDATE -> {
                    System.out.println("Enter the note ID to update a note: ");
                    String noteIDToModify = inputScanner.nextLine();
                    storedNotes.modifyNote(noteIDToModify);
                }
            }
        } while (!Objects.equals(userInput, MenuItems.EXIT));
    }
}
