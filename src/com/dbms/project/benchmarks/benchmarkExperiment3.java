package com.dbms.project.benchmarks;

import com.dbms.project.experiment1.*;
import com.dbms.project.experiment3.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class benchmarkExperiment3 {
    //results are stored to keep the compiler from optimising the code obtaining them away
    public static List<Integer> use_results = new ArrayList<Integer>(10000);

    //Identifiers for different experimental setups
    public enum Strategy{
        VERSION1_LARGE,
        VERSION2_LARGE,
        VERSION3_LARGE_DEF,
        VERSION3_LARGE_12k,
        VERSION3_LARGE_16k,
        VERSION4_LARGE_DEF,
        VERSION4_LARGE_12k,
        VERSION4_LARGE_16k,
        VERSION1_MEDIUM,
        VERSION2_MEDIUM,
        VERSION3_MEDIUM_DEF,
        VERSION3_MEDIUM_12k,
        VERSION3_MEDIUM_16k,
        VERSION4_MEDIUM_DEF,
        VERSION4_MEDIUM_12k,
        VERSION4_MEDIUM_16k,
        VERSION1_SMALL,
        VERSION2_SMALL,
        VERSION3_SMALL_DEF,
        VERSION3_SMALL_12k,
        VERSION3_SMALL_16k,
        VERSION4_SMALL_DEF,
        VERSION4_SMALL_12k,
        VERSION4_SMALL_16k,
        VERSION1_TINY,
        VERSION2_TINY,
        VERSION3_TINY_DEF,
        VERSION3_TINY_12k,
        VERSION3_TINY_16k,
        VERSION4_TINY_DEF,
        VERSION4_TINY_12k,
        VERSION4_TINY_16k,
    }

        //Actual instantiations of the different strategies compared
    static final List<Function<Integer,Integer>> strategies =
                Arrays.asList(
                        version1("movie_info.csv"),
                        version2("movie_info.csv"),
                        version3("movie_info.csv",8000),
                        version3("movie_info.csv",12000),
                        version3("movie_info.csv",16000),
                        version4("movie_info.csv", 8000),
                        version4("movie_info.csv", 12000),
                        version4("movie_info.csv", 16000),

                        version1("person_info.csv"),
                        version2("person_info.csv"),
                        version3("person_info.csv",8000),
                        version3("person_info.csv",12000),
                        version3("person_info.csv",16000),
                        version4("person_info.csv", 8000),
                        version4("person_info.csv", 12000),
                        version4("person_info.csv", 16000),

                        version1("movie_keyword.csv"),
                        version2("movie_keyword.csv"),
                        version3("movie_keyword.csv",8000),
                        version3("movie_keyword.csv",12000),
                        version3("movie_keyword.csv",16000),
                        version4("movie_keyword.csv", 8000),
                        version4("movie_keyword.csv", 12000),
                        version4("movie_keyword.csv", 16000),

                        version1("complete_cast.csv"),
                        version2("complete_cast.csv"),
                        version3("complete_cast.csv",8000),
                        version3("complete_cast.csv",12000),
                        version3("complete_cast.csv",16000),
                        version4("complete_cast.csv", 8000),
                        version4("complete_cast.csv", 12000),
                        version4("complete_cast.csv", 16000)
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

            File res_file = new File("runtimes3.csv");
            System.out.println("Measuring runtimes for files ");
            for(int j = 0; j < strategies.size(); j++){
                System.out.print(Strategy.values()[j]+": ");
                System.out.flush();
                //measure runtimes
                List<Long> rts = benchmark(0,strategies.get(j),n_repetitions);
                //write to file
                write2file(res_file, Strategy.values()[j],rts);
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
         * @return The experiment for version 1
         */

        static Function<Integer,Integer> version1(String file){
            return (Integer i) -> {
                ReadAndWriteStreamInterface readAndWriteStreamImplementationOne = new ReadingAndWritingExperimentOne(file);
                return 0;
            };
        }

    /**
     * @return The experiment for version 2
     */
    static Function<Integer,Integer> version2(String file){
        return (Integer i) -> {
            ReadAndWriteStreamInterface readAndWriteStreamImplementationTwo = new ReadingAndWritingExperimentTwo(file);
            return 0;
        };
    }


    /**
     * @return The experiment for version 3
     */
    static Function<Integer,Integer> version3(String file, int B){
        return (Integer i) -> {
            ReadAndWriteStreamInterface readAndWriteStreamImplementationThree = new ReadingAndWritingExperimentThree(B, file);
            return 0;
        };
    }

    /**
     * @return The experiment for version 4
     */
    static Function<Integer,Integer> version4(String file, int B){
        return (Integer i) -> {
            ReadAndWriteStreamInterface readAndWriteStreamImplementationFour = new ReadingAndWritingExperimentFour(B, file);
            return 0;
        };
    }
}
