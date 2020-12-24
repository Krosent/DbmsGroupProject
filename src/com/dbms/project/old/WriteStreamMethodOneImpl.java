package com.dbms.project.old;

import com.dbms.project.streamInterfaces.WriteStreamInterface;

import java.io.*;

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
        close();
    }

    @Override
    public void close() throws IOException {
        fw.close();
    }
}
