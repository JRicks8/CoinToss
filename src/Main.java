import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String userResponse;
        while (true) {
            System.out.print("What would you like to do? Flip a (c)oin or roll a (d)ie: ");
            userResponse = scanner.nextLine().trim().toLowerCase();

            if (userResponse.equals("c") || userResponse.equals("coin")) {

                int numFlips = getNumCoinsToFlip(scanner);
                int[] results = getCoinFlipResults(numFlips);

                System.out.println("Totals - Heads: " + results[0] + ", Tails: " + results[1]);

                // Likelihood of observed scenario
                float percentile = (float)results[0] / numFlips;
                System.out.printf("This total of heads vs tails is in the %f percentile.%n", percentile * 100);
                printLikelihoodResponse(percentile);
            } else if (userResponse.equals("d") || userResponse.equals("die")) {

                int numRolls = getNumDiceToRoll(scanner);
                int[] results = getDiceRollResults(numRolls);

                System.out.println("Totals - \n" +
                        "Ones: " + results[0] + "\n" +
                        "Twos: " + results[1] + "\n" +
                        "Threes: " + results[2] + "\n" +
                        "Fours: " + results[3] + "\n" +
                        "Fives: " + results[4] + "\n" +
                        "Sixes: " + results[5]);

                // Likelihood of observed scenario
                int highRange = numRolls * 6;
                int resultsSum = 0;
                for (int i = 0; i < results.length; i++)
                    resultsSum += results[i] * (i + 1);
                float percentile = (float)(resultsSum - numRolls) / (highRange - numRolls);
                System.out.println("Sum of all rolls: " + resultsSum);
                System.out.printf("This total of rolls is in the %f percentile.%n", percentile * 100);
                printLikelihoodResponse(percentile);
            } else if (userResponse.equals("no") || userResponse.equals("n")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid response. Please enter 'yes' or 'no'.");
            }
        }

        scanner.close();
    }

    public static int getNumCoinsToFlip(Scanner scanner) {
        System.out.print("How many coins would you like to flip? (1-1000): ");
        int numFlips = 0;
        while (numFlips == 0) {
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
        }

        return numFlips;
    }

    public static int[] getCoinFlipResults(int numFlips) {
        int[] results = new int[2];
        Random random = new Random();

        for (int i = 0; i < numFlips; i++) {
            int flipResult = random.nextInt(2);

            if (flipResult == 0) {
                if (numFlips <= 10) System.out.println("Heads");
                results[0]++;
            } else {
                if (numFlips <= 10) System.out.println("Tails");
                results[1]++;
            }
        }

        return results;
    }

    public static int getNumDiceToRoll(Scanner scanner) {
        System.out.print("How many dice would you like to roll? (1-1000): ");
        int numRolls = 0;
        while (numRolls == 0) {
            try {
                numRolls = Integer.parseInt(scanner.nextLine().trim());
                if (numRolls < 1 || numRolls > 1000) {
                    System.out.println("Please enter a number between 1 and 1000.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid integer.");
                continue;
            }
        }

        return numRolls;
    }

    public static int[] getDiceRollResults(int numRolls) {
        int[] results = new int[6];
        Random random = new Random();

        for (int i = 0; i < numRolls; i++) {
            int rollResult = random.nextInt(6);
            results[rollResult]++;
            if (numRolls <= 10) System.out.println(rollResult + 1);
        }

        return results;
    }

    public static void printLikelihoodResponse(float percentile) {
        float v = Math.abs(50 - percentile * 100);
        if (v <= 10) {
            System.out.println("Pretty standard likelihood.");
        } else if (v <= 20) {
            System.out.println("Pretty uncommon results!");
        } else if (v <= 30) {
            System.out.println("Wow! That's pretty rare!");
        } else if (v <= 40) {
            System.out.println("HOLY MOLY! Those are INSANE results!");
        } else if (v <= 50) {
            System.out.println("Now that... that is as rare as it gets. Well done.");
        }
    }
}