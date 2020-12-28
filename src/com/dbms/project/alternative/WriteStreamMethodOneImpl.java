package com.dbms.project.alternative;

import com.dbms.project.streamInterfaces.WriteStreamInterface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteStreamMethodOneImpl implements WriteStreamInterface {
    File file;
    FileWriter fw;

    // creating a new file for writing and a new FileWriter, which is meant for writing streams of characters
    @Override
    public void create(String name) throws IOException {
        file = new File(name);
        fw = new FileWriter(file);
    }

    //writing the string to the file writer and appending the end of line symbol
    @Override
    public void writeLn(String data) throws IOException {
        fw.write(data);
        fw.append("\n");
    }

    //no more 'writeln' operations can be done on the stream, and therefore the created file through this stream
    //and the corresponding resources associated with the stream are released
    @Override
    public void close() throws IOException {
        fw.close();
    }
}
