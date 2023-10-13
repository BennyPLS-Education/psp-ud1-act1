package act3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <h1>Activitat 3
 * <p>
 * Crea 2 programes en Java de manera que un (el pare) executa l'altre (el fill).
 * Crea els streams necessaris de manera que la comunicació entre pare i fill
 * flueixi en els dos sentits.
 */
public class Main {
    private final static String CHILD_PREFIX = "\t";
    private final static String PREFIX = "Pare: ";
    private final static String SALUTATION = "Salutacions del pare";
    private final static String RECEIVED_NOTIFICATION = "rep missatge del fill ";
    private final static String SEND_NOTIFICATION = "envia missatge";

    public static void main(String[] args) {
        Process process = getProcess();

        System.out.println(PREFIX + SEND_NOTIFICATION);

        writeTo(process);

        readFrom(process);
    }

    /**
     * Retrieves the process of a child program.
     *
     * @return the process of the child program
     */
    private static Process getProcess() {
        Process process = null;
        try {
            process = new ProcessBuilder("java", "-jar", "act3.child.jar").start();
        } catch (IOException e) {
            System.out.println("No s'ha pogut crear el procés fill");
            System.exit(1);
        }
        return process;
    }

    /**
     * Reads the output from the given Process and prints it to the console.
     * This method reads the input stream of the Process and prints the lines
     * to the console using the format specified by the constants CHILD_PREFIX
     * and PREFIX.
     * <p>
     * It waits for the process to complete and then prints the last line.
     *
     * @param process the Process object from which to read the output
     */
    private static void readFrom(Process process) {
        try (var reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

            System.out.println(CHILD_PREFIX + reader.readLine());
            System.out.println(CHILD_PREFIX + reader.readLine());

            process.waitFor();

            System.out.println(PREFIX + RECEIVED_NOTIFICATION + '"' + reader.readLine() + '"');

        } catch (Exception ignored) {
            System.out.println("No s'ha pogut llegir el missatge del procés fill");
            System.exit(1);
        }
    }

    /**
     * Writes a message to the input stream of the given Process.
     * This method writes the message specified by the constant SALUTATION
     * to the input stream of the Process.
     *
     * @param process the Process object to which the message should be written
     */
    private static void writeTo(Process process) {
        try (var writer = process.getOutputStream()) {
            writer.write(SALUTATION.getBytes());
            writer.flush();
        } catch (IOException ignored) {
            System.out.println("No s'ha pogut enviar el missatge al procés fill");
            System.exit(1);
        }
    }
}
