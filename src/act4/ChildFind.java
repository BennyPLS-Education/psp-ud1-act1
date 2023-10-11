package act4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ChildFind {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("S'ha d'introduir un argument");
            System.exit(1);
        }
        var path = args[0];

        ProcessBuilder pb = getProcessBuilder(path);

        Process command = null;
        try {
            command = pb.start();
        } catch (IOException e) {
            System.out.println("No s'ha pogut crear el procés fill");
            System.exit(1);
        }

        var in = new BufferedReader(new InputStreamReader(command.getInputStream()));

        String line = null;
        while (true) {
            try {
                if ((line = in.readLine()) == null) break;
            } catch (IOException e) {
                System.out.println("No s'ha pogut llegir el procés fill");
            }
            System.out.println(line);
        }
    }

    private static ProcessBuilder getProcessBuilder(String path) {
        String text;

        try (var scanner = new Scanner(System.in)) {
            text = scanner.nextLine();
        }

        switch (System.getProperty("os.name").toLowerCase()) {
            case "linux" -> {
                return new ProcessBuilder("grep", "-i", text, path);
            }
            case "windows" -> {
                return new ProcessBuilder("find", "/i", text, path);
            }
            default -> {
                System.out.println("Sistema operatiu no suportat");
                System.exit(1);
            }
        }

        return null;
    }
}
