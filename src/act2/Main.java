package act2;

import java.io.IOException;
import java.util.Scanner;

/**
 * <h1>Activitat 2
 * <p>
 * Escriu un programa que faci el següent:
 * a. Crear un procés fill (ExercicisMultiproces2_ModificarString )
 * <p>
 * b. El procés pare (ExercicisMultiproces2 ) i el procés fill es comunicarà de
 * manera bidireccional utilitzant streams.
 * <p>
 * c. El procés pare llegirà línies de la seva entrada estàndard i les enviarà a
 * l'entrada estàndard del fill (utilitzant l'OutputStream del fill).
 * <p>
 * d. El procés fill llegirà el text per la seva entrada estàndard, el transformarà
 * tot a lletres majúscules i substituirà totes les vocals per el símbol guió
 * baix “_”.
 * <p>
 * e. El pare imprimirà en pantalla el que rep del fill a través de l'InputStream
 * del mateix.
 * <p>
 * f. Quan el pare parla la sortida per pantalla ha de començar amb: “El
 * PARE diu:” i quan el fill parla, ha de començar per: “El Fill diu:”
 */

public class Main {
    public static void main(String[] args) {
        Process process = null;
        try {
            process = new ProcessBuilder("java", "-jar", "act3.child.jar").start();
        } catch (IOException e) {
            System.out.println("No s'ha pogut crear el procés fill");
            System.exit(1);
        }
        String inputLine;

        try (var scanner = new Scanner(System.in)) {
            inputLine = scanner.nextLine();
        }

        try (var writer = process.getOutputStream()) {
            writer.write(inputLine.getBytes());
            writer.flush();
        } catch (IOException ignored) {
            System.out.println("No s'ha pogut enviar el missatge al procés fill");
        }

        try {
            process.waitFor();
        } catch (InterruptedException e) {
            System.out.println("El procés fill ha estat interromput");
        }

        try {
            System.out.print("El Pare diu: ");
            process.getInputStream().transferTo(System.out);
        } catch (IOException e) {
            System.out.println("No s'ha pogut llegir el missatge del procés fill");
        }

    }
}
