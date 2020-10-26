package com.dbms.project;

import com.dbms.project.streamInterfaces.ReadStreamInterface;
import com.dbms.project.streamInterfaces.WriteStreamInterface;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	    // write your code here

        // Execute write function of first method
        //writeStreamMethodOne();

        // Execute read function of the second method
        readStreamMethodTwo();

        // Execute write function of second method
        writeStreamMethodTwo();

    }

    public static void readStreamMethodOne() {
        ReadStreamInterface readStreamInstance = new ReadStreamMethodOneImpl();
        try {
            readStreamInstance.open("/Users/artyomkuznetsov/Desktop/untitled.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // readStreamInstance.seek(3); // to check how seek works
                readStreamInstance.readLn();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeStreamMethodOne() {
        WriteStreamInterface writeStreamInstance = new WriteStreamMethodOneImpl();
        try {
            writeStreamInstance.create("newFile");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writeStreamInstance.writeLn("Hello World!");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Write operation succeed");
            }
        }
    }

    public static void readStreamMethodTwo() {
        ReadStreamInterface readStreamInstance = new ReadStreamMethodTwoImpl();
        try {
            readStreamInstance.open("/Users/artyomkuznetsov/Desktop/untitled.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // readStreamInstance.seek(3); // to check how seek works
                readStreamInstance.readLn();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeStreamMethodTwo() {
        WriteStreamInterface writeStreamInstance = new WriteStreamMethodTwoImpl();
        try {
            writeStreamInstance.create("newFile2");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writeStreamInstance.writeLn("Hello World!");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Write operation succeed");
            }
        }
    }
}
