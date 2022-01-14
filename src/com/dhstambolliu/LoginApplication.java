package com.dhstambolliu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class LoginApplication {
    private String emailAddress;
    private String password;

    public void verifyIsUserIsRegistered() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your email");
        emailAddress = scanner.nextLine();
        System.out.println("Please enter your password");
        password = scanner.nextLine();
        String verifyIfEmailExists = emailAddress;
        String verifyIfPasswordMatches = password;
        String passwordToHash = verifyIfPasswordMatches;
        String generatedVerifiedPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(passwordToHash.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                generatedVerifiedPassword = stringBuilder.toString();
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        boolean flag = false;
        Scanner scanner2 = new Scanner(new FileInputStream("RegisteredUsers.txt"));
        while (scanner2.hasNextLine()) {
            String line = scanner2.nextLine();
            if (line.indexOf(verifyIfEmailExists) != -1 && line.indexOf(generatedVerifiedPassword) != -1) {
                flag = true;
            }
        }
        if (flag) {
            System.out.println("Login successful\n");
        } else {
            System.out.println("Login information is not correct");
            verifyIsUserIsRegistered();
        }
    }
}
