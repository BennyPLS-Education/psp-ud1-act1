package act1;

/**
 * <h1>Activitat 1</h1>
 *
 * Escriu un programa anomenat “ExercicisMultiproces1_ParellSenar”.
 * Aquest rebrà un nombre enter positiu i haurà de mostrar el resultat
 * “Parell” o “Senar”.
 */
public class Main {

    public static void main(String[] args) {
        int even = 2;
        int odd = 3;

        System.out.println("El número " + even + " es " + is_even(even));
        System.out.println("El número " + odd + " es " + is_even(odd));
    }

    /**
     * Check whether a given number is even or not.
     *
     * @param number the number to be checked
     * @return true if the number is even, false otherwise
     */
    public static boolean is_even(int number) {
        return number % 2 == 0;
    }
}
