import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        String userResponse;
        int headsCount = 0;
        int tailsCount = 0;
        while (true) {
            System.out.print("Would you like to flip a coin? (yes/no): ");
            userResponse = scanner.nextLine().trim().toLowerCase();

            if (userResponse.equals("yes") || userResponse.equals("y")) {
                int flipResult = random.nextInt(2);

                if (flipResult == 0) {
                    System.out.println("Heads");
                    headsCount++;
                } else {
                    System.out.println("Tails");
                    tailsCount++;
                }

                System.out.println("Running totals - Heads: " + headsCount + ", Tails: " + tailsCount);
            } else if (userResponse.equals("no") || userResponse.equals("n")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid response. Please enter 'yes' or 'no'.");
            }
        }

        scanner.close();
    }
}