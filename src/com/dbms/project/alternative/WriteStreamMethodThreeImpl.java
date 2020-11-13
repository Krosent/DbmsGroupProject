package com.dbms.project.alternative;

import com.dbms.project.streamInterfaces.WriteStreamInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteStreamMethodThreeImpl implements WriteStreamInterface {
    File file;
    FileWriter fw;
    BufferedWriter buffer;
    int B;

    public WriteStreamMethodThreeImpl(int B) {
        this.file = null;
        this.B = B;
    }

    @Override
    public void create(String name) throws IOException {
        file = new File(name);
        fw = new FileWriter(file);
        buffer = new BufferedWriter(fw,B);

    }

    @Override
    public void writeLn(String data) throws IOException {
        buffer.write(data);
        buffer.append("\n");
    }

    @Override
    public void close() throws IOException {
        buffer.close();
    }
}
