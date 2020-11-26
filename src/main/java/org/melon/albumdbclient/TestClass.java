package org.melon.albumdbclient;

import org.melon.albumdbclient.actions.AddRecord;

import java.io.IOException;

public class TestClass {
    public static void main(String[] args) throws IOException {
        AddRecord addRecord = new AddRecord();

        addRecord.addRecordToDb();
        System.out.println();
    }
}
