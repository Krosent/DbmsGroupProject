package com.dbms.project.experiment1;

import com.dbms.project.alternative.ReadStreamMethodThreeImpl;
import com.dbms.project.streamInterfaces.ReadStreamInterface;

public class LengthReadStreamMethodThree {
    int B;
    ReadStreamInterface readStreamInstance;
    LengthExperiment length1;


    public LengthReadStreamMethodThree(int B) {

        this.B = B;
        this.readStreamInstance = new ReadStreamMethodThreeImpl(B);
        length1 = new LengthExperiment(readStreamInstance);

    }

    public int calculateSum(String f) {
        int sum = 0;

            sum = length1.calculateSum(f);

        return sum;
    }
}