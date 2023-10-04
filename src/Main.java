import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        int option;

        System.out.println("-------------------------");
        System.out.println("Selecciona una activitat:");
        System.out.println("-------------------------");
        System.out.println("0. Sortir");
        System.out.println("1. Exercici 1");
        System.out.println("2. Exercici 2");
        System.out.println("3. Exercici 3");
        System.out.println("-------------------------");

        option = getNumber();

        switch (option) {
            case 0 -> System.out.println("Adeu!");
            case 2 -> act2.Main.main(args);

            default -> System.out.println("Opció no vàlida");
        }

        if (option != 0) main(args);
    }

    private static int getNumber() {
        var input = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Opció: ");
                return input.nextInt();
            } catch (InputMismatchException e) {
                input.next();
                System.out.println("Introdueix un número vàlid");
            }
        }
    }
}
