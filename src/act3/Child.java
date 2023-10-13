package act3;

import java.util.Scanner;

public class Child {

    private final static String PREFIX = "Fill: ";
    private final static String SALUTATION = "Salutacions de part del fill";
    private final static String RECEIVED_NOTIFICATION = "rep missatge del pare ";
    private final static String SEND_NOTIFICATION = "envia missatge al pare";
    /**
     * The main method of the application.
     * <p>
     * This method prompts the user for input, receives the input, and prints a notification with the received input.
     * It then prints a send notification and a salutation.
     */
    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {
            var input = scanner.nextLine();
            System.out.println(PREFIX + RECEIVED_NOTIFICATION + '"' + input + '"');
        }

        System.out.println(PREFIX + SEND_NOTIFICATION);
        System.out.println(SALUTATION);
    }
}
