package act4;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChildDir {
    public static void main(String[] args) {
        Process process = null;
        try {
            switch (System.getProperty("os.name")) {
                case "Linux" -> process = new ProcessBuilder("ls", "-l", System.getProperty("user.home")).start();
                case "Windows" -> process = new ProcessBuilder("dir", System.getProperty("user.home")).start();
                default -> {
                    System.out.println("Sistema operatiu no suportat");
                    System.exit(1);
                }
            }
        } catch (IOException e) {
            System.out.println("No s'ha pogut crear el procés fill (Command 'dir')");
            System.exit(1);
        }

        try {
            process.waitFor();
        } catch (InterruptedException e) {
            System.out.println("El procés fill ha estat interromput");
            System.exit(1);
        }

        if (process.exitValue() != 0) {
            System.out.println("El procés fill ha acabat amb un error");
            System.exit(1);
        }

        String[] lines = new String[0];

        try (var reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            lines = reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            System.out.println("No s'ha pogut llegir el procés fill");
            System.exit(1);
        }

        try (var writer = new FileWriter("output.txt")) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("No s'ha pogut escriure al fitxer 'output.txt'");
            System.exit(1);
        }

        System.out.println("Fitxer creat");
    }
}
