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

    // creating a new file for writing and a new FileWriter, which is meant for writing streams of characters
    // by wrapping a BufferedWriter around it, chunks of characters that need to be written are stored in an internal buffer.
    // and not written directly to the underlying stream, this is only done when the buffer has reached its capacity or the stream was closed.
    // the integer B specifies, in bytes, how big this buffer should be.
    @Override
    public void create(String name) throws IOException {
        file = new File(name);
        fw = new FileWriter(file);
        buffer = new BufferedWriter(fw,B);
    }

    //writing the string and end of line symbol to the buffer, which will write it to the file writer when the buffer is full
    @Override
    public void writeLn(String data) throws IOException {
        buffer.write(data);
        buffer.append("\n");
    }

    //no more 'writeln' operations can be done on the stream, and therefore the created file through this stream
    //and the corresponding resources associated with the stream are released
    @Override
    public void close() throws IOException {
        buffer.close();
    }
}
