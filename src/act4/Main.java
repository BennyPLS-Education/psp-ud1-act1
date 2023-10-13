package act4;

import java.io.IOException;
import java.util.Scanner;

/**
 * <h1>Activitat 3
 * <p>
 * - Crear un programa (classe tipus main anomenada ExecutarDir) per executar
 * la comanda dir del directori del teu Escriptori i emmagatzemar el resultat en
 * un arxiu de text anomenat sortida.txt. Ha de retornar el missatge “Fitxer creat” i
 * el pare l’ha de mostrar per pantalla.
 * - Crear un programa (classe tipus main anomenada ExecutarFind) per executar
 * la comanda find /i "text" sortida.txt. El text el passarà el pare com a entrada.
 * - Crear un programa (classe tipus main anomenada Programa). Aquest ha de
 * demanar a l’usuari un nom de fitxer (p.e. sortida.txt) i ha d’executar els dos
 * programes anteriors.
 * La sortida estàndard del darrer fill executat la mostrarà el procés pare per
 * pantalla.
 */

public class Main {

    private final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        String path = getInput("Introdueix el path: ");
        String text = getInput("Introdueix el text: ");

        dirProcess(path);
        findProcess(path, text);
    }

    /**
     * Process a directory to a file.
     *
     * @param path the path to the directory to process
     */
    private static void dirProcess(String path) {
        Process processDir = getProcessDir();

        writeProcessInput(processDir, path);
        wait(processDir);
        readProcessOutput(processDir);
    }

    /**
     * Executes a process to find a specific text in a given file path.
     *
     * @param path The path to search for the text.
     * @param text The text to search for in the path.
     */
    private static void findProcess(String path, String text) {
        Process processFind = getProcessFind(path);

        writeProcessInput(processFind, text);
        wait(processFind);
        readProcessOutput(processFind);
    }

    /**
     * Writes the given input to the input stream of a process.
     *
     * @param process The process to write the input to.
     * @param input The input to write to the process.
     */
    private static void writeProcessInput(Process process, String input) {
        try (var writer = process.getOutputStream()) {
            writer.write(input.getBytes());
            writer.flush();
        } catch (IOException e) {
            System.out.println("No s'ha pogut escriure al procés fill");
            System.exit(1);
        }
    }

    /**
     * Method to read the output from a Process and transfer it to the standard output.
     *
     * @param process the Process object representing the running process
     */
    private static void readProcessOutput(Process process) {
        try {
            process.getInputStream().transferTo(System.out);
        } catch (IOException e) {
            System.out.println("No s'ha pogut llegir el procés fill");
        }
    }

    /**
     * This method prompts the user with a message and waits for user input from the console.
     *
     * @param message the message to display to the user
     * @return the user input as a String
     */
    private static String getInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    /**
     * Retrieves a Process object for executing the 'find' command using the given path.
     *
     * @param path the path to be used for the 'find' command
     * @return a Process object
     */
    private static Process getProcessFind(String path) {
        try {
            return getProcessBuilderFind(path).start();
        } catch (IOException e) {
            System.out.println("No s'ha pogut crear el procés fill 'find'");
            System.exit(1);
        }

        return null;
    }


    /**
     * Returns a Process object representing the 'dir' command executed as a separate process.
     *
     * @return a Process object.
     */
    private static Process getProcessDir() {
        try {
            return getProcessBuilderDir().start();
        } catch (IOException e) {
            System.out.println("No s'ha pogut crear el procés fill 'dir'");
            System.exit(1);
        }

        return null;
    }

    /**
     * Creates the process builder for the "act4.child.dir.jar" application.
     *
     * @return a ProcessBuilder of the "act4.child.dir.jar" application
     */
    private static ProcessBuilder getProcessBuilderDir() {
        return new ProcessBuilder("java", "-jar", "act4.child.dir.jar");
    }

    /**
     * Creates the process builder for the "act4.child.find.jar" application.
     *
     * @param path the path argument to be passed to the Java application
     * @return a ProcessBuilder of the "act4.child.find.jar" application
     */
    private static ProcessBuilder getProcessBuilderFind(String path) {
        return new ProcessBuilder("java", "-jar", "act4.child.find.jar", path);
    }

    /**
     * Waits for the given process to complete execution.
     *
     * @param command the process to wait for
     */
    private static void wait(Process command) {
        try {
            command.waitFor();
        } catch (InterruptedException e) {
            System.out.println("El procés fill ha estat interromput");
            System.exit(1);
        }

        if (command.exitValue() != 0) {
            System.out.println("El procés fill ha acabat amb un error");
            System.exit(1);
        }
    }
}
