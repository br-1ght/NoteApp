package com.br1ght.Services;

import com.br1ght.Models.NoteBucket;

import java.util.Scanner;

public class DeleteService extends BaseService {
    private final Scanner inputScanner;

    public DeleteService(NoteBucket noteBucket, Scanner inputScanner) {
        super(noteBucket);
        this.inputScanner = inputScanner;
    }

    @Override
    public void run() {
        System.out.print("Enter the note ID to delete a note: ");
        String noteIDToDelete = inputScanner.nextLine();
        System.out.println(noteBucket.deleteNote(noteIDToDelete));
    }
}
