package com.br1ght;

public class NoteExporter {
    private final NoteBucket noteBucket;

    NoteExporter(NoteBucket noteBucket) {
        this.noteBucket = noteBucket;
    }
    StringBuilder stringBuilder = new StringBuilder();

    public void exportToJson() {
        if (noteBucket.bucket.isEmpty()) {
            System.out.println("There are currently no notes. Aborting export.");
            return;
        }
        for (Note note:
             noteBucket.bucket) {
            stringBuilder.append("{\n");
            stringBuilder.append("\t'id': '%s',\n".formatted(note.getNoteID()));
            stringBuilder.append("\t'author': '%s',\n".formatted(note.getAuthor()));
            stringBuilder.append("\t'content': '%s'\n".formatted(note.getContent()));
            stringBuilder.append("}");
        }
        System.out.println(stringBuilder);
    }
}
