package com.br1ght;

import java.util.*;

public class NoteBucket {
    private final List<Note> bucket = new ArrayList<>();
    Scanner inputScanner = new Scanner(System.in);

    private Note getNote(String noteID) {
        // Retrieve note by note ID
        return bucket.stream()
                .filter(note -> note.getNoteID().toString().equals(noteID))
                .findFirst()
                .orElse(null);
    }

    public String addNote() {
        System.out.println("Enter author name: ");
        String author = inputScanner.nextLine();

        System.out.println("Enter content: ");
        String content = inputScanner.nextLine();
        Note newNote = new Note(author, content);
        bucket.add(newNote);
        return "Note with ID " + newNote.getNoteID() + " added!";
    }

    public String deleteNote(String noteIDToDelete) {
        Note note = getNote(noteIDToDelete);
        if (note == null) {
            return "Note with ID " + noteIDToDelete + " does not exist.";
        }
        bucket.remove(note);
        return "Note with ID " + noteIDToDelete + " is deleted!";
    }

    public void modifyNote(String noteIDToModify) {
        Note note = getNote(noteIDToModify);
        if (note == null) {
            System.out.println("Note with ID " + noteIDToModify + " does not exist!");
            return;
        }

        String modifyMenu = """
                0. Go back
                1. Modify author
                2. Modify content""";
        String modifyChoice;

        do {
            System.out.println(modifyMenu);
            System.out.println("Enter choice: ");
            modifyChoice = inputScanner.nextLine();
            switch (modifyChoice) {
                case "1" -> {
                    System.out.println("Enter new author: ");
                    String authorToModify = inputScanner.nextLine();
                    note.setAuthor(authorToModify);
                    System.out.println("Note's author modified!");
                }
                case "2" -> {
                    System.out.println("Enter new content: ");
                    String contentToModify = inputScanner.nextLine();
                    note.setContent(contentToModify);
                    System.out.println("Note's content modified");
                }
            }
        } while (!modifyChoice.equals("0"));
    }


    @Override
    public String toString() {
        if (bucket.isEmpty()) {
            return "There are currently no notes.";
        }

        StringBuilder output = new StringBuilder();
        for (Note note:
             bucket) {
            output.append("Note ID: ");
            output.append(note.getNoteID());
            output.append("\n");
            output.append("Note Author: ");
            output.append(note.getAuthor());
            output.append("\n");
            output.append("Note Content:\n");
            output.append(note.getContent());
            output.append("\n");
        }
        return output.toString();
    }
}
