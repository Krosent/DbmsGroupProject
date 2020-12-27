package com.dbms.project.alternative;

import com.dbms.project.MultiWayMerge.Extsort;
import com.dbms.project.experiment1.*;
import com.dbms.project.experiment2.*;
import com.dbms.project.experiment3.*;
import com.dbms.project.streamInterfaces.ReadStreamInterface;
import com.dbms.project.streamInterfaces.WriteStreamInterface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        class Sortbyk implements Comparator<List<String>> {
            // Used for sorting in ascending order of
            // roll number
            public int compare(List<String> a, List<String> b) {
                return a.get(2).compareTo(b.get(2));
            }
        }
/*
        System.out.println("--- READ Function METHOD ONE ---");
        // Execute write function of first method
        readStreamMethodOne();
        System.out.println("--- END ---");

        System.out.println("--- Write Function METHOD ONE ---");
        // Execute write function of first method
        writeStreamMethodOne();
        System.out.println("--- END ---");
*/

/*
        System.out.println("--- READ Function METHOD TWO ---");
        // Execute write function of second method
        readStreamMethodTwo();
        System.out.println("--- END ---");

        System.out.println("--- Write Function METHOD TWO ---");
        // Execute write function of second method
        writeStreamMethodTwo();
        System.out.println("--- END ---");
*/

/*
        System.out.println("--- READ Function METHOD THREE ---");
        // Execute read function of the third method
        readStreamMethodThree();
        System.out.println("--- END ---");

        System.out.println("--- WRITE Function METHOD THREE ---");
        // Execute read function of the third method
        writeStreamMethodThree();
        System.out.println("--- END ---");
 */
/*
        System.out.println("--- READ Function METHOD FOURTH ---");
        // Execute read function of the fourth method
        readStreamMethodFour();
        System.out.println("--- END ---");

        System.out.println("--- WRITE Function METHOD FOURTH ---");
        // Execute write function of fourth method
        writeStreamMethodFour();
        System.out.println("--- END ---");
*/

/*
        System.out.println("--- START Sequential Reading Experiment | One ---");
        sequentialReadExperimentOne();
        System.out.println("--- END OPERATION ---");

        System.out.println("--- START Sequential Reading Experiment | Two ---");
        sequentialReadExperimentTwo();
        System.out.println("--- END OPERATION ---");

        System.out.println("--- START Sequential Reading Experiment | Three ---");
        sequentialReadExperimentThree();
        System.out.println("--- END OPERATION ---");

        System.out.println("--- START Sequential Reading Experiment | Four ---");
        sequentialReadExperimentFour();
        System.out.println("--- END OPERATION ---");
*/


        System.out.println("--- START Random Reading Experiment | ONE ---");
        randomReadExperimentOne();
        System.out.println("--- END OPERATION ---");

        System.out.println("--- START Random Reading Experiment | TWO ---");
        randomReadExperimentTwo();
        System.out.println("--- END OPERATION ---");

        System.out.println("--- START Random Reading Experiment | THREE ---");
        randomReadExperimentThree();
        System.out.println("--- END OPERATION ---");

        System.out.println("--- START Random Reading Experiment | Four ---");
        randomReadExperimentFour();
        System.out.println("--- END OPERATION ---");

/*
        System.out.println("--- START Reading and Writing Experiment | One ---");
        readAndWriteExperimentOne();
        System.out.println("--- END OPERATION ---");

        System.out.println("--- START Reading and Writing Experiment | Two ---");
        readAndWriteExperimentTwo();
        System.out.println("--- END OPERATION ---");

        System.out.println("--- START Reading and Writing Experiment | Three ---");
        readAndWriteExperimentThree();
        System.out.println("--- END OPERATION ---");

        System.out.println("--- START Reading and Writing Experiment | Four ---");
        readAndWriteExperimentFour();
        System.out.println("--- END OPERATION ---");
*/

/*
        System.out.println("--- START Testing Multi Way Merge SORT");
        TestExtsort();
        System.out.println("--- END OPERATION ---");
 */
    }



    public static void readStreamMethodOne() {
        ReadStreamInterface readStreamInstance = new ReadStreamMethodOneImpl();
        try {
            readStreamInstance.open("src/com/dbms/project/data/comp_cast_type.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                readStreamInstance.seek(2);
                readStreamInstance.seek(2);
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
            writeStreamInstance.create("sortedFile");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writeStreamInstance.writeLn("1, movie");
                writeStreamInstance.writeLn("5, tv mini series");
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
            readStreamInstance.open("src/com/dbms/project/alternative/readFile");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                readStreamInstance.seek(2);
                readStreamInstance.seek(2);
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
            readStreamInstance.open("src/com/dbms/project/alternative/readFile");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                readStreamInstance.seek(2);
                readStreamInstance.seek(2);
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
        ReadStreamInterface readStreamInstance = new ReadStreamMethodFourImpl(8);
        try {
            readStreamInstance.open("src/com/dbms/project/alternative/readFile");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {

                readStreamInstance.seek(2);
                readStreamInstance.seek(2);
                while (!(readStreamInstance.endOfStream())) {

                    readStreamInstance.readLn();

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
    public static void sequentialReadExperimentOne() throws IOException {
        LengthReadStreamInterface sequentialReadExperimentOne = new LengthReadStreamExperimentOne();
        sequentialReadExperimentOne.executeExperiment("kind_type.csv");
    }

    public static void sequentialReadExperimentTwo() throws IOException {
        LengthReadStreamInterface sequentialReadExperimentOne = new LengthReadStreamExperimentTwo();
        sequentialReadExperimentOne.executeExperiment("kind_type.csv");
    }
    public static void sequentialReadExperimentThree() throws IOException {
        LengthReadStreamInterface sequentialReadExperimentOne = new LengthReadStreamExperimentThree(10);
        sequentialReadExperimentOne.executeExperiment("kind_type.csv");
    }
    public static void sequentialReadExperimentFour() throws IOException {
        LengthReadStreamInterface sequentialReadExperimentOne = new LengthReadStreamExperimentFour(15);
        sequentialReadExperimentOne.executeExperiment("kind_type.csv");
    }

    public static void randomReadExperimentOne() throws IOException {
        RandomReadingExperimentOne randomReadingExperimentOne = new RandomReadingExperimentOne();
        randomReadingExperimentOne.executeExperiment("kind_type.csv",4);
    }

    public static void randomReadExperimentTwo() throws IOException {
        RandomReadingExperimentTwo randomReadingExperimentTwo = new RandomReadingExperimentTwo();
        randomReadingExperimentTwo.executeExperiment("kind_type.csv",10);
    }
    public static void randomReadExperimentThree() throws IOException {
        RandomReadingExperimentThree randomReadingExperimentThree = new RandomReadingExperimentThree(10);
        randomReadingExperimentThree.executeExperiment("kind_type.csv", 5);
    }
    public static void randomReadExperimentFour() throws IOException {
        RandomReadingExperimentFour randomReadingExperimentFour = new RandomReadingExperimentFour(5);
        randomReadingExperimentFour.executeExperiment("kind_type.csv", 5);
    }
    public static void readAndWriteExperimentOne() throws IOException {
        ReadingAndWritingExperimentOne readAndWriteExperiment = new ReadingAndWritingExperimentOne("mergedExp3vers1");
        readAndWriteExperiment.executeExperiment("kind_type.csv","comp_cast_type.csv");
    }
    public static void readAndWriteExperimentTwo() throws IOException {
        ReadingAndWritingExperimentTwo readAndWriteExperiment = new ReadingAndWritingExperimentTwo("mergedExp3vers2");
        readAndWriteExperiment.executeExperiment("comp_cast_type.csv","kind_type.csv");
    }
    public static void readAndWriteExperimentThree() throws IOException {
        ReadingAndWritingExperimentThree readAndWriteExperiment = new ReadingAndWritingExperimentThree(5,"mergedExp3vers3");
        readAndWriteExperiment.executeExperiment("comp_cast_type.csv","kind_type.csv","comp_cast_type.csv");
    }
    public static void readAndWriteExperimentFour() throws IOException {
        ReadingAndWritingExperimentFour readAndWriteExperiment = new ReadingAndWritingExperimentFour(20,"mergedExp3vers4");
        readAndWriteExperiment.executeExperiment("kind_type.csv","kind_type.csv");
    }


    public static void TestExtsort() throws IOException {
        String file = "kind_type.csv";

        ReadStreamInterface readStreamInstance = new ReadStreamMethodOneImpl();
        WriteStreamInterface writeStreamInstance = new WriteStreamMethodOneImpl();
        Extsort srt = new Extsort(file, 2, 20, 2, readStreamInstance, writeStreamInstance);
        srt.doSort();
    }
}
