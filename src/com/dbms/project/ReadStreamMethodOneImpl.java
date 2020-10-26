package com.dbms.project;

import com.dbms.project.streamInterfaces.ReadStreamInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadStreamMethodOneImpl implements ReadStreamInterface {
    File file;
    FileReader fileReader;

    Boolean endOfStream = false;

    int cursor; // Current position

    public ReadStreamMethodOneImpl() {
        this.file = null;
        this.fileReader = null;
    }

    @Override
    public void open(String path) throws FileNotFoundException {
        file = new File(path);
        this.fileReader = new FileReader(file);
    }

    @Override
    public void readLn() throws IOException {

        while((cursor = fileReader.read()) != -1) {
            System.out.println("cursor: " + cursor);
        }

        endOfStream = true;
        fileReader.close();

    }

    @Override
    public void seek(int pos) throws IOException {
        // To mimic seek functionality let's read file until indicated position.
        // Positions starts with 0 as arrays in Java.
        for(int i=0; i<=pos; i++) {
            if(fileReader.read() != -1) {
                continue;
            } else {
                break;
            }
        }
    }

    @Override
    public boolean endOfStream() {
        return endOfStream;
    }
}
