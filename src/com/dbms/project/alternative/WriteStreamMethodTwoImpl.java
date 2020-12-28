package com.dbms.project.alternative;

import com.dbms.project.streamInterfaces.WriteStreamInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteStreamMethodTwoImpl implements WriteStreamInterface {
    File file;
    BufferedWriter bw;

    // creating a new file for writing and a new FileWriter, which is meant for writing streams of characters
    // by wrapping a BufferedWriter around it, chunks of characters that need to be written are stored in an internal buffer.
    // and not written directly to the underlying stream, this is only done when the buffer has reached its capacity or the stream was closed.
    // the buffer is 8KB big by default
    @Override
    public void create(String name) throws IOException {
        file = new File(name);
        bw = new BufferedWriter(new FileWriter(file));
    }

    //writing the string and end of line symbol to the buffer, which will write it to the file writer when the buffer is full
    @Override
    public void writeLn(String data) throws IOException {
        bw.write(data);
        bw.append("\n");
    }

    //no more 'writeln' operations can be done on the stream, and therefore the created file through this stream
    //and the corresponding resources associated with the stream are released
    @Override
    public void close() throws IOException {
        bw.close();
    }
}
