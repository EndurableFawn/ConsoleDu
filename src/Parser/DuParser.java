package Parser;

import main.ConsoleDu;

import java.util.ArrayList;
import java.util.List;

public class DuParser {
    public static void main(String[] args) {
        boolean c = false;
        boolean h = false;
        double base = 1024.0;
        List<String> res = new ArrayList<>();
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
                default:
                    res.add(elem);
                    break;

            }
        }
        ConsoleDu printer = new ConsoleDu(c, h, base, res);
        printer.du();
    }
}
