
package gameofcraps;

import java.text.DecimalFormat;
import java.util.Scanner;

public class GameOfCraps {
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.##");
 
        double wager;
        double human_winnings = 0;
        double computer_winnings = 0;
        int num_games_to_play;
        
        final int MAX_WAGER = 100;
        Dice dice;

        num_games_to_play = promptForNumGames();
        for (int i = 1; i <= num_games_to_play; i++) {

            // HUMAN'S ROUND
            wager = getHumansWager(MAX_WAGER);
            System.out.println("\nHuman Round");
            dice = new Dice();
            playRound(dice);

            if (dice.getState() instanceof Win) {
                System.out.println("Human Won!!!");
                human_winnings = human_winnings + wager;
            }
            else {
                System.out.println("Human Lost...");
                human_winnings = human_winnings - wager;
            }

            // COMPUTER'S ROUND
            wager = randomly_generate(MAX_WAGER);
            System.out.println("\nComputer Wagered $" + df.format(wager));
            System.out.println("\nComputer Round");
            dice = new Dice();
            playRound(dice);

            if (dice.getState() instanceof Win) {
                System.out.println("Computer Won!!!");
                computer_winnings = computer_winnings + wager;
            }
            else {
                System.out.println("Computer Lost...");
                computer_winnings = computer_winnings - wager;
            }

            System.out.println("\nHuman Winnings: $" + df.format(human_winnings) + "\nComputer Winnings: $"
                    + df.format(computer_winnings) + "\n\n--------------------------------------------\n");
        }

        scanner.close();
    } 
        
    public static int promptForNumGames() {
        System.out.println("How many games do you want to play?");
        int games = scanner.nextInt();
        while (games < 0) {
            System.out.println("Incorrect number of games, you can only choose a number greater then 0.");
            games = scanner.nextInt();
        }
        return games;
    }

    public static double getHumansWager(double max_wager) {
        System.out.println("How much do you want to bet?  Max is $" + max_wager + ".");
        double wager = scanner.nextDouble();
        while (wager < 1 || wager > max_wager) {
            System.out.println("Incorrect amount, you can only choose between $1 and $" + max_wager + ".");
            wager = scanner.nextDouble();
        }
        return wager;
    }
    // prompts for and returns wager between 1 and max_wager dollars

    public static double randomly_generate(int max) {
        return ((Math.random() * max) + 1);
    }
    // generates random integer between 1 and max

    public static void playRound(Dice dice) {
    // play until win or loss occurs
        while(!(dice.getState() instanceof gameofcraps.Win) &&
              !(dice.getState() instanceof gameofcraps.Loss))
            dice.rollDice();
    }
}
