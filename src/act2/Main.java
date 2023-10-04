package act2;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <h1>Activitat 1</h1>
 * <p>
 * Escriu un programa anomenat “ExercicisMultiproces1”. Creará un procés
 * fill per executar el programa “ParellSenar” anterior. Aquest procés pare
 * mostrarà per pantalla “Introdueix un nombre:” i l’usuari por introduir:
 * <p>
 * - Un nombre enter positiu.
 * En aquest cas, es creará un procés fill (cridant a
 * ParellSenar) i es mostrarà el resultat de l’execució.
 * - “exit” per acabar l’execució del programa.
 */

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        int number = getNumber();

        ProcessBuilder pb = new ProcessBuilder("java", "-jar", "act1.jar");
        Process process = pb.start();

        try (var writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
            writer.write(String.valueOf(number));
            writer.newLine();
            writer.flush();
        } catch (IOException ignored) {}

        process.waitFor();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Resultado: es par -  " + line.split(": ")[1]);
            }
        }
    }

    public static int getNumber() {
        var input = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Introdueix un número: ");
                return input.nextInt();
            } catch (InputMismatchException e) {
                input.next();
                System.out.println("Introdueix un número vàlid");
            }
        }
    }
}
