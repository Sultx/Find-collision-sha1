package org.example;


import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class Task implements Runnable {
    private int temp;
    private BufferedWriter fileWriter;
    private BufferedReader fileReader;
    public static final ConcurrentHashMap<String,String> collisionStrings = new ConcurrentHashMap<>();
    private static boolean collisionFound = false;
    public Task(int temp){
        this.temp = temp;
    }

    public Task() throws IOException {
        File file = new File("D:\\zSHA\\" + Constants.FILENAME);
        System.out.println(file.createNewFile());
        this.fileWriter = new BufferedWriter(new FileWriter(file));
        this.fileReader = new BufferedReader(new FileReader(file));
    }

    @Override
    public void run() {
        StringBuilder combination = new StringBuilder();

        for (int j = 0; j < Constants.MAXIMUM_LENGTH; j++) {

            int index = temp % Constants.CHARACTERS_LENGTH;
            combination.append(Constants.CHARACTERS.charAt(index));
            temp /= Constants.CHARACTERS_LENGTH;

            try {
                String hashedText = Sha1.hashPlainText(combination.toString());
                if (hashedText.equals(Sha1.hashedText)) {
                    setCollisionFound(true);
                    collisionStrings.put(combination.toString(), hashedText);
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

    }

    public static boolean isCollisionFound() {
        return collisionFound;
    }

    public static synchronized void setCollisionFound(boolean collisionFound) {
        Task.collisionFound = collisionFound;
    }

    public void writePossibilitiesToFile() throws IOException {

        HashSet<String> stringHashSet =new HashSet<>();
        final int BUFFER_SIZE = 5000000;

        for (int i = 0; i < Math.pow(Constants.CHARACTERS_LENGTH, Constants.MAXIMUM_LENGTH); i++) {
            StringBuilder combination = new StringBuilder();

            int temperory = i;

            for (int j = 0; j < Constants.MAXIMUM_LENGTH; j++) {
                int index = temperory % Constants.CHARACTERS_LENGTH;
                combination.append(Constants.CHARACTERS.charAt(index));
                temperory /= Constants.CHARACTERS_LENGTH;
                stringHashSet.add(combination.toString());
            }

            if ((i % BUFFER_SIZE) == 0 && i != 0){
                String s = String.join("\n", stringHashSet);
                writeToFile(s);
                stringHashSet.clear();
            }
        }


        if (!stringHashSet.isEmpty()) {
            String s = String.join("\n", stringHashSet);
            writeToFile(s);
        }

    }

    public void writeToFile(String s) throws IOException {
        fileWriter.write(s);
    }

    public void readFile()  {


    }

    public void closeFileStreams() throws IOException {
        fileWriter.close();
        fileReader.close();
    }


}
