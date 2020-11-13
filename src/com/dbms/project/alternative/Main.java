package com.dbms.project.alternative;

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
        readStreamMethodThree();

        // Execute write function of second method
        writeStreamMethodThree();

    }

    public static void readStreamMethodOne() {
        ReadStreamInterface readStreamInstance = new ReadStreamMethodOneImpl();
        try {
            readStreamInstance.open(".\\src\\com\\dbms\\project\\readFile");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                while (!(readStreamInstance.endOfStream())) {
                    readStreamInstance.readLn();
                }
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
                writeStreamInstance.writeLn("Hello World2!");
                writeStreamInstance.close();
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
            readStreamInstance.open(".\\src\\com\\dbms\\project\\readFile");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                while (!(readStreamInstance.endOfStream())) {
                    readStreamInstance.readLn();
                }
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
                writeStreamInstance.writeLn("Hello World2!");
                writeStreamInstance.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Write operation succeed");
            }
        }
    }
    public static void readStreamMethodThree() {
        ReadStreamInterface readStreamInstance = new ReadStreamMethodThreeImpl(10);
        try {
            readStreamInstance.open(".\\src\\com\\dbms\\project\\readFile");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                while (!(readStreamInstance.endOfStream())) {
                    readStreamInstance.readLn();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void writeStreamMethodThree() {
        WriteStreamInterface writeStreamInstance = new WriteStreamMethodThreeImpl(5);
        try {
            writeStreamInstance.create("newFile3");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writeStreamInstance.writeLn("Hello World!");
                writeStreamInstance.writeLn("Hello World2!");
                writeStreamInstance.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Write operation succeed");
            }
        }
    }
}
