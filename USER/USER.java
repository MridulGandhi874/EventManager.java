package USER;

import java.io.*;
import java.util.*;

abstract class AbstractUser {
    protected String name;
    protected int age;
    protected long sapId;

    public AbstractUser(String name, int age, long sapId) {
        this.name = name;
        this.age = age;
        this.sapId = sapId;
    }

    public abstract void displayDetails();
}

interface UserOperations {
    void addUser();
    void viewUsers();
}

class NormalUser extends AbstractUser {
    public NormalUser(String name, int age, long sapId) {
        super(name, age, sapId);
    }

    @Override
    public void displayDetails() {
        System.out.println("User: " + name + " | Age: " + age + " | SAP ID: " + sapId);
    }
}

public class USER implements UserOperations {
    private static final String FILE_NAME = "users.txt";

    @Override
    public void addUser() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Age: ");
            int age = scanner.nextInt();
            System.out.print("Enter SAP ID: ");
            long sapId = scanner.nextLong();

            NormalUser user = new NormalUser(name, age, sapId);
            saveToFile(user);
            System.out.println("User Added Successfully!");
        } catch (Exception e) {
            System.out.println("Invalid Input! Please enter correct values.");
        } finally {
            scanner.close();
        }
    }

    @Override
    public void viewUsers() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("No users found.");
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                System.out.println("Error closing file.");
            }
        }
    }

    private void saveToFile(NormalUser user) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(FILE_NAME, true));
            bw.write(user.name + "," + user.age + "," + user.sapId);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving user data.");
        } finally {
            try {
                if (bw != null) bw.close();
            } catch (IOException e) {
                System.out.println("Error closing file.");
            }
        }
    }

    public void operation() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("1. ADD USER");
                System.out.println("2. VIEW USERS");
                System.out.println("3. GO BACK");
                System.out.print("Enter your choice: ");
                int operation = scanner.nextInt();
                System.out.println();
                switch (operation) {
                    case 1:
                        addUser();
                        break;
                    case 2:
                        viewUsers();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid Choice! Try Again.");
                }
            }
        } finally {
            scanner.close();
        }
    }
}
