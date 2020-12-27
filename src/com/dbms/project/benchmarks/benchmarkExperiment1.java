package com.dbms.project.benchmarks;

import com.dbms.project.experiment1.LengthReadStreamExperimentOne;
import com.dbms.project.experiment1.LengthReadStreamExperimentThree;
import com.dbms.project.experiment1.LengthReadStreamInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
public class benchmarkExperiment1 {
    //results are stored to keep the compiler from optimising the code obtaining them away
    public static List<Integer> use_results = new ArrayList<Integer>(10000);

    //Identifiers for different experimental setups
    public enum Strategy{
        VERSION1FILE7LINES,
        VERSION3B5FILE4LINES
    }

        //Actual instantiations of the different strategies compared
    static final List<Function<Integer,Integer>> strategies =
                Arrays.asList(
                        version1("kind_type.csv"),
                        version3("comp_cast_type.csv",5)
                );

        /*
         * main method, to be used to perform run time measurements
         *
         * command-line args:
         * args[0]: # repetitions to perform
         */
        public static void main(String[] args) {

            int n_repetitions = 20;
            System.out.println("Repeating each measurement "+n_repetitions+" times.");


            File res_file = new File("runtimes1.csv");
            System.out.println("Measuring runtimes for files ");
            for(int j = 0; j < strategies.size(); j++){
                System.out.print(Strategy.values()[j]+": ");
                System.out.flush();
                //measure runtimes
                List<Long> rts = benchmark(0,strategies.get(j),n_repetitions);
                //write to file
                write2file(res_file,Strategy.values()[j],rts);
                System.out.println("v");
            }
        }


        //writes runtimes for a given strategy to file
        static private void write2file(File f, Strategy s, List<Long> runtimes){
            PrintWriter csv_writer;
            try {
                csv_writer = new PrintWriter(new FileOutputStream(f,true));
                String line = ""+s;
                for(Long rt : runtimes){
                    line += ","+rt;
                }
                csv_writer.println(line);
                csv_writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        /**
         * Measures the runtime(s) (in nanoseconds) for a given strategy on a given data preset for a given number of repetitions.
         *
         * @param strategy: The filtering strategy, i.e. a function mapping to length
         * @param nrep: The number of times the experiment should be repeated consecutively
         * @return A list of nrep runtimes (in nanoseconds), where the i'th element is the runtime of the i'th repetition
         */
        static public List<Long> benchmark(int B,Function<Integer,Integer> strategy, int nrep){
            List<Long> runtimes = new ArrayList<Long>(nrep);
            for(int i = 0; i < nrep; i++){
                System.gc(); //a heuristic to avoid Garbage Collection (GC) to take place in timed portion of the code
                long before = System.nanoTime(); //time is measured in ns
                use_results.add(strategy.apply(B)); //store results to avoid code getting optimised away
                runtimes.add(System.nanoTime()-before);
            }
            return runtimes;
        }

        /**
         * @return The experiment for version1
         */

        static Function<Integer,Integer> version1(String file){
            return (Integer i) -> {
                int length = 0;
                try{LengthReadStreamInterface sequentialReadExperimentOne = new LengthReadStreamExperimentOne();
                length = sequentialReadExperimentOne.executeExperiment(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
                return length;
            };
        }
    /**
     * @return The experiment for version3
     */
    static Function<Integer,Integer> version3(String file, int B){
        return (Integer i) -> {
            int length = 0;
            try{LengthReadStreamInterface sequentialReadExperimentOne = new LengthReadStreamExperimentThree(B);
                length = sequentialReadExperimentOne.executeExperiment("kind_type.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return length;
        };
    }





}
