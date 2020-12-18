package com.dbms.project.experiment1;

import com.dbms.project.alternative.ReadStreamMethodFourImpl;
import com.dbms.project.streamInterfaces.ReadStreamInterface;

public class LengthReadStreamMethodFour {
    int B;
    ReadStreamInterface readStreamInstance;
    LengthExperiment length1;


    public LengthReadStreamMethodFour(int B) {

        this.B = B;
        this.readStreamInstance = new ReadStreamMethodFourImpl(B);
        length1 = new LengthExperiment(readStreamInstance);

    }

    public int calculateSum(String f) {
        int sum = 0;

            sum = length1.calculateSum(f);

        return sum;
    }
}
