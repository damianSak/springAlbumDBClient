package org.melon.albumdbclient.utils;

import java.util.Locale;

public class StringUtils {

    public static void printSingleRecordWithHeadingAndEnding(int id, String title, String band, String genre, int releaseYear) {
        System.out.println(returnHeading());
        System.out.format(Locale.GERMAN, "%-8s|%-31s|%-25s|%-23s| %-15s\n",
                id, title, band, genre, releaseYear);
        System.out.println(returnEnding());

    }

    public static void printSingleRecord(int id, String title, String band, String genre, int releaseYear) {

        System.out.format(Locale.GERMAN, "%-8s|%-31s|%-25s|%-23s| %-15s\n",
                id, title, band, genre, releaseYear);

    }

    public static String returnHeading() {

        return "--------+-------------------------------+-------------------------+-----------------------+---------------\n" +
                "   ID   |             TYTUŁ             |        WYKONAWCA        |        GATUNEK        |  ROK WYDANIA \n" +
                "--------+-------------------------------+-------------------------+-----------------------+---------------";

    }

    public static String returnEnding() {

        return "--------+-------------------------------+-------------------------+-----------------------+---------------";

    }

}
