package com.dbms.project.streamInterfaces;

public interface WriteStreamInterface {
    void create(String name);
    void writeLn(String data);
    void close();
}
