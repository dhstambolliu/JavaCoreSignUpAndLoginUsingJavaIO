package com.dhstambolliu;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        init();
    }

    private static void init() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        MainMenu object = new MainMenu();
        object.mainMenu();
        System.out.print("\nPlease Enter Menu choice: ");
        int caseNumber = Integer.parseInt(scanner.nextLine());

        switch (caseNumber) {
            case 1: {
                object.login();
                break;
            }
            case 2: {
                object.signUp();
                break;
            }
        }
    }
}
