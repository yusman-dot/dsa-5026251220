package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<WashService> jobs = new ArrayList<>();
        Scanner yusman = new Scanner(new File("washes.txt"));

        while (yusman.hasNext()) {
            String type = yusman.next();
            String id = yusman.next();
            int days = yusman.nextInt();

            if (type.equals("CAR")) {
                jobs.add(new CarWash(id, days));
            } else if (type.equals("MOTORCYCLE")) {
                jobs.add(new MotorcycleWash(id, days));
            }
        }
        yusman.close();

        for (WashService job : jobs) {
            System.out.println(job.summary());
        }
    }
}
