package com.dbms.project.MultiWayMerge;


import com.dbms.project.alternative.ReadStreamMethodOneImpl;
import com.dbms.project.streamInterfaces.ReadStreamInterface;
import com.dbms.project.streamInterfaces.WriteStreamInterface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Extsort {
    int k;
    int M;
    int d;
    ReadStreamInterface readStream;
    WriteStreamInterface writeStream;
    ArrayList<List<String>> buffer;
    PriorityQueue<String> outputFiles;

    String pathImdbFile;

    //The program extsort takes a file to be sorted, the column on which the file needs to be sorted, the number of bytes free to use for it in memory, and the number of streams that need to be merged in the process at one time
    //it also takes the best read and write stream implementation concluded from past experiments
    //a call to the doSort() method creates a new file that contains the same contents as f, but now sorted on the k-th column of f.
    public Extsort(String f, int k, int M, int d, ReadStreamInterface bestReadVersion, WriteStreamInterface bestWriteVersion) {
        this.pathImdbFile = "src/com/dbms/project/data/" + f;
        this.k = k;
        this.M = M;
        this.d = d;
        this.buffer = new ArrayList<>();
        this.outputFiles = new PriorityQueue<String>();
        this.readStream = bestReadVersion;
        this.writeStream = bestWriteVersion;


    }

    //the input file is read and N/M (N size of the file) output files are created each containing +/- M/S (S abg length of a line) sorted lines
    void readAndSort() throws IOException {
        int sizeBytes = 0;
        //reading the file line by line until the size in bytes exceeds M, storing them as a record in an in-memory buffer
        while (!readStream.endOfStream() & (sizeBytes < M)) {
            String line = readStream.readLn();
            if (!line.isEmpty()) {
                sizeBytes += line.getBytes().length;
                //each line corresponds to a record
                List<String> record = Arrays.asList(line.split(","));
                buffer.add(record);
            }

        }
        // Sort the buffered records on the k-th column using an internal memory sorting algorithm.
        if (!buffer.isEmpty()) {
            //k is the column needed to be sorted on so it can be at most the number of columns in the file
            if (buffer.get(0).size() >= k) {
                Collections.sort(buffer, new Comparator<List<String>>() {
                    public int compare(List<String> strings, List<String> otherStrings) {
                        return strings.get(k - 1).compareTo(otherStrings.get(k - 1));
                    }
                });
            } //else {new Error("k exceeds the number of columns");}
            //the sorted records are written to an output file, the name of which depends on the number of these kind of output files already present
            String nameOutputFile = "sortedFile" + (outputFiles.size() + 1);
            //the reference to this new output file is added to a queue
            outputFiles.add(nameOutputFile);
            writeStream.create(nameOutputFile);

            for (List<String> record : buffer) {
                //the records are again transformed in strings
                String stringRecord = String.join(",", record);
                writeStream.writeLn(stringRecord);
            }
            writeStream.close();
        }
        //the internal memory can again be used because the sorted records are written to an external file
        buffer = new ArrayList<>();
    }

    //after the mapping to d input streams, they are merged, i is given to know the name of the output file with the merged streams
    void merge(ArrayList<ReadStreamInterface> inputStreams, int i) throws IOException {
        String nameOutputFile = "mergeSortedFile" + i;
        writeStream.create(nameOutputFile);
        //for every (already sorted) inputstream, the first record is read and added to the buffer
        for (ReadStreamInterface stream : inputStreams) {
            if (!stream.endOfStream()) {
                String line = stream.readLn();
                if (!line.isEmpty()) {
                    List<String> record = Arrays.asList(line.split(","));
                    buffer.add(record);
                }
            }
        }
        //every time find smallest record in buffer and write it to output, add the next record from the corresponding file and
        //test again for the smallest record, do this until all the records of all the streams have been read
        while (!buffer.isEmpty()) {
            if (buffer.get(0).size() >= k) {
                //1) find smallest record
                List<String> record = Collections.min(buffer, new Comparator<List<String>>() {
                    public int compare(List<String> strings, List<String> otherStrings) {
                        return strings.get(k - 1).compareTo(otherStrings.get(k - 1));
                    }
                });
                String stringRecord = String.join(",", record);
                //2) write it to the output file
                writeStream.writeLn(stringRecord);
                int idx = buffer.indexOf(record);
                //3) remove this record from the buffer
                buffer.remove(idx);
                //4) for the corresponding stream, read the next record and add it to the buffer
                if (!inputStreams.get(idx).endOfStream()) {
                    String line = inputStreams.get(idx).readLn();
                    if (!line.isEmpty()) {
                        List<String> newRecord = Arrays.asList(line.split(","));
                        buffer.add(idx, newRecord);
                    }
                    //4.1 if the stream is closed, and no more records are left in this file, the stream is
                    //removed from the streams that still need to be processed
                    //no new record is added to the buffer then so the indexes stay correct
                } else {
                    inputStreams.remove(idx);
                }
            }
        }
        //add the output file with the merged streams at the end of the queue
        outputFiles.add(nameOutputFile);
        writeStream.close();
        buffer = new ArrayList<>();
    }

    //creates a new file that contains the same contents as f, but now sorted on the k-th column of f
    public void doSort() throws IOException {
        //1) the input file is read and N/M (N size of the file) output files are created each containing M/S (S abg length of a line) sorted lines
        readStream.open(pathImdbFile);
        //creating output files until the whole file is read, the references to these files are stored in a priority queue
        while (!readStream.endOfStream()) {
            readAndSort();
        }

        //2)until only one stream remains, the first d streams are merged, and added to the end of the queue
        //used to name the new output file after a merge is done
        int mer = 1;
        //until only one stream remains (one output file from previous steps)
        while (!(outputFiles.size() == 1)) {
            ArrayList<ReadStreamInterface> inputStreams = new ArrayList<>();
            int i = 0;
            while ((i < d) & (!outputFiles.isEmpty())) {
                //the first d input files (which are the sorted output files from step 1 and/or the results of a merge) are mapped to a read stream
                ReadStreamInterface readStreamInstance = new ReadStreamMethodOneImpl();
                String file = outputFiles.poll();
                readStreamInstance.open(file);
                inputStreams.add(readStreamInstance);
                i++;
            }
            //after the mapping to d input streams, they are merged
            merge(inputStreams, mer);
            mer++;
        }

    }
}
