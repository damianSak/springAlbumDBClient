package org.melon.albumdbclient.model;

public class AlbumDbResponse {

    private String message;
    private Album album;

    private AlbumDbResponse(Album album , String message) {
        this.message = message;
        this.album = album;
    }

    private AlbumDbResponse(String message) {
        this.message = message;
    }

    public static AlbumDbResponse of(Album album, String message) {
        return new AlbumDbResponse(album, message);
    }

    public static AlbumDbResponse of(String message){
        return new AlbumDbResponse( message);
    }

    public String getMessage() {
        return message;
    }

    public Album getAlbum() {
        return album;
    }

}
