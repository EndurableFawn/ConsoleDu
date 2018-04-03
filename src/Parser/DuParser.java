package Parser;

import main.ConsoleDu;

public class DuParser {
    public static void main(String[] args) {
        boolean c = false;
        boolean h = false;
        double base = 1024.0;
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
        ConsoleDu.du(args, c, h, base);
    }
}
