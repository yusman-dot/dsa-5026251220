

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        LinkedList<String[]> transactions = new LinkedList<>();
        Scanner fileScanner = new Scanner(new File("transactions.txt"));

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split("\\s+"); 
            transactions.add(parts);
        }
        fileScanner.close();

        LinkedList<String[]> customers = new LinkedList<>();
        for (String[] t : transactions) {
            String name = t[0];
            boolean alreadyExists = false;

            for (String[] c : customers) {
                if (c[0].equals(name)) {
                    alreadyExists = true;
                    break;
                }
            }

            if (!alreadyExists) {
                customers.add(new String[]{name, "0"}); 
            }
        }

        Queue<String[]> transactionQueue = new LinkedList<>();
        transactionQueue.addAll(transactions);

        Stack<String[]> failedWithdrawals = new Stack<>();

        while (!transactionQueue.isEmpty()) {
            String[] t = transactionQueue.poll();
            String name = t[0];
            String type = t[1];
            int amount = Integer.parseInt(t[2]);

            String[] customer = null;
            for (String[] c : customers) {
                if (c[0].equals(name)) {
                    customer = c;
                    break;
                }
            }

            int balance = Integer.parseInt(customer[1]);

            if (type.equals("DEPOSIT")) {
                balance += amount;
                customer[1] = String.valueOf(balance);
            } else if (type.equals("WITHDRAW")) {
                if (amount > balance) {
                    failedWithdrawals.push(t);
                } else {
                    balance -= amount;
                    customer[1] = String.valueOf(balance);
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] c : customers) {
            System.out.println(c[0] + " : " + c[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failedWithdrawals.isEmpty()) {
            String[] t = failedWithdrawals.pop();
            System.out.println(t[0] + " " + t[1] + " " + t[2]);
        }
    }
}
