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

                System.out.print("How many coins would you like to flip? (1-1000)");
                int numFlips;
                try {
                    numFlips = Integer.parseInt(scanner.nextLine().trim());
                    if (numFlips < 1 || numFlips > 1000) {
                        System.out.println("Please enter a number between 1 and 1000.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Please enter a valid integer.");
                    continue;
                }

                for (int i = 0; i < numFlips; i++) {
                    int flipResult = random.nextInt(2);

                    if (flipResult == 0) {
                        if (numFlips <= 10) System.out.println("Heads");
                        headsCount++;
                    } else {
                        if (numFlips <= 10) System.out.println("Tails");
                        tailsCount++;
                    }
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