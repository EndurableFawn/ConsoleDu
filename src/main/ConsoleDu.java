package main;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class ConsoleDu {
    public static int du(String[] args, boolean c, boolean h, double base) {
        boolean check = true;
        File outputFile = new File("out\\logs.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
            if (c) {
                long res = 0;
                for (String elem : args) {
                    if (elem.equals("--si") || elem.equals("-c") || elem.equals("-h")) continue;
                    File file = new File(elem);
                    if (file.exists()) res += getLength(file);
                    else {
                        check = false;
                        System.out.println("File " + file.getAbsolutePath() + " doesn't exist.");
                        bw.write("File " + file.getAbsolutePath() + " doesn't exist.");
                        bw.newLine();
                    }

                }
                if (h) {
                    System.out.print("Total space: ");
                    bw.write("Total space: ");
                    human(res, base, bw);
                } else {
                    System.out.println("Total space: " + String.format("%.1f", res / base));
                    bw.write("Total space: " + String.format("%.1f", res / base));
                    bw.newLine();
                }
            } else {
                for (String elem : args) {
                    if (elem.equals("--si") || elem.equals("-c") || elem.equals("-h")) continue;
                    File file = new File(elem);
                    if (file.exists()) {
                        if (h) {
                            System.out.print(file.getName() + ": ");
                            bw.write(file.getName() + ": ");
                            human(getLength(file), base, bw);
                        } else {
                            System.out.println(file.getName() + ": " +
                                    String.format("%.1f", getLength(file) / base));
                            bw.write(file.getName() + ": " +
                                    String.format("%.1f", getLength(file) / base));
                            bw.newLine();
                        }

                    } else {
                        check = false;
                        System.out.println("File " + file.getAbsolutePath() + " doesn't exist.");
                        bw.write("File " + file.getAbsolutePath() + " doesn't exist.");
                        bw.newLine();
                    }

                }
            }
            bw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return check ? 0 : 1;
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

    private static void human(long length, double base, BufferedWriter bw) {
        double len = (double) length;
        List<String> dimensions = Arrays.asList("B", "KB", "MB", "GB", "TB");
        String dim = "B";
        for (int i = 1; len >= base; i++) {
            len /= base;
            dim = dimensions.get(i);
        }
        System.out.print(String.format("%.1f", len) + " " + dim + "\n");
        try {
            bw.write(String.format("%.1f", len) + " " + dim);
            bw.newLine();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}