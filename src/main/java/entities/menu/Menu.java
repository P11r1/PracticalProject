package entities.menu;

import entities.Tasks;

import java.util.Scanner;


public class Menu {

    static Scanner scanner = new Scanner(System.in);

    public static void chooseAccount() {
        System.out.println("Choose an account for continue : ");
        System.out.println("1. Users");
        System.out.println("2. Admin");

        System.out.println();

        int option;
        option = scanner.nextInt();

        switch (option) {
            case 1:
                usersAccount();
                break;
            case 2:
                adminAccount();
                break;
            default:
                System.out.println("Invalid option.");
                chooseAccount();
                break;
        }
    }

    public static void usersAccount() {

        System.out.println("Pick an option to continue : ");
        System.out.println("1.View the tasks");
        System.out.println("2.Update the tasks");
        System.out.println("3.Delete the tasks");
        System.out.println("4.Mark the tasks"); // User can update the tasks.
        // So we do not need to add something.
        System.out.println("4.View progress of the project by entering the project name");
        System.out.println("5.Set due dates for tasks");
        // User can update the tasks.
        // So we do not need to add something.

        System.out.println();
        System.out.println("Select an option or enter 0 to go back to the account menu : ");
        int option = scanner.nextInt();

        switch (option) {
            case 0 :
                chooseAccount();
                break;
            case 1:
                Tasks.listTasks();
                usersAccount();
            case 2:
                Tasks.updateTasks(Integer.valueOf(null),"","","", Boolean.parseBoolean(""));
                usersAccount();
            case 3:
                Tasks.listTasks();
                Tasks.deleteTasks();
                usersAccount();
                //case 4:
                //Project.listProject;
                //usersAccount();

        }
    }

    public static void adminAccount() {

    }

}


