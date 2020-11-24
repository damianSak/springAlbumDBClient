package org.melon.albumdbclient.serverUtils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetFromServer {
    CloseableHttpClient httpClient = HttpClients.createDefault();


    public void printAllAlbumsOnConsole() throws IOException {
        HttpGet request = new HttpGet("http://localhost:8080/server/api/database/find_all");
        CloseableHttpResponse response = httpClient.execute(request);
        try {
            HttpEntity entity = response.getEntity();
            if(entity !=null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }finally{
            response.close();
        }
    }
    public void printOneAlbumOnConsole(int albumID) throws IOException{
        HttpGet request = new HttpGet("http://localhost:8080/server/api/database/find/id/"+albumID);
        CloseableHttpResponse response = httpClient.execute(request);
        try {
            HttpEntity entity = response.getEntity();
            if(entity !=null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }finally{
            response.close();
        }
    }
    public void findAlbumsByBandName(String band)throws IOException{
        HttpGet request = new HttpGet("http://localhost:8080/server/api/database/find/band/"+band);
        CloseableHttpResponse response = httpClient.execute(request);
        try {
            HttpEntity entity = response.getEntity();
            if(entity !=null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }finally{
            response.close();
        }
    }
    public void findAlbumsByTitle(String title)throws IOException{
        HttpGet request = new HttpGet("http://localhost:8080/server/api/database/find/title/"+title);
        CloseableHttpResponse response = httpClient.execute(request);
        try {
            HttpEntity entity = response.getEntity();
            if(entity !=null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }finally{
            response.close();
        }
    }

    public void findAlbumsByReleaseYear(int releaseYear) throws IOException{
        HttpGet request = new HttpGet("http://localhost:8080/server/api/database/find/releaseYear/"+releaseYear);
        CloseableHttpResponse response = httpClient.execute(request);
        try {
            HttpEntity entity = response.getEntity();
            if(entity !=null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }finally{
            response.close();
        }
    }

    public void findAlbumsByGenre(String genre) throws IOException{
        HttpGet request = new HttpGet("http://localhost:8080/server/api/database/find/genre/"+genre);
        CloseableHttpResponse response = httpClient.execute(request);
        try {
            HttpEntity entity = response.getEntity();
            if(entity !=null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }finally{
            response.close();
        }
    }
}
