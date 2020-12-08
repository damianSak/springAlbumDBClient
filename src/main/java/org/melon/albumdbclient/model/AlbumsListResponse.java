package org.melon.albumdbclient.model;

import java.util.List;

public class AlbumsListResponse {

    private String message;
    private List<Album> albumList;

    public AlbumsListResponse() {

    }

    public AlbumsListResponse(String message, List<Album> albumList) {
        this.message = message;
        this.albumList = albumList;
    }

    public static AlbumsListResponse of(List<Album>albumList , String message){
        return new AlbumsListResponse(message,albumList);
    }

    public String getMessage() {
        return message;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }
}
