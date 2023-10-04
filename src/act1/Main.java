package act1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <h1>Activitat 1</h1>
 * <p>
 * Escriu un programa anomenat “ExercicisMultiproces1_ParellSenar”.
 * Aquest rebrà un nombre enter positiu i haurà de mostrar el resultat
 * “Parell” o “Senar”.
 */

public class Main {
    public static void main(String[] args) {
        int number = getNumber();

        try {
            System.out.println(is_even(number));
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
                System.out.print("Introdueix un número: ");
                return scan.nextInt();
            } catch (InputMismatchException e) {
                scan.next();
                System.out.println("Introdueix un número vàlid");
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
