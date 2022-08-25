package com.br1ght.Services;

import com.br1ght.Models.NoteBucket;

import java.util.Scanner;

public class CreateService extends BaseService {
    private final Scanner inputScanner;

    public CreateService(NoteBucket noteBucket, Scanner inputScanner) {
        super(noteBucket);
        this.inputScanner = inputScanner;
    }

    @Override
    public void run() {
        System.out.print("Enter author name: ");
        String author = inputScanner.nextLine();

        System.out.println("Enter content: ");
        String content = inputScanner.nextLine();

        System.out.println(noteBucket.addNote(author, content));
    }
}
