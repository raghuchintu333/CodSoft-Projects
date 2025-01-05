import java.util.Scanner;
import java.util.Random;

public class NumGame 
{
    public static void main(String[] args)
     {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) 
        {
            
            int number = random.nextInt(100) + 1; 
            int maxAttempts = 10;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nA random number between 1 and 100 has been generated. Try to guess it!");

            
            while (attempts < maxAttempts) 
            {
                System.out.printf("Attempt %d/%d: Enter your guess: ", attempts + 1, maxAttempts);
                int guess = scanner.nextInt();
                attempts++;

                if (guess == number) 
                {
                    System.out.printf("Congratulations! You guessed the correct number in %d attempts.%n", attempts);
                    score++;
                    guessedCorrectly = true;
                    break;
                } else if (guess < number)
                 {
                    System.out.println("Too low! Try again.");
                } else
                 {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!guessedCorrectly) 
            {
                System.out.printf("Sorry! You've used all %d attempts. The number was %d.%n", maxAttempts, number);
            }

            System.out.printf("Your current score: %d%n", score);

            
            System.out.print("Do you want to play another round? (yes/no): ");
            String choice = scanner.next().toLowerCase();
            playAgain = choice.equals("yes");
        }

        System.out.println("Thanks for playing! Goodbye.");
        scanner.close();
    }
}