package com.dbms.project;

import com.dbms.project.streamInterfaces.ReadStreamInterface;

import java.io.*;

public class ReadStreamMethodTwoImpl implements ReadStreamInterface {
    File file;
    BufferedReader br;
    Boolean endOfStream = false;
    String line;

    @Override
    public void open(String path) throws FileNotFoundException {
        file = new File(path);
        this.br = new BufferedReader(new FileReader(file));
    }

    @Override
    public void readLn() throws IOException {
        while((line = br.readLine()) != null) {
            System.out.println("Line: " + line);
        }
        endOfStream = true;
        br.close();
    }

    @Override
    public void seek(int pos) throws IOException {
        for(int i=0; i<=pos; i++) {
            if (br.readLine() == null) { break; };
        }
    }

    @Override
    public boolean endOfStream() {
        return false;
    }
}
