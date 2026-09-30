package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foodStock = new LinkedList<>();
        LinkedList<String[]> drinkStock = new LinkedList<>();
        LinkedList<String[]> successfulOrders = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        foodStock.add(new String[]{"Bakso", "2"});
        foodStock.add(new String[]{"Sate", "1"});
        foodStock.add(new String[]{"Soto", "2"});

        drinkStock.add(new String[]{"EsTeh", "4"});
        drinkStock.add(new String[]{"EsJeruk", "2"});

        Scanner yusman = new Scanner(
            Main.class.getResourceAsStream("orders.txt")
        );

        while (yusman.hasNext()) {
            String[] order = new String[4];
            order[0] = yusman.next();
            order[1] = yusman.next();
            order[2] = yusman.next();
            order[3] = yusman.next();
            orders.add(order);
        }

        yusman.close();

        queue.addAll(orders);

        while (!queue.isEmpty()) {
            String[] order = queue.poll();
            String food = order[1];
            String drink = order[2];

            boolean foodAvailable = true;
            boolean drinkAvailable = true;

            String[] foodRecord = null;
            if (!food.equals("-")) {
                for (String[] data : foodStock) {
                    if (data[0].equals(food)) { foodRecord = data; break; }
                }
                if (Integer.parseInt(foodRecord[1]) <= 0) foodAvailable = false;
            }

            String[] drinkRecord = null;
            if (!drink.equals("-")) {
                for (String[] data : drinkStock) {
                    if (data[0].equals(drink)) { drinkRecord = data; break; }
                }
                if (Integer.parseInt(drinkRecord[1]) <= 0) drinkAvailable = false;
            }

            if (foodAvailable && drinkAvailable) {
                if (foodRecord != null) foodRecord[1] = String.valueOf(Integer.parseInt(foodRecord[1]) - 1);
                if (drinkRecord != null) drinkRecord[1] = String.valueOf(Integer.parseInt(drinkRecord[1]) - 1);
                successfulOrders.add(order);
            } else {
                failed.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] o : successfulOrders) System.out.println(o[0]+" "+o[1]+" "+o[2]+" "+o[3]);

        System.out.println("=== Remaining Food Stock ===");
        for (String[] f : foodStock) System.out.println(f[0]+" : "+f[1]);

        System.out.println("=== Remaining Drink Stock ===");
        for (String[] d : drinkStock) System.out.println(d[0]+" : "+d[1]);

        System.out.println("=== Failed Orders ===");
        while (!failed.isEmpty()) {
            String[] o = failed.pop();
            System.out.println(o[0]+" "+o[1]+" "+o[2]+" "+o[3]);
        }
    }
}