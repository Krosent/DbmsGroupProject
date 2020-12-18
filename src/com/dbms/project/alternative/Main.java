package com.dbms.project.alternative;

import com.dbms.project.experiment1.*;
import com.dbms.project.experiment1.LengthReadStreamMethodOne;
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
        //writeStreamMethodFour();

        Experiment1version4();

    }

    public static void Experiment1version4() {
        String file = "comp_cast_type.csv";

              LengthReadStreamMethodFour length1 = new LengthReadStreamMethodFour(5);
              int sum = length1.calculateSum(file);
              System.out.println("sum" + sum);


    }

    public static void readStreamMethodOne() {
        ReadStreamInterface readStreamInstance = new ReadStreamMethodOneImpl();
        try {
            readStreamInstance.open(".\\src\\com\\dbms\\project\\data\\comp_cast_type.csv");
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
        ReadStreamInterface readStreamInstance = new ReadStreamMethodThreeImpl(5);
        try {
            readStreamInstance.open(".\\src\\com\\dbms\\project\\data\\comp_cast_type.csv");
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
    public static void readStreamMethodFour() {
        ReadStreamInterface readStreamInstance = new ReadStreamMethodFourImpl(5);
        try {
            readStreamInstance.open(".\\src\\com\\dbms\\project\\readFile");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                int i = 2;
                while (!(readStreamInstance.endOfStream())) {
                    readStreamInstance.seek(i);
                    readStreamInstance.readLn();
                    i +=3 ;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void writeStreamMethodFour() {
        WriteStreamInterface writeStreamInstance = new WriteStreamMethodFourImpl(5);
        try {
            writeStreamInstance.create("newFile4");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writeStreamInstance.writeLn("Hello World!");
                writeStreamInstance.writeLn("Hello World2!");
                writeStreamInstance.writeLn("Hello world3!");
                writeStreamInstance.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Write operation succeed");
            }
        }
    }
}
