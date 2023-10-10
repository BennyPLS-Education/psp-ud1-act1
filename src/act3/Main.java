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

    public static void main(String[] args) throws IOException {
        var process = new ProcessBuilder("java", "-jar", "act4.child.jar").start();

        System.out.println(PREFIX + SEND_NOTIFICATION);

        try (var writer = process.getOutputStream()) {
            writer.write(SALUTATION.getBytes());
            writer.flush();
        } catch (IOException ignored) {}

        try (var reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

            System.out.println(CHILD_PREFIX + reader.readLine());
            System.out.println(CHILD_PREFIX + reader.readLine());

            process.waitFor();

            System.out.println(PREFIX + RECEIVED_NOTIFICATION + '"' + reader.readLine() + '"');

        } catch (Exception ignored) {}
    }
}
