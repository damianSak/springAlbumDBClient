import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.melon.albumdbclient.model.Album;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Test {


    public static void main(String[] args) throws ClientProtocolException, IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://localhost:8080/server/api/database/find_all");

        HttpResponse response = client.execute(request);
        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        System.out.println("Podaj leszczu id do usunięcua z listy albumów");
        Scanner scan = new Scanner(System.in);
        int id= scan.nextInt();
        HttpDelete deleteRequest = new HttpDelete("http://localhost:8080/server/api/database/delete_by_id/"+id);

        HttpResponse deleteResponse = client.execute(deleteRequest);
        deleteResponse.getEntity().getContent().close();



        HttpPost post = new HttpPost("http://localhost:8080/server/api/database/add");
        post.addHeader("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString( new Album("Marilyn Manson","The pale emperor","Hard rock",2015) );
        StringEntity input = new StringEntity(json);
        post.setEntity(input);
        HttpResponse response2 = client.execute(post);
        BufferedReader br2 = new BufferedReader(new InputStreamReader(response2.getEntity().getContent()));
        String line2 = "";
        while ((line2 = br2.readLine()) != null) {
            System.out.println(line2);
        }
    }


    }
