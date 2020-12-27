package com.dbms.project.experiment3;

import com.dbms.project.alternative.WriteStreamMethodFourImpl;

import java.io.IOException;

public class ReadingAndWritingExperimentFour implements ReadAndWriteStreamInterface{
    int B;
    String nameOutputFile;
    public ReadingAndWritingExperimentFour(int B,String outputFile) {
        this.B = B;
        this.nameOutputFile = outputFile;}

    public void executeExperiment(String... fileNames) throws IOException {
        ReadingAndWritingExperiment experiment =
                new ReadingAndWritingExperiment(new WriteStreamMethodFourImpl(B),nameOutputFile);
        experiment.rrmerge(fileNames);
    }
}
