package org.example;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.*;

public class Main {

    static long beginTimeInMillis;
    public static void main(String[] args) throws NoSuchAlgorithmException {
        new Sha1();

        findCollisionInMemory();

    }

    private static void writePossibilitiesToDisk() throws IOException {

        Task task = new Task();
        task.writePossibilitiesToFile();
        task.closeFileStreams();

    }

    private static void findCollisionInMemory(){

        CheckStringFound.start();

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        beginTimeInMillis = System.currentTimeMillis();

        for (int i = 0; i < Math.pow(Constants.CHARACTERS_LENGTH, Constants.MAXIMUM_LENGTH); i++) {

            executor.submit(new Task(i));

        }

        executor.close();
    }

}



