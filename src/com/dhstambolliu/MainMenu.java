package com.dhstambolliu;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class MainMenu {

    public void mainMenu() {
        System.out.println("Press 1 to login");
        System.out.println("Press 2 to register");
    }

    public void signUp() {
        RegisterApplication registerApplication = new RegisterApplication();
        try {
            FileWriter myRegisteredUsers = new FileWriter("RegisteredUsers.txt", true);
            myRegisteredUsers.write(registerApplication.getName() + "\t"
                    + registerApplication.getSurname() + "\t"
                    + registerApplication.getEmailAddress() + "\t"
                    + registerApplication.getBirthdate() + "\t"
                    + registerApplication.getPassword() +
                    "\n");
            myRegisteredUsers.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login() throws FileNotFoundException {
        LoginApplication loginApplication = new LoginApplication();
        loginApplication.verifyIsUserIsRegistered();
    }
}
