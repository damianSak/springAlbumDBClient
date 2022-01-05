package org.melon.albumdbclient.actions;

import org.json.JSONException;
import org.melon.albumdbclient.model.Album;
import org.melon.albumdbclient.utils.ServerUtils;
import org.melon.albumdbclient.utils.StringUtils;

import java.io.IOException;
import java.util.List;

public class PrintToConsole {

    private ServerUtils serverUtils;

    public PrintToConsole() {
        this.serverUtils = new ServerUtils();
    }

    protected void printAlbumRecords(List<Album> albums) {
        for (Album album : albums) {
            StringUtils.printSingleRecord(album.getId(), album.getTitle(), album.getBand(), album.getGenre(),
                    album.getReleaseYear());
        }
    }

    protected void printAlbumRecords(Album album) {
        StringUtils.printSingleRecord(album.getId(), album.getTitle(), album.getBand(), album.getGenre(),
                album.getReleaseYear());
    }

    public void printAlbumsDbListOnConsole(List<Album> albums) {

        System.out.println(StringUtils.returnHeading());
        printAlbumRecords(albums);
        System.out.println(StringUtils.returnEnding());
    }

    public void printAllAlbumsFromDbOnConsole() throws IOException, JSONException {
        List<Album> albums = serverUtils.findAllAlbums();
        printAlbumsDbListOnConsole(albums);
    }

    public void printSingleAlbumsOnConsole(Album album) {
        System.out.println(StringUtils.returnHeading());
        printAlbumRecords(album);
        System.out.println(StringUtils.returnEnding());
    }

}
