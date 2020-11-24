package org.melon.albumdbclient.serverUtils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetFromServer {
    HttpClient client = new DefaultHttpClient();
    HttpGet request = new HttpGet("http://localhost:8080/server/api/database/find_all");

    public void printAllAlbumsOnConsole() throws IOException {
        HttpResponse response = client.execute(request);
        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }


    }
    public void printOneAlbumOnConsole(){

    }
    public void findAlbumsByBandName(){

    }
    public void findAlbumsByTitle(){

    }
    public void findAlbumsByReleaseYear(){

    }

}
