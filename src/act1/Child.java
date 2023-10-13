package act1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Child {
    /**
     * The main method for the application.
     * <p>
     * This method prompts the user for input, receives the input, and prints whether the input number is even or odd.
     */
    public static void main(String[] args) {
        int number = getNumber();

        try {
            System.out.println(is_even(number) ? "Parell" : "Senar");
        } catch (NumberFormatException e) {
            System.out.println("Introdueix un número vàlid");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Introdueix un número com a argument");
        }
    }

    /**
     * Get a number from the command line arguments.
     *
     * @return the number
     */
    static int getNumber() {
        var scan = new Scanner(System.in);

        while (true) {
            try {
                return scan.nextInt();
            } catch (InputMismatchException e) {
                scan.next();
            }
        }
    }

    /**
     * Check whether a given number is even or not.
     *
     * @param number the number to be checked
     * @return true if the number is even, false otherwise
     */
    static boolean is_even(int number) {
        return number % 2 == 0;
    }
}
