package com.br1ght;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteBucketTest {
    NoteBucket noteBucket = new NoteBucket();

    @BeforeEach
    void init() {
        noteBucket.bucket.add(new Note("bob", "hello"));
        noteBucket.bucket.add(new Note("james", "goodbye"));
    }

    @AfterEach
    void destroy() {
        noteBucket.bucket.clear();
    }

    @Test
    void testGetNoteIfExists() {
        // Get first note's id
        Note retrievedNote = noteBucket.getNote(noteBucket.bucket.get(0).getNoteID().toString());
        assertNotNull(retrievedNote);
    }

    @Test
    void testGetNoteIfDoesNotExist() {
        Note retrievedNote = noteBucket.getNote("invalid-note-id");
        assertNull(retrievedNote, "Invalid ID should be null");
    }

    @Test
    void testAddNote() {
       noteBucket.addNote("alice", "have a nice day!");
       assertEquals(3, noteBucket.size());
    }

    @Test
    void testDeleteNote() {
        String noteIDOfFirstNote = noteBucket.bucket.get(0).getNoteID().toString();
        assertEquals("Note with ID " + noteIDOfFirstNote + " is deleted!", noteBucket.deleteNote(noteIDOfFirstNote));
        assertEquals(1, noteBucket.size());
    }

    @Test
    void testDeleteNoteIfNoteDoesNotExist() {
        String invalidNoteID = "invalid-note-id";
        assertEquals("Note with ID " + invalidNoteID + " does not exist.", noteBucket.deleteNote(invalidNoteID));
        assertEquals(2, noteBucket.size());
    }

    @Test
    void testModifyAuthor() {
        Note modifiedNote = noteBucket.bucket.get(0);
        assertEquals("Author changed!",
                noteBucket.modifyAuthor(modifiedNote, "ryan"));
        assertEquals("ryan", modifiedNote.getAuthor());
    }

    @Test
    void testModifyAuthorIfEmpty() {
        Note modifiedNote = noteBucket.bucket.get(0);
        assertEquals("No changes to author has been applied.",
                noteBucket.modifyAuthor(modifiedNote, ""));
    }

    @Test
    void testModifyContent() {
        Note modifiedNote = noteBucket.bucket.get(0);
        assertEquals("Content changed!",
                noteBucket.modifyContent(modifiedNote, "greetings"));
        assertEquals("greetings", modifiedNote.getContent());
    }

    @Test
    void testModifyContentIfEmpty() {
        Note modifiedNote = noteBucket.bucket.get(0);
        assertEquals("No changes to content has been applied.",
                noteBucket.modifyContent(modifiedNote, ""));
    }

    @Test
    void testViewNotesIfThereAreNotes() {
        assertNotEquals("There are currently no notes.", noteBucket.viewNotes());
    }

    @Test
    void testViewNotesIfThereAreNoNotes() {
        noteBucket.bucket.clear();
        assertEquals("There are currently no notes.", noteBucket.viewNotes());
    }
}