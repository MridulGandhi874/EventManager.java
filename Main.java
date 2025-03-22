import java.util.Scanner;
import USER.USER;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        USER userManager = new USER();

        System.out.println("Welcome to the Event Manager!!");

        while (true) {
            try {
                System.out.println("Select the Operation You want to perform: ");
                System.out.println("1. PERFORM USER OPERATIONS");
                System.out.println("2. EXIT");
                System.out.print("Enter your choice: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a number (1 or 2).");
                    scanner.next();
                    continue;
                }

                int operation = scanner.nextInt();
                System.out.println();

                switch (operation) {
                    case 1:
                        userManager.operation();
                        break;
                    case 2:
                        System.out.println("Exiting... Thank you!");
                        return;
                    default:
                        System.out.println("Invalid choice! Please enter 1 or 2.");
                }
            } catch (Exception e) {
                System.out.println("Something went wrong. Please try again.");
                scanner.nextLine();
            }
        }
    }
}
