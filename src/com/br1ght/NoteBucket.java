package com.br1ght;

import java.util.*;

public class NoteBucket {
    public final List<Note> bucket = new ArrayList<>();

    private Note getNote(String noteID) {
        // Retrieve note by note ID
        return bucket.stream()
                .filter(note -> note.getNoteID().toString().equals(noteID))
                .findFirst()
                .orElse(null);
    }

    public String addNote(Scanner inputScanner) {
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
        if (Validator.noteDoesNotExist(note)) {
            return "Note with ID " + noteIDToDelete + " does not exist.";
        }
        bucket.remove(note);
        return "Note with ID " + noteIDToDelete + " is deleted!";
    }

    public void modifyNote(String noteIDToModify, Scanner inputScanner) {
        Note note = getNote(noteIDToModify);
        if (Validator.noteDoesNotExist(note)) {
            System.out.println("Note with ID " + noteIDToModify + " does not exist!");
            return;
        }

        System.out.println("Enter new author (Leave empty if no change): ");
        String authorToModify = inputScanner.nextLine();
        if (!authorToModify.equals("")) {
           note.setAuthor(authorToModify);
        }
        System.out.println("Enter new content (Leave empty if no change): ");
        String contentToModify = inputScanner.nextLine();
        if (!contentToModify.equals("")) {
            note.setContent(contentToModify);
        }
    }

    public String viewNotes() {
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
