package com.dbms.project.experiment3;

import com.dbms.project.alternative.ReadStreamMethodTwoImpl;
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

    //rrmerge creates one new file that merges the contents of files from the IMDB dataset by copying one line of
    //each file in a round robin fashion, until all files have been processed.

    // files consists of a couple of csv files in the IMDB dataset
    public void rrmerge(String... files) throws IOException {
        ArrayList<ReadStreamInterface> inputStreams = new ArrayList<>();
        //creating the output files in which all the lines of all the files will be written
        writeStream.create(nameOutputFile);

        //for all the files, a read stream is created
        for (String file: files) {
            //the best reading stream implementation from experiment 1
            ReadStreamInterface stream = new ReadStreamMethodTwoImpl();
            String pathName = pathImdb + file;
            stream.open(pathName);
            inputStreams.add(stream);
        }
        //looping over all the read streams reading the next line of each file in turn until all the files are processed
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
        writeStream.close();
    }
}
