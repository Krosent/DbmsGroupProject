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
    public Extsort(String f, int k, int M, int d, ReadStreamInterface bestReadVersion, WriteStreamInterface bestWriteVersion){
        this.pathImdbFile = "src/com/dbms/project/data/" + f;
        this.k = k;
        this.M = M;
        this.d = d;
        this.buffer = new ArrayList<>();
        this.outputFiles = new PriorityQueue<String>();
        this.readStream = bestReadVersion;
        this.writeStream = bestWriteVersion;


    }
    void readAndSort()throws IOException {
        int sizeBytes = 0;

        while ( !readStream.endOfStream() & (sizeBytes < M)) {
            String line = readStream.readLn();
            if(!line.isEmpty()) {sizeBytes += line.getBytes().length;
            List<String> record = Arrays.asList(line.split(","));
            buffer.add(record);}

        }
        if(!buffer.isEmpty()){
            if (buffer.get(0).size()>= k) {Collections.sort(buffer,new Comparator<List<String>>() {
                public int compare(List<String> strings, List<String> otherStrings) {
                    return strings.get(k-1).compareTo(otherStrings.get(k-1));
                }
            });}
            String nameOutputFile = "sortedFile" + (outputFiles.size() +1);
            outputFiles.add(nameOutputFile);
            writeStream.create(nameOutputFile);

            for (List<String> record : buffer) {
                String stringRecord = String.join(",", record);
                writeStream.writeLn(stringRecord);
            }
            writeStream.close();
        }
        buffer = new ArrayList<>();
    }

    void merge(ArrayList<ReadStreamInterface> inputStreams, int i)throws IOException {
        String nameOutputFile = "mergeSortedFile" + i;
        writeStream.create(nameOutputFile);
        for (ReadStreamInterface stream : inputStreams) {
            if( !stream.endOfStream()){
                String line = stream.readLn();
                if(!line.isEmpty()) {
                    List<String> record = Arrays.asList(line.split(","));
                    buffer.add(record);}}}
            //find smallest in buffer and write it to output

            while(!buffer.isEmpty()){
                if (buffer.get(0).size()>= k) {
                    List<String> record = Collections.min(buffer,new Comparator<List<String>>() {
                        public int compare(List<String> strings, List<String> otherStrings) {
                            return strings.get(k-1).compareTo(otherStrings.get(k-1));
                        }
                    });
                    String stringRecord = String.join(",", record);
                    writeStream.writeLn(stringRecord);
                    int idx = buffer.indexOf(record);
                    buffer.remove(idx);
                    if (!inputStreams.get(idx).endOfStream()) {
                        String line = inputStreams.get(idx).readLn();
                        if(!line.isEmpty()) {
                            List<String> newRecord = Arrays.asList(line.split(","));

                            buffer.add(idx,newRecord);}
                    }else {inputStreams.remove(idx);}
                    }
                }

        outputFiles.add(nameOutputFile);
        writeStream.close();
        buffer = new ArrayList<>();
        }


    public void doSort()throws IOException {
        readStream.open(pathImdbFile);
        while (!readStream.endOfStream())
        {readAndSort();}
        int mer = 1;
        while(!(outputFiles.size() == 1 )) {
            ArrayList<ReadStreamInterface> inputStreams = new ArrayList<>();
        int i = 0;
        while ((i<d) & (!outputFiles.isEmpty())){
            ReadStreamInterface readStreamInstance = new ReadStreamMethodOneImpl();
            String file = outputFiles.poll();
            readStreamInstance.open(file);
            inputStreams.add(readStreamInstance);
            i++;
        }
        merge(inputStreams, mer);
        mer++;}

    }
}
