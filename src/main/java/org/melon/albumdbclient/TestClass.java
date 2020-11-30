package org.melon.albumdbclient;

import org.melon.albumdbclient.actions.DeleteRecord;

import java.io.IOException;
import java.net.URISyntaxException;

public class TestClass {
    public static void main(String[] args) throws IOException, URISyntaxException {
//        AddRecord addRecord = new AddRecord();
        DeleteRecord deleteRecord = new DeleteRecord();

//        addRecord.addRecordToDb();
        deleteRecord.deleteRecordFromDb();
    }
}
