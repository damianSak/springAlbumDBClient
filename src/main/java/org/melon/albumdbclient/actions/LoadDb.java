package org.melon.albumdbclient.actions;

import org.melon.albumdbclient.utils.ConsoleInputProvider;
import org.melon.albumdbclient.utils.StringUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadDb {

    private String dbPath = StringUtils.selectDbPatch();

    protected void loadDBdir() {
        File file = new File(dbPath);
        String[] dirList = file.list();
        for (int i = 0; i < dirList.length; i++) {
            System.out.println(dirList[i]);
        }
    }

    private void dbReader(File dbFile){
        try (Scanner scan = new Scanner(dbFile)) {
            while (scan.hasNext()) {
                System.out.println(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public File loadDbFromFile() {
        File file;

        System.out.println("\nZawartość katalogu z Bazami Płyt:");
        loadDBdir();

        System.out.println("\nWybierz bazę do wczytania:");
            do {
                String dbName = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                file = new File(dbPath + "\\" + dbName + ".txt");
                if (file.exists()) {
                    dbReader(file);
                } else {
                    System.out.println("Niepoprawna nazwa pliku do odczytu, podaj właściwą nazwę:");
                }
            } while (!file.exists());

        return file;
    }
}

