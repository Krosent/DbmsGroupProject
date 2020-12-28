package com.dbms.project.alternative;

import com.dbms.project.streamInterfaces.ReadStreamInterface;

import java.io.*;

public class ReadStreamMethodTwoImpl implements ReadStreamInterface {
    File file;
    BufferedReader br;
    Boolean endOfStream = false;
    String line;

    // open an existing file for reading by creating a new FileReader, which is meant for reading streams of characters
    // by wrapping a BufferedReader around it, chunks of characters are read and stored in an internal buffer.
    // While the buffer has data, the reader will read from it instead of directly from the underlying stream.
    @Override
    public void open(String path) throws FileNotFoundException {
        file = new File(path);
        this.br = new BufferedReader(new FileReader(file));
    }

    @Override
    public String readLn() throws IOException {
        if((line = br.readLine()) != null) {
            //System.out.println("Line: " + line);
            return line;
        }else {
            endOfStream = true;
            br.close();
            return "";
        }
    }

    //reinitializing a new file reader and buffered reader, and using ‘skip’ on it to skip characters until the correct position.
    @Override
    public void seek(int pos) throws IOException {
        br = new BufferedReader(new FileReader(file));
        br.skip(pos);

    }
    //returning a boolean: true if the end of the stream was reached, and false otherwise
    @Override
    public boolean endOfStream() {
        return endOfStream;
    }
}
