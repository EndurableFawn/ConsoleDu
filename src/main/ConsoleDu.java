package main;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ConsoleDu {
    private static boolean c = false;
    private static boolean h = false;
    private static double base = 1024.0;
    private static String dim = "";

    public static void main(String[] args) {
        boolean check = true;
        for (String elem : args) {
            switch (elem) {
                case "--si":
                    base = 1000.0;
                    break;
                case "-c":
                    c = true;
                    break;
                case "-h":
                    h = true;
                    break;
            }
        }
        if (c) {
            long res = 0;
            for (String elem : args) {
                if (elem.equals("--si") || elem.equals("-c") || elem.equals("-h")) continue;
                File file = new File(elem);
                if (file.exists()) res += getLength(file);
                else {
                    check = false;
                    System.out.println("File " + file.getName() + " doesn't exist.");
                }

            }
            if (h) System.out.println("Total space: " +
                    String.format("%.1f", human(res)) + " " + dim);
            else System.out.println("Total space: " + String.format("%.1f", res / base));
        } else {
            for (String elem : args) {
                if (elem.equals("--si") || elem.equals("-c") || elem.equals("-h")) continue;
                File file = new File(elem);
                if (file.exists()) {
                    if (h) System.out.println(file.getName() + ": " +
                            String.format("%.1f", human(getLength(file))) + " " + dim);
                    else System.out.println(file.getName() + ": " +
                            String.format("%.1f", getLength(file) / base));
                } else {
                    System.out.println("File " + file.getAbsolutePath() + " doesn't exist.");
                    check = false;
                }

            }
        }
        System.exit(check ? 0 : 1);
    }

    private static long getLength(File file) {
        long length = 0;
        if (file.listFiles() != null) {
            for (File f : file.listFiles()) {
                length += getLength(f);
            }
        } else {
            length += file.length();
        }
        return length;
    }

    private static double human(long length) {
        double len = (double) length;
        List<String> dimensions = Arrays.asList("B", "KB", "MB", "GB", "TB");
        dim = "B";
        for (int i = 1; len >= base; i++) {
            len /= base;
            dim = dimensions.get(i);
        }
        return len;
    }
}