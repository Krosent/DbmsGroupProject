package com.dbms.project.alternative;

import com.dbms.project.streamInterfaces.WriteStreamInterface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteStreamMethodOneImpl implements WriteStreamInterface {
    File file;
    FileWriter fw;

    @Override
    public void create(String name) throws IOException {
        file = new File(name);
        fw = new FileWriter(file);
    }

    @Override
    public void writeLn(String data) throws IOException {
        fw.write(data);
        fw.append("\n");
    }

    @Override
    public void close() throws IOException {
        fw.close();
    }
}
