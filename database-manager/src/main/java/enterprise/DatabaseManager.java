
package enterprise;

import enterprise.rest.Operations;
import enterprise.rest.Person;

import java.util.Scanner;

public class DatabaseManager {

    public static void main(String[] args) {

        Operations operations = new Operations();
        Scanner input = new Scanner(System.in);

        while (true) {

            String line = "";

            System.out.println("\n--DATA BASE MANAGER--\n" +
                    "Command - \"EXIT\" exit from the program\n" +
                    "Command - \"GETALL\" get all users from the DB\n" +
                    "Command - \"GETBYNAME\" get user by name from the DB\n" +
                    "Command - \"ADD\" add a new user into DB\n" +
                    "Command - \"DELETE\" delete a user by name from the DB\n");

            line = input.nextLine();

            // stop the program by command "exit"
            if (line.equalsIgnoreCase("Exit")) {
                break;
            }

            // get all users by command "getall"
            if (line.equalsIgnoreCase("GETALL")) {

                if (operations.getAll() == null) {

                    System.out.println("Database is empty");

                } else {

                    System.out.println(operations.getAll());
                }
            }

            // get user by name by command "getbyname"
            if (line.equalsIgnoreCase("GETBYNAME")) {

                System.out.println("Enter name:");

                String name = input.nextLine();

                if (name.isBlank()) {

                    System.out.println("Name can't be empty");
                    continue;
                }

                String lowerCaseName = name.toLowerCase();

                if (operations.getByName(lowerCaseName) == null) {

                    System.out.println("Such user doesn't exists");

                } else {

                    System.out.println(operations.getByName(name));
                }
            }

            // add a new user by command "add"
            if (line.equalsIgnoreCase("ADD")) {

                String name = "";
                String password = "";

                System.out.println("Enter name:");

                name = input.nextLine();

                if (name.isBlank()) {

                    System.out.println("Name can't be empty");
                    continue;
                }

                String lowerCaseName = name.toLowerCase();

                if (operations.getByName(lowerCaseName) == null) {

                    System.out.println("Enter password:");

                    password = input.nextLine();

                    if (password.isBlank()) {

                        System.out.println("Password can't be empty");
                        continue;
                    }

                    String lowerCasePassword = password.toLowerCase();

                    Person person = new Person(lowerCaseName, lowerCasePassword);

                    operations.add(person);

                    System.out.println("User " + person.getUsername() + " successfully added");

                } else {

                    System.out.println("Such user already exists");
                }
            }

            // delete a user by command "delete"
            if (line.equalsIgnoreCase("DELETE")) {

                String name = "";

                System.out.println("Enter name:");

                name = input.nextLine();

                if (name.isBlank()) {

                    System.out.println("Name can't be empty");
                    continue;
                }

                String lowerCaseName = name.toLowerCase();

                if (operations.getByName(lowerCaseName) == null) {

                    System.out.println("Such user doesn't exists");

                } else {

                    operations.delete(lowerCaseName);

                    System.out.println("User " + lowerCaseName + " successfully deleted");
                }
            }
        }
    }
}
