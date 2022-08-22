package com.br1ght;

import java.util.*;

public class NoteBucket {
    private final List<Note> bucket = new ArrayList<>();

    public int size() {
        return bucket.size();
    }

    public List<Note> getAllNotes() { return bucket; }

    public Note getNote(String noteID) {
        // Retrieve note by note ID
        return bucket.stream()
                .filter(note -> note.getNoteID().toString().equals(noteID))
                .findFirst()
                .orElse(null);
    }

    public String addNote(String author, String content) {
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

    public String modifyAuthor(Note note, String author) {
        if (!author.equals("")) {
           note.setAuthor(author);
           return "Author changed!";
        } else {
            return "No changes to author has been applied.";
        }
    }

    public String modifyContent(Note note, String content) {
        if (!content.equals("")) {
            note.setContent(content);
            return "Content changed!";
        } else {
            return "No changes to content has been applied.";
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
