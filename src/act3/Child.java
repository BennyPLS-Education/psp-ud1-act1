package act3;

import java.util.Scanner;

public class Child {

    private final static String PREFIX = "Fill: ";
    private final static String SALUTATION = "Salutacions de part del fill";
    private final static String RECEIVED_NOTIFICATION = "rep missatge del pare ";
    private final static String SEND_NOTIFICATION = "envia missatge al pare";
    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {
            var input = scanner.nextLine();
            System.out.println(PREFIX + RECEIVED_NOTIFICATION + '"' + input + '"');
        }

        System.out.println(PREFIX + SEND_NOTIFICATION);
        System.out.println(SALUTATION);
    }
}
