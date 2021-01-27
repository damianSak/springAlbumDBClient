package org.melon.albumdbclient.utils;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleInputProvider {

    static Scanner scanner = new Scanner(System.in);

    public static void waitForPresedEnterWithMessage(String message) {
        System.out.println(message);
        waitForPresedEnter();
    }

    public static void waitForPresedEnter() {
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int readIntFromUserHandlingEmptyInput() {
        int number = 0;
        boolean exceptionOccurred;
        do {
            try {
                exceptionOccurred = false;
                number = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                exceptionOccurred = true;
                System.out.println("Wprowadzona wartość nie jest liczbą całkowitą lub nic nie wprowadzonio, podaj własciwą liczbę");
            }
        } while (exceptionOccurred);
        return number;
    }

    public static String readStringFromUserHandlingEmptyInput() {
        String string;
        do {
            string = scanner.nextLine().trim();
            if (string.isEmpty()) {
                System.out.println("Nie wprowadzono żadnego słowa");
            }

        } while (string.isEmpty());
        return string;
    }

    public static String readStringFromUserWithEmptyInput() {
        String string;
        string = scanner.nextLine().trim();
        return string;
    }

    public static void closeScanner() {
        scanner.close();
    }
}
