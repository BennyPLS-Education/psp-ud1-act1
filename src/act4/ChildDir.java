package act4;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The ChildDir class is a utility that executes the ls/dir command on the user home.
 * <p>
 * On Linux, it uses the "ls".
 * <p>
 * On Windows, it uses the "dir".
 */
public class ChildDir {
    public static void main(String[] args) {
        Process command = getProcess();

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

        final String[] lines = getProcessOutput(command);

        writeFile(lines);

        System.out.println("Fitxer creat");
    }

    /**
     * Writes the given array of lines to a file named "output.txt".
     *
     * @param lines the array of lines to write to the file
     */
    private static void writeFile(String[] lines) {
        try (var writer = new FileWriter("output.txt")) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("No s'ha pogut escriure al fitxer 'output.txt'");
            System.exit(1);
        }
    }

    /**
     * Retrieves the output of a given process as an array of strings.
     *
     * @param command The process to get the output from.
     * @return An array of strings representing the output lines of the process.
     */
    private static String[] getProcessOutput(Process command) {
        String[] lines = null;

        try (var reader = new BufferedReader(new InputStreamReader(command.getInputStream()))) {
            lines = reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            System.out.println("No s'ha pogut llegir el procés fill");
            System.exit(1);
        }

        return lines;
    }

    /**
     * Retrieves a new process object from the getProcessBuilder method.
     *
     * @return The process object.
     */
    private static Process getProcess() {
        ProcessBuilder pb = getProcessBuilder();
        Process process = null;

        try {
            process = pb.start();
        } catch (IOException e) {
            System.out.println("No s'ha pogut crear el procés fill");
            System.exit(1);
        }

        return process;
    }

    /**
     * Returns a ProcessBuilder object configured based on the operating system.
     *
     * @return A ProcessBuilder object configured with the appropriate command based on the operating system.
     */
    private static ProcessBuilder getProcessBuilder() {
        ProcessBuilder pb = null;
        String os = System.getProperty("os.name").toLowerCase();
        String userHome = System.getProperty("user.home");


        switch (os) {
            case "linux" -> pb = new ProcessBuilder("ls", "-l", userHome);
            case "windows" -> pb = new ProcessBuilder("dir", userHome);
            default -> {
                System.out.println("Sistema operatiu no suportat");
                System.exit(1);
            }
        }

        return pb;
    }
}
