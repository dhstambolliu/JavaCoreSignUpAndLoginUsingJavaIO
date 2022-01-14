package com.dhstambolliu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterApplication {
    private String name;
    private String surname;
    private String emailAddress;
    private String birthdate;
    private String password;
    private String confirmPassword;

    Scanner scanner = new Scanner(System.in);

    public String getName() {
        System.out.println("Insert your name in text format");
        name = scanner.nextLine();
        String regex = "^[A-Za-z]\\w{2,29}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        if (name.isEmpty()) {
            System.out.println("Name must not be empty!");
            return getName();
        } else if (name.length() < 3) {
            System.out.println("Enter a name longer than 2 character long");
            return getName();
        } else if (matcher.matches() == false) {
            System.out.println("Name is not valid. Please enter your name in required format");
            return getName();
        }
        return name;
    }

    public String getSurname() {
        System.out.println("Insert your surname in text format");
        surname = scanner.nextLine();
        String regex = "^[A-Za-z]\\w{2,29}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(surname);
        if (surname.isEmpty()) {
            System.out.println("Surname must not be empty.");
            return getSurname();
        } else if (surname.length() < 3) {
            System.out.println("Enter a surname longer than 2 character long");
            return getSurname();
        } else if (matcher.matches() == false) {
            System.out.println("Surname is not valid. Please enter your Surname in required format");
            return getSurname();
        }
        return surname;
    }

    public String getEmailAddress() throws FileNotFoundException {
        System.out.println("Insert your email address in email format");
        emailAddress = scanner.nextLine();
        String verifyIfEmailExists = emailAddress;
        boolean flag = false;
        Scanner scanner2 = new Scanner(new FileInputStream("RegisteredUsers.txt"));
        while (scanner2.hasNextLine()) {
            String line = scanner2.nextLine();
            if (line.indexOf(verifyIfEmailExists) != -1) {
                flag = true;
            }
        }
        if (flag) {
            System.out.println("This email is already registered, please enter new email address");
            return getEmailAddress();
        }
        String regex = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailAddress);
        if (emailAddress.isEmpty()) {
            System.out.println("Email address must not be empty.");
            return getEmailAddress();
        } else if (matcher.matches() == false) {
            System.out.println("Email address is not valid. Please enter your email address in required format");
            return getEmailAddress();
        }
        return emailAddress;
    }

    public String getBirthdate() {
        System.out.println("Insert your Birthdate in required format ddmmyyyy");
        birthdate = scanner.nextLine();
        String regex = "^\\d{8,10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(birthdate);
        if (birthdate.isEmpty()) {
            System.out.println("Birthday must not be empty!");
            return getBirthdate();
        } else if (matcher.matches() == false) {
            System.out.println("Birthdate is not valid. Please enter your birthdate in required format");
            return getBirthdate();
        }
        return birthdate;
    }

    public String getPassword() {
        System.out.println("Insert your password");
        password = scanner.nextLine();
        String passwordToHash = password;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(passwordToHash.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).*[A-Za-z0-9].{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if (password.isEmpty()) {
            System.out.println("Password must not be empty!");
            return getPassword();
        } else if (password.length() < 8) {
            System.out.println("Password must be minimum 8 character long");
            return getPassword();
        } else if (!password.matches(".*[A-Z].*")) {
            System.out.println("Password must contain an Uppercase Letter");
            return getPassword();
        } else if (!password.matches(".*[0-9].*")) {
            System.out.println("Password must contain a number");
            return getPassword();
        } else if (matcher.matches() == false) {
            System.out.println("Password must contain a special character. Please enter your password in required format");
            return getPassword();
        }
        return getConfirmPassword();
    }

    public String getConfirmPassword() {
        System.out.println("Confirm your password");
        confirmPassword = scanner.nextLine();
        String confirmPasswordToHash = confirmPassword;
        String generatedConfirmPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(confirmPasswordToHash.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                generatedConfirmPassword = stringBuilder.toString();
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (password.equals(confirmPassword)) {
            System.out.println("You are registered successfully\n");
        } else {
            System.out.println("Password does not match");
            return getConfirmPassword();
        }
        return generatedConfirmPassword;
    }
}
