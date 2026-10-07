package lw03.prelab;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        problem1();
        problem2();
        problem3();
    }

    public static void problem1() {
        List<String> playlist = new ArrayList<>();

        Scanner yusman = new Scanner(
            Main.class.getResourceAsStream("playlist.txt")
        );

        while (yusman.hasNextLine()) {
            String line = yusman.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            if (line.startsWith("ADD ")) {
                String song = line.substring(4);
                playlist.add(song);

            } else if (line.startsWith("INSERT ")) {
                String[] parts = line.split(" ", 3);
                int index = Integer.parseInt(parts[1]);
                String song = parts[2];
                playlist.add(index, song);

            } else if (line.startsWith("REMOVE ")) {
                String song = line.substring(7);
                playlist.remove(song); // removes the first occurrence
            }
        }

        yusman.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    // Problem 2: Unique participants managed with a Set<String>
    public static void problem2() {
        Set<String> participants = new LinkedHashSet<>(); // keeps first-seen order
        int duplicateCount = 0;

        Scanner yusman = new Scanner(
            Main.class.getResourceAsStream("participants.txt")
        );

        while (yusman.hasNextLine()) {
            String name = yusman.nextLine().trim();
            if (name.isEmpty()) {
                continue;
            }

            if (participants.contains(name)) {
                duplicateCount++;
            } else {
                participants.add(name);
            }
        }

        yusman.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());

        int number = 1;
        for (String name : participants) {
            System.out.println(number + ". " + name);
            number++;
        }

        System.out.println("Duplicate registrations: " + duplicateCount);
    }

    // Problem 3: Inventory stock managed with a Map<String, Integer>
    public static void problem3() {
        Map<String, Integer> inventory = new LinkedHashMap<>(); // keeps first-seen order
        int failedSales = 0;

        Scanner yusman = new Scanner(
            Main.class.getResourceAsStream("inventory.txt")
        );

        while (yusman.hasNext()) {
            String type = yusman.next();
            String product = yusman.next();
            int quantity = Integer.parseInt(yusman.next());

            if (type.equals("ADD")) {
                if (inventory.containsKey(product)) {
                    int current = inventory.get(product);
                    inventory.put(product, current + quantity);
                } else {
                    inventory.put(product, quantity);
                }

            } else if (type.equals("SELL")) {
                if (inventory.containsKey(product) && inventory.get(product) >= quantity) {
                    int current = inventory.get(product);
                    inventory.put(product, current - quantity);
                } else {
                    failedSales++;
                }
            }
        }

        yusman.close();

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
        
    }
}
