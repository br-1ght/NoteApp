package com.br1ght;

import java.util.UUID;

public class Note {
    private final UUID noteID;
    private String author;
    private String content;


    public Note(String author, String content) {
        this.noteID = UUID.randomUUID();
        this.author = author;
        this.content = content;
    }


    public UUID getNoteID() {
        return noteID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
