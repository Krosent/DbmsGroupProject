package com.dbms.project.streamInterfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ReadStreamInterface {
    void open(String path) throws FileNotFoundException;
    String readLn() throws IOException;
    void seek(int pos) throws IOException;
    boolean endOfStream();
}
