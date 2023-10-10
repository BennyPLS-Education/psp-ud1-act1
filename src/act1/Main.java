package act1;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * <h1>Activitat 1</h1>
 * <p>
 * Escriu un programa anomenat “ExercicisMultiproces1_ParellSenar”.
 * Aquest rebrà un nombre enter positiu i haurà de mostrar el resultat
 * “Parell” o “Senar”.
 * b. Escriu un programa anomenat “ExercicisMultiproces1”. Creará un procés
 * fill per executar el programa “ParellSenar” anterior. Aquest procés pare
 * mostrarà per pantalla “Introdueix un nombre:” i l’usuari por introduir:
 * <p>
 * - Un nombre enter positiu.
 * En aquest cas, es creará un procés fill (cridant a
 * ParellSenar) i es mostrarà el resultat de l’execució.
 * - “exit” per acabar l’execució del programa.
 */

public class Main {
    public static void main(String[] args) {
        Process process = null;
        try {
            process = new ProcessBuilder("java", "-jar", "act1.child.jar").start();
        } catch (IOException e) {
            System.out.println("No s'ha pogut crear el procés fill");
            System.exit(1);
        }

        int number = getNumber();

        try (var writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
            writer.write(String.valueOf(number));
            writer.newLine();
            writer.flush();
        } catch (IOException ignored) {
            System.out.println("No s'ha pogut enviar el missatge al procés fill");
            System.exit(1);
        }

        try {
            process.waitFor();
        } catch (InterruptedException e) {
            System.out.println("El procés fill ha estat interromput");
            System.exit(1);
        }

        try {
            process.getInputStream().transferTo(System.out);
        } catch (IOException e) {
            System.out.println("No s'ha pogut llegir el missatge del procés fill");
            System.exit(1);
        }
    }


    /**
     * Prompts the user to enter a number and keeps prompting until a valid number is entered.
     *
     * @return The valid number entered by the user.
     */
    public static int getNumber() {
        var input = new Scanner(System.in);
        Integer a = null;

        try {
            System.out.print("Introdueix un número: ");
            a = input.nextInt();
        } catch (InputMismatchException e) {
            input.next();
            System.out.println("Introdueix un número vàlid");
        }

        return Objects.requireNonNullElseGet(a, Main::getNumber);
    }
}
