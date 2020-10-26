package com.dbms.project.streamInterfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface WriteStreamInterface {
    void create(String name) throws IOException;
    void writeLn(String data) throws IOException;
    void close() throws IOException;
}
