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

    @Override
    public void open(String path) throws FileNotFoundException {
        file = new File(path);
        this.fileReader = new FileReader(file);
        this.buffer = new BufferedReader(fileReader,B);
    }

    @Override
    public void readLn() throws IOException {
        List<Character> charList = new ArrayList<>();
        int endLine = '\n';

        while (((cursor = buffer.read() ) != endLine) && (cursor != -1)) {
            char ch = (char)cursor;
            charList.add(ch);
        }
        if (cursor == -1) {
            endOfStream = true;
            buffer.close();
        }
        String charListToString = charList.stream().map(e->e.toString()).collect(Collectors.joining());
        System.out.println("Character list with buffer: "+ charListToString);

    }

    @Override
    public void seek(int pos) throws IOException {
        // To mimic seek functionality let's read file until indicated position.
        // Positions starts with 0 as arrays in Java.
        for(int i=0; i<=pos; i++) {
            if(buffer.read() != -1) {
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
