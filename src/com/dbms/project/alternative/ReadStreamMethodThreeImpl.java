package com.dbms.project.alternative;

import com.dbms.project.streamInterfaces.ReadStreamInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReadStreamMethodThreeImpl implements ReadStreamInterface {
    File file;
    FileReader fileReader;

    Boolean endOfStream = false;

    int cursor; // Current position
    BufferedReader buffer;
    int B;

    public ReadStreamMethodThreeImpl(int B) {
        this.file = null;
        this.fileReader = null;
        this.buffer = null;
        this.B = B;
    }

    // open an existing file for reading by creating a new FileReader, which is meant for reading streams of characters
    // by wrapping a BufferedReader around it, chunks of characters are read and stored in an internal buffer.
    // While the buffer has data, the reader will read from it instead of directly from the underlying stream.
    // the integer B specifies, in bytes, how big this buffer should be.
    @Override
    public void open(String path) throws FileNotFoundException {
        file = new File(path);
        this.fileReader = new FileReader(file);
        this.buffer = new BufferedReader(fileReader,B);
    }

    //the read operation on a buffered reader reads one character at a time, so a loop is implemented
    //it performs the operation until an end of line symbol is read, or the end of the file is reached
    //in this case the stream is closed and the end of stream field is set to true.
    @Override
    public String readLn() throws IOException {
        //all the read characters are being added to currentString until \n is read
        String currentString = "";
        int endLine = '\n';

        while (((cursor = buffer.read() ) != endLine) && (cursor != -1)) {
            currentString = currentString + (char)cursor;
        }
        if (cursor == -1) {
            endOfStream = true;
            buffer.close();
        }
        //System.out.println(currentString);
        return  currentString;

    }

    //reinitializing a new file reader and buffered reader, and using ‘skip’ on it to skip characters until the correct position.
    @Override
    public void seek(int pos) throws IOException {
        buffer = new BufferedReader(new FileReader(file),B);
        buffer.skip(pos);
    }
    //returning a boolean: true if the end of the stream was reached, and false otherwise
    @Override
    public boolean endOfStream() {
        return endOfStream;
    }
}
