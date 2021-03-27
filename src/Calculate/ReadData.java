package Calculate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadData {
    private static String line;

    private static void read() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            line = reader.readLine();
        } catch (IOException e) {
        }

    }

    public static String getLine() {
        read();
        return line;
    }
}
