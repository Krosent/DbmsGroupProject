package com.dbms.project.experiment3;

import com.dbms.project.alternative.WriteStreamMethodTwoImpl;

import java.io.IOException;

//with this class the reading and writing experiment can be done on a couple of files using the second implementation of the write stream and
// outputting all the lines of all the files in an output file, the name of which is given as a parameter
public class ReadingAndWritingExperimentTwo implements ReadAndWriteStreamInterface{
    String nameOutputFile;
    public ReadingAndWritingExperimentTwo(String outputFile) {
        this.nameOutputFile = outputFile;}
    public void executeExperiment(String... fileNames) throws IOException {
        ReadingAndWritingExperiment experiment =
                new ReadingAndWritingExperiment(new WriteStreamMethodTwoImpl(),nameOutputFile);
        experiment.rrmerge(fileNames);
    }
}
