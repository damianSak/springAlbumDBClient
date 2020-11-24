package org.melon.albumdbclient.utils;

import java.util.Locale;

/**
 * by putting as a 'c = \n' (end of the line) you
 * can count how many rows are for example in printHeading ended by that way
 */

public class StringUtils {

    public static int countChar(String str, char c) {
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c)
                count++;
        }
        return count;
    }

    public static void printSingleRecord(String title, String band, String genre, int releaseYear) {
        System.out.format(Locale.GERMAN, "%-25s|%-19s|%-17s| %-13s\n",
                title, band, genre, releaseYear);

    }
    public static String selectDbPatch(){
        return "D:\\java\\Baza danych płyt\\";
    }
}
