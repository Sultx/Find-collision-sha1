package org.example;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1 {
    public static String hashedText;

    public Sha1() throws NoSuchAlgorithmException{
        hashedText = hashPlainText(Constants.PLAINTEXT);
        System.out.println("\n16 byte hash value have been generated successfully " + hashedText + "\n");
    }


    public static String hashPlainText(String plaintext) throws NoSuchAlgorithmException {

        // Create an instance of the SHA-1 algorithm
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");

        // Convert plaintext to bytes
        // Hash the bytes using SHA-1 algorithm
        byte[] hashedBytes = sha1.digest(plaintext.getBytes());

        // Convert the hashed bytes to a hexadecimal string
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            stringBuilder.append(String.format("%02X", hashedBytes[i]));
        }

        return stringBuilder.toString();
    }


}



