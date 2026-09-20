import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<PrintJob> jobs = new ArrayList<>();
        Scanner fileScanner = new Scanner(new File("jobs.txt"));

        while (fileScanner.hasNext()) {
            String type = fileScanner.next();
            String id = fileScanner.next();
            int pages = fileScanner.nextInt();

            if (type.equals("MONO")) {
                jobs.add(new MonoPrint(id, pages));
            } else if (type.equals("COLOUR")) {
                jobs.add(new ColourPrint(id, pages));
            }
        }
        fileScanner.close();

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}