package com.dbms.project.alternative;

import com.dbms.project.streamInterfaces.ReadStreamInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReadStreamMethodOneImpl implements ReadStreamInterface {
    File file;
    FileReader fileReader;

    Boolean endOfStream = false;

    int cursor; // Current position

    public ReadStreamMethodOneImpl() {
        this.file = null;
        this.fileReader = null;
    }

    // open an existing file for reading by creating a new FileReader, which is meant for reading streams of characters
    @Override
    public void open(String path) throws FileNotFoundException {
        file = new File(path);
        this.fileReader = new FileReader(file);
    }

    //the read operation on a file reader reads one character at a time, so a loop is implemented
    //it performs the operation until an end of line symbol is read, or the end of the file is reached
    //in this case the stream is closed and the end of stream field is set to true.
    @Override
    public String readLn() throws IOException {
        //all the read characters are being added to currentString until \n is read
        String currentString = "";
        int endLine = '\n';

        while(((cursor = fileReader.read() ) != endLine) && (cursor != -1)) {
            currentString = currentString + (char)cursor;
        }
        if (cursor == -1) {
            endOfStream = true;
            fileReader.close();
        }
        //System.out.println(currentString);
        return currentString;

    }

    //reinitializing a new file reader and using ‘skip’ on it to skip until the correct position.
    //The skip operation of the file reader skips bytes instead of characters, so this should be considered when using this seek operation.
    @Override
    public void seek(int pos) throws IOException {
        fileReader = new FileReader(file);
        fileReader.skip(pos);
    }

    //returning a boolean: true if the end of the stream was reached, and false otherwise
    @Override
    public boolean endOfStream() {
        return endOfStream;
    }
}
