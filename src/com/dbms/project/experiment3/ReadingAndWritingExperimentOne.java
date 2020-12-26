package com.dbms.project.experiment3;

import com.dbms.project.alternative.WriteStreamMethodOneImpl;

import java.io.IOException;

public class ReadingAndWritingExperimentOne {
    String nameOutputFile;
    public ReadingAndWritingExperimentOne(String outputFile) {
        this.nameOutputFile = outputFile;}
    public void executeExperiment(String... fileNames) throws IOException {
        ReadingAndWritingExperiment experiment =
                new ReadingAndWritingExperiment(new WriteStreamMethodOneImpl(),nameOutputFile);
        experiment.rrmerge(fileNames);
    }


}
