package com.br1ght.Validators;

import com.br1ght.Models.Note;
import com.br1ght.Models.NoteBucket;

public class Validator {
    public static boolean noteDoesNotExist(Note note) {
        return note == null;
    }

    public static boolean noNotesCurrently(NoteBucket notebucket) {
        return notebucket.size() == 0;
    }
}
