import java.util.*;

public class CodeCracker {
    private static String hiddenCode;
    // Stores the generated secret code
    private static int codeLength;
    // Stores the length of the secret code

    public CodeCracker(int codeLength, boolean allowRepeats) {
        // Constructor initializes game settings and generates the hidden code
        this.codeLength = codeLength;
        // comparison of user entered length
        this.hiddenCode = generateCode(codeLength, allowRepeats);
    }

    // Generates a random code with specified length, allowing or restricting repeated numbers
    private String generateCode(int length, boolean allowRepeats) {
        Random rand = new Random();
        // compares the generated random number
        char[] digits = new char[10];
        // Array to store digits in it ( array size = 10)
        for (int i = 0; i < 10; i++) digits[i] = (char) ('0' + i);
        // iteration
        StringBuilder code = new StringBuilder();

        while (code.length() < length) {
            // Keep generating digits until the required length is met
            int index = rand.nextInt(10);
            char digit = digits[index];
            // comparison of characters with the digits position
            if (allowRepeats || code.indexOf(String.valueOf(digit)) == -1) {
                code.append(digit);
            }
        }

        return code.toString();
    }

    // Checks the user's guess against the hidden code and provides feedback
    public boolean checkGuess(String guess) {
        System.out.println(guess);
        if (guess.length() != codeLength) {
            // Check guess matches required length
            System.out.println("Invalid length. Enter " + codeLength + " digits.");
            return false;
        }

        int correctChars = 0, correctPositions = 0;
        for (int i = 0; i < codeLength; i++) {
            if (hiddenCode.contains(String.valueOf(guess.charAt(i)))) correctChars++;
            if (hiddenCode.charAt(i) == guess.charAt(i)) {
                correctPositions++;
            };
        }

        // Display feedback to the user
        System.out.println("Your guess: " + guess + " | Correct chars: " + correctChars + " | Correct positions: " + correctPositions);
        return guess.equals(hiddenCode);
        // Return true if the guess matches the hidden code
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input for code length, ensuring it is between 2 and 6
        System.out.print("Enter code length (2-6): ");
        int length = Math.max(2, Math.min(6, scanner.nextInt()));

        // Ask whether repeated numbers are allowed in the code
        System.out.print("Allow repeated numbers? (true/false): ");
        boolean allowRepeats = scanner.nextBoolean();

        // Ask for a maximum number of attempts (0 for unlimited attempts)
        System.out.print("Max attempts: ");
        int maxAttempts = scanner.nextInt();

        // Create the game instance
        CodeCracker game = new CodeCracker(length, allowRepeats);
        System.out.println("Start guessing:");

        // Loop through attempts until the user wins or reaches the max attempts
        for (int attempts = 1; maxAttempts == 0 || attempts <= maxAttempts; attempts++) {
            System.out.print("Attempt " + attempts + ": ");
            if (game.checkGuess(scanner.next())) { // If guess is correct, end game
                System.out.println("You cracked the code in " + attempts + " attempts!");
                return;
            }
        }
        // Display the correct code if the user fails to guess it
        System.out.println("Game over! The code was: " + game.hiddenCode);

    }
}
