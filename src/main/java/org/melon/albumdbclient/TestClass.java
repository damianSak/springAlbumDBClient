package org.melon.albumdbclient;

import org.json.JSONException;
import org.melon.albumdbclient.actions.AddRecord;
import org.melon.albumdbclient.actions.DeleteRecord;
import org.melon.albumdbclient.actions.FindRecord;

import java.io.IOException;
import java.net.URISyntaxException;

public class TestClass {
    public static void main(String[] args) throws IOException, JSONException, URISyntaxException {
        AddRecord addRecord = new AddRecord();
        DeleteRecord deleteRecord = new DeleteRecord();
        FindRecord findRecord = new FindRecord();

//        addRecord.addRecordToDb();
//        deleteRecord.deleteRecordFromDb();

        findRecord.findRecord();
    }
}
