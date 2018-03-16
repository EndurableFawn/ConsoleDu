package main;

import java.io.File;

public class ConsoleDu {
    public static void main(String[] args) {
        for (String elem : args) {
            File file = new File(elem);
            if (file.exists()) {
                System.out.println(file.getName() + ": " + getLength(file));
            } else {
                System.out.println("File " + file.getName() + " doesn't exist.");
            }

        }
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
}