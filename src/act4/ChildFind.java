package act4;

import java.io.IOException;
import java.util.Scanner;

/**
 * The ChildFind class is a utility that searches for a specified text in a file specified by the user.
 * <p>
 * The text and file path are provided as command-line arguments. The utility works differently based on the operating system.
 * <p>
 * On Linux, it uses the "grep" command with the case-insensitive option to search for the text in the file.
 * <p>
 * On Windows, it uses the "find" command with the case-insensitive option to search for the text in the file.
 */
class ChildFind {
    public static void main(String[] args) {
        String path = getPath(args);
        String text = getText();

        Process command = getProcess(text, path);

        wait(command);

        try {
            command.getInputStream().transferTo(System.out);
        } catch (IOException e) {
            System.out.println("No s'ha pogut llegir el procés fill");
        }
    }

    /**
     * Reads a line of text from the standard input using the Scanner class.
     *
     * @return the line of text read from the standard input
     */
    private static String getText() {
        try (var scanner = new Scanner(System.in)) {
            return scanner.nextLine();
        }
    }

    /**
     * Returns the path from the command line arguments.
     *
     * @param args the command line arguments
     * @return the path represented as a string
     */
    private static String getPath(String[] args) {
        if (args.length != 1) {
            System.out.println("S'ha d'introduir un argument");
            System.exit(1);
        }

        return args[0];
    }

    /**
     * Returns a Process object created from the given text and path using the getProcessBuilder method.
     *
     * @param text the text to be processed
     * @param path the path used for the process
     * @return The created process object.
     */
    private static Process getProcess(String text, String path) {
        ProcessBuilder pb = getProcessBuilder(text, path);
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
     * Returns a ProcessBuilder object configured based on the given text and path.
     *
     * @param text the text to be searched
     * @param path the path to search in
     * @return A ProcessBuilder object configured with the appropriate command based on the operating system.
     */
    private static ProcessBuilder getProcessBuilder(String text, String path) {
        ProcessBuilder pb = null;
        String os = System.getProperty("os.name").toLowerCase();

        switch (os) {
            case "linux" -> pb = new ProcessBuilder("grep", "-i", text, path);
            case "windows" -> pb = new ProcessBuilder("find", "/i", text, path);
            default -> {
                System.out.println("Sistema operatiu no suportat");
                System.exit(1);
            }
        }

        return pb;
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
    }
}
