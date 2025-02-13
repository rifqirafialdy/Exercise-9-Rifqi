package org.Rifqi.menu;

import org.Rifqi.entity.User;

import java.util.HashMap;
import java.util.Scanner;

public class Auth {
    private final Scanner scanner = new Scanner(System.in);
    HashMap<String, User> registeredUser = new HashMap<>();


    public void login() {
        System.out.println("----------- LOGIN ------------");
        System.out.println("User Name :");
        String userName = scanner.nextLine();
        System.out.println("Password :");
        String password = scanner.nextLine();
        User user = registeredUser.get(userName);
        if (user == null) {
            System.out.println("USER NOT FOUND, WOULD YOU LIKE TO REGISTER ? (YES/NO)");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                register();
            } else if (!response.equalsIgnoreCase("no")) {
                System.out.println("INVALID INPUT PLEASE TRY AGAIN !");
            }
            return;
        }
        if (user.checkLogin(userName, password)) {
            System.out.println("Login Success");
            TaskManager taskManager = new TaskManager();
            taskManager.manageTaskOption(userName);
        }
    }

    public void register() {
        while (true) {
            System.out.println("---- REGISTER ----");
            System.out.println("User Name :");
            String userName = scanner.nextLine();
            if (registeredUser.containsKey(userName)) {
                System.out.println("USERNAME ALREADY TAKEN, PLEASE TRY ANOTHER");
                scanner.nextLine();
                continue;
            }
            System.out.println("Password :");
            String password = scanner.nextLine();
            if (userName.isEmpty() || password.isEmpty()) {
                System.out.println("PLEASE FILL ALL THE INPUT");
                scanner.nextLine();
                continue;
            }

            User user = new User(userName, password);
            registeredUser.put(userName, user);
            break;
        }
        System.out.println("====== REGISTER SUCCESS ======");

    }

    public void runAuth() {
        while (true) {
            System.out.println("=============================");
            System.out.println("|||| WELCOME TO TASK APP ||||");
            System.out.println("=============================");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            if (!scanner.hasNextInt()) {
                System.out.println("INVALID INPUT PLEASE TRY AGAIN !");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice < 1 || choice > 3) {
                System.out.println("INVALID INPUT PLEASE TRY AGAIN !");
                scanner.nextLine();
                continue;
            }
            switch (choice) {
                case 1 -> login();
                case 2 -> register();
                case 3 -> {
                    System.out.println("Good Bye......");
                    return;
                }
            }

        }


    }

}
