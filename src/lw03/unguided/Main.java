package lw03.unguided;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Set<String> registeredStudents = new HashSet<>();

        Scanner yusman = new Scanner(
            Main.class.getResourceAsStream("registrations.txt")
        );

        while (yusman.hasNextLine()) {
            String id = yusman.nextLine().trim();
            if (!id.isEmpty()) {
                registeredStudents.add(id); 
            }
        }

        yusman.close();

        Set<String> checkedIn = new HashSet<>();
        int rejectedAttempts = 0;

        Scanner yusman2 = new Scanner(
            Main.class.getResourceAsStream("checkins.txt")
        );

        System.out.println("===== Event Check-In Results =====");

        while (yusman2.hasNextLine()) {
            String id = yusman2.nextLine().trim();
            if (id.isEmpty()) {
                continue;
            }

            if (!registeredStudents.contains(id)) {
                System.out.println(id + ": Rejected (not registered)");
                rejectedAttempts++;

            } else if (checkedIn.contains(id)) {
                System.out.println(id + ": Rejected (already checked in)");
                rejectedAttempts++;

            } else {
                checkedIn.add(id);
                System.out.println(id + ": Checked in");
            }
        }

        yusman2.close();

        int registeredCount = registeredStudents.size();
        int successfulCheckIns = checkedIn.size();
        int absentStudents = registeredCount - successfulCheckIns;

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registeredCount);
        System.out.println("Successful check-ins: " + successfulCheckIns);
        System.out.println("Absent students: " + absentStudents);
        System.out.println("Rejected attempts: " + rejectedAttempts);
        
    }
}