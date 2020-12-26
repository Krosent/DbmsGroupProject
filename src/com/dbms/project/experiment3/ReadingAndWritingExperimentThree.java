package com.dbms.project.experiment3;

import com.dbms.project.alternative.WriteStreamMethodThreeImpl;

import java.io.IOException;

public class ReadingAndWritingExperimentThree {
    int B;
    String nameOutputFile;
    public ReadingAndWritingExperimentThree(int B,String outputFile) {
        this.B = B;
        this.nameOutputFile = outputFile;}

    public void executeExperiment(String... fileNames) throws IOException {
        ReadingAndWritingExperiment experiment =
                new ReadingAndWritingExperiment(new WriteStreamMethodThreeImpl(B),nameOutputFile);
        experiment.rrmerge(fileNames);
    }
}
