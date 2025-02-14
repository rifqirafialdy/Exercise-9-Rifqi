package org.Rifqi.menu;

import org.Rifqi.entity.Task;
import org.Rifqi.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private final Scanner scanner = new Scanner(System.in);
    private final HashMap<String, List<Task>> tasks = new HashMap<>();
    private User currentUser;

    public void seUserSession(User user) {
        System.out.println("WELCOME   " + user.getUsername().toUpperCase());
        this.currentUser = user;
        manageTaskOption();
    }

    public void manageTaskOption() {
        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. View Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Logout");
            System.out.println("Please choose the option");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTask();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    return;
                default:
            }
        }
    }

    private void deleteTask() {
        List<Task> userTasks = tasks.getOrDefault(currentUser.getUsername(), new ArrayList<>());

        if (userTasks.isEmpty()) {
            System.out.println("YOU NOT HAVE ANY TASK YET");
            return;
        }

        viewTask();
        System.out.print("Enter task number to delete: ");

        if (!scanner.hasNextInt()) {
            System.out.println("INVALID INPUT, PLEASE ENTER A NUMBER");
            scanner.nextLine();
            return;
        }

        int taskIndex = scanner.nextInt();
        scanner.nextLine();

        if (taskIndex < 1 || taskIndex > userTasks.size()) {
            System.out.println("INVALID INPUT ! PLEASE REFER TO THE OPTION");
            return;
        }

        Task removedTask = userTasks.remove(taskIndex - 1);
        System.out.println(" Task deleted: " + removedTask.getName());
    }


    private void viewTask() {
        List<Task> userTasks = tasks.getOrDefault(currentUser.getUsername(), new ArrayList<>());

        if (userTasks.isEmpty()) {
            System.out.println("NO TASK FOUND");
            return;
        }

        System.out.println("\n Your Tasks:");
        for (int i = 0; i < userTasks.size(); i++) {
            Task task = userTasks.get(i);
            System.out.println((i + 1) + ". " + task.getName() + " | Done: " + task.isDone());
        }

    }

    private void addTask() {
        System.out.println("Enter Task Name :");
        String taskName = scanner.nextLine();
        Task newTask = new Task(taskName);
        if (!tasks.containsKey(currentUser.getUsername())) {
            tasks.put(currentUser.getUsername(), new ArrayList<>());
        }
        tasks.get(currentUser.getUsername()).add(newTask);
        System.out.println("TASK ADDED TO YOUR LIST : " + newTask.getName() + " | Done: " + newTask.isDone());
        scanner.nextLine();
    }
}
