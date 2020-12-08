package org.melon.albumdbclient.utils;

import java.util.Locale;

public class StringUtils {

    public static void printSingleRecord( int id,String title, String band, String genre, int releaseYear) {
        System.out.format(Locale.GERMAN, "%-8s%-25s|%-19s|%-17s| %-13s\n",
                id,title, band, genre, releaseYear);

    }

}
