package org.melon.albumdbclient;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try{
        new AlbumsCollection().start();
    }catch (IOException e){
            e.printStackTrace();
        }
    }
}
