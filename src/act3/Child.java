package act3;

import java.util.Scanner;

public class Child {
    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {
            var input = scanner.nextLine();

            input = input.toUpperCase().trim();
            input = input.replaceAll("[AEIOU]", "_");

            System.out.println("El Fill diu: " + input);
        }
    }
}
