package com.br1ght.Services;

import com.br1ght.Models.Note;
import com.br1ght.Models.NoteBucket;

import java.util.Scanner;

public class UpdateService extends BaseService {

    Scanner inputScanner;
    Note chosenNote;

    public UpdateService(NoteBucket noteBucket, Scanner inputScanner, Note chosenNote) {
        super(noteBucket);
        this.inputScanner = inputScanner;
        this.chosenNote = chosenNote;
    }

    @Override
    public void run() {
        String modifyMenu = """
                0. Exit
                1. Modify author
                2. Modify content""";
        String modifyChoice;

        do {
            System.out.println(modifyMenu);
            modifyChoice = inputScanner.nextLine();

            switch (modifyChoice) {
                case "1" -> {
                    System.out.print("Enter new author (Leave empty if no change): ");
                    String authorToModify = inputScanner.nextLine();
                    System.out.println(noteBucket.modifyAuthor(chosenNote, authorToModify));
                }

                case "2" -> {
                    System.out.println("Enter new content (Leave empty if no change): ");
                    String contentToModify = inputScanner.nextLine();
                    System.out.println(noteBucket.modifyContent(chosenNote, contentToModify));
                }
            }
        } while (!modifyChoice.equals("0"));
    }
}
