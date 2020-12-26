package com.dbms.project.experiment3;

import com.dbms.project.alternative.ReadStreamMethodOneImpl;
import com.dbms.project.streamInterfaces.ReadStreamInterface;
import com.dbms.project.streamInterfaces.WriteStreamInterface;

import java.io.IOException;
import java.util.ArrayList;

public class ReadingAndWritingExperiment {
    WriteStreamInterface writeStream;
    String pathImdb = "src/com/dbms/project/data/";
    String nameOutputFile;

    public ReadingAndWritingExperiment(WriteStreamInterface writeStream, String outputFile) {
        this.writeStream = writeStream;
        this.nameOutputFile = outputFile;
    }

    // f is the name of one of the csv files in the IMDB dataset, and j is a positive integer.
    public void rrmerge(String... files) throws IOException {
        ArrayList<ReadStreamInterface> inputStreams = new ArrayList<>();
        try {
            writeStream.create(nameOutputFile);
        } catch (IOException e) {
            e.printStackTrace();}
        for (String file: files) {
            //the best reading stream implementation from experiment 1
            ReadStreamInterface stream = new ReadStreamMethodOneImpl();
            String pathName = pathImdb + file;
            stream.open(pathName);
            inputStreams.add(stream);
        }
        while (! inputStreams.isEmpty()) {
            //we remove the streams that are completely read, so we have less tests
            ArrayList<ReadStreamInterface> delete = new ArrayList<>();
            for (ReadStreamInterface stream: inputStreams) {
                if (!stream.endOfStream()) {
                    String line = stream.readLn();
                    if(!line.isEmpty()) {writeStream.writeLn(line);}
                    //cannot immediately remove the stream here because of a ConcurrentModificationException
                }else delete.add(stream); }
            for (ReadStreamInterface processedStream : delete) {
                inputStreams.remove(processedStream);
            }
        }
        try{
            writeStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
