package main;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class Tests {
    private static void assertFileContent(String name, String expectedContent) {
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(name));
            String curr;
            while ((curr = br.readLine()) != null) {
                content.append(curr);
                content.append("\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        int length = content.length();
        if (length > 0) content.deleteCharAt(length - 1);
        assertEquals(expectedContent, content.toString());
    }

    @Test
    public void du() {
        ConsoleDu.main(new String[]{"files", "files\\1.xlsx", "1234", "-h"});
        assertFileContent("out\\logs.txt", "files: 22,6 MB\n1.xlsx: 11,7 KB\n" +
                "File C:\\Users\\bober\\IdeaProjects\\ConsoleDu\\1234 doesn't exist.");

        ConsoleDu.main(new String[]{"files\\Irodov.pdf", "files\\Kalimba.mp3", "files\\nothingIsHere", "--si", "-c"});
        assertFileContent("out\\logs.txt", "File C:\\Users\\bober\\IdeaProjects\\ConsoleDu\\files\\nothingIsHere " +
                "doesn't exist.\nTotal space: 23693,2");

        ConsoleDu.main(new String[]{"-h", "--si", "files\\Irodov.pdf", "files\\Kalimba.mp3", "files\\1.xlsx"});
        assertFileContent("out\\logs.txt", "Irodov.pdf: 15,3 MB\nKalimba.mp3: 8,4 MB\n1.xlsx: 12,0 KB");

        ConsoleDu.main(new String[]{});
        assertFileContent("out\\logs.txt", "");
    }
}
