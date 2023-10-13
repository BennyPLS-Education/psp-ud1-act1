package act2;

import java.util.Scanner;

public class Child {
    /**
     * The main method reads user input from the console and transforms it as follows:
     * 1. Converts the input string to uppercase.
     * 2. Trims leading and trailing whitespace from the input string.
     * 3. Replaces all vowel characters (A, E, I, O, U) with an underscore character (_).
     * <p>
     * The transformed input string is then printed to the console with a prefix.
     *
     */
    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {
            var input = scanner.nextLine();

            input = input.toUpperCase().trim();
            input = input.replaceAll("[AEIOU]", "_");

            System.out.println("El Fill diu: " + input);
        }
    }
}
