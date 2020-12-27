package com.dbms.project.experiment3;

import com.dbms.project.alternative.WriteStreamMethodTwoImpl;

import java.io.IOException;

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
