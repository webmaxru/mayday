import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Ekaterina Orlova on 09/10/14.
 */
public class In {

    private Scanner scanner;

    public In(File file) {
        try {
            scanner = new Scanner(file);
        } catch (IOException ioe) {
            System.err.println("Could not open " + file);
        }
    }


    public boolean exists() {
        return scanner != null;
    }

    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    public String readLine() {
        String line;
        try {
            line = scanner.nextLine();
        } catch (Exception e) {
            line = null;
        }
        return line;
    }

}
