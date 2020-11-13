package com.dbms.project.alternative;

import com.dbms.project.streamInterfaces.WriteStreamInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteStreamMethodTwoImpl implements WriteStreamInterface {
    File file;
    BufferedWriter bw;

    @Override
    public void create(String name) throws IOException {
        file = new File(name);
        bw = new BufferedWriter(new FileWriter(file));
    }

    @Override
    public void writeLn(String data) throws IOException {
        bw.write(data);
        bw.append("\n");
    }

    @Override
    public void close() throws IOException {
        bw.close();
    }
}
