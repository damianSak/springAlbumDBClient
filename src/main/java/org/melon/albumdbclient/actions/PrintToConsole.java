package org.melon.albumdbclient.actions;

public class PrintToConsole {

//    public PrintToConsole() {
//    }
//
    public String printHeading() {
        return "-------------------------+-------------------+-----------------+-------------\n" +
                "          Tytuł          |     Wykonawca     |     Gatunek     | Rok Wydania \n" +
                "-------------------------+-------------------+-----------------+-------------";
    }
//
//
//    protected void printAlbumRecrds() {
//        for (Album album : albums) {
//            StringUtils.printSingleRecord(album.getTitle(), album.getBand(), album.getGenre(), album.getReleaseYear());
//        }
//    }
//
    public String printEnding() {
        return "-------------------------+-------------------+-----------------+-------------";
    }
//
//    public void printAlbumsDbOnConsole() {
//
//        System.out.println(printHeading());
//        printAlbumRecrds(albums);
//        System.out.println(" ");
//    }
}
