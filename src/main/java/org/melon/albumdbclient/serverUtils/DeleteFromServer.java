package org.melon.albumdbclient.serverUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class DeleteFromServer {

    CloseableHttpClient httpClient = HttpClients.createDefault();

    public void deleteRecordFromDbById(int id) throws IOException {
        HttpDelete deleteRequest = new HttpDelete("http://localhost:8080/server/api/database/delete_by_id/" + id);
        CloseableHttpResponse response = httpClient.execute(deleteRequest);
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                HttpResponse deleteResponse = httpClient.execute(deleteRequest);

            }
        } finally {
            response.close();
        }
    }

}
