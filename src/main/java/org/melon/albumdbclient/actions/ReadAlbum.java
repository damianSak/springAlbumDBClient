package org.melon.albumdbclient.actions;

import org.melon.albumdbclient.model.Album;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class ReadAlbum {

    public List<Album> readAlbumsDbToList(File chosenDb, int headingSize) {
        List<Album> albums = new LinkedList<>();
        try {
            List<String> linesFromDb = Files.readAllLines(Path.of(chosenDb.getPath()));
            List<String> albumsRecords = linesFromDb.subList(headingSize, linesFromDb.size() - 1);
            addAlbumToLoadingList(albums, albumsRecords);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return albums;
    }

    private void addAlbumToLoadingList(List<Album> albums, List<String> readLines) {
        for (String line : readLines) {
            String[] singleAlbumFields = line.split("\\|");
            String singleAlbumBand = singleAlbumFields[0].trim();
            String singleAlbumTitle = singleAlbumFields[1].trim();
            String singleAlbumGenre = singleAlbumFields[2].trim();
            String singleAlbumReleaseYear = singleAlbumFields[3].trim();
            albums.add(new Album(singleAlbumTitle, singleAlbumBand, singleAlbumGenre, Integer.parseInt(singleAlbumReleaseYear)));
        }
    }
}

