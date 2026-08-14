import java.util.ArrayList;
import java.util.List;

public class Account {
    private String owner;
    private double balance;
    private String pin;
    private List<String> transactionHistory;

    public Account(String owner, double openingBalance, String pin) {
        this.owner = owner;
        this.pin = pin;
        this.transactionHistory = new ArrayList<>();

        if (openingBalance < 0) {
            this.balance = 0;
        } else {
            this.balance = openingBalance;
        }

        transactionHistory.add(String.format("Account opened with balance: $%.2f", this.balance));
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public boolean validatePin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        balance += amount;
        System.out.printf("Deposited $%.2f. New balance: $%.2f%n", amount, balance);
        transactionHistory.add(String.format("Deposit:  +$%.2f | Balance: $%.2f", amount, balance));
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds.");
            return;
        }
        balance -= amount;
        System.out.printf("Withdrew $%.2f. New balance: $%.2f%n", amount, balance);
        transactionHistory.add(String.format("Withdraw: -$%.2f | Balance: $%.2f", amount, balance));
    }

    public void printTransactionHistory() {
        System.out.println("\n===== TRANSACTION HISTORY =====");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions on record.");
        } else {
            for (String record : transactionHistory) {
                System.out.println(" • " + record);
            }
        }
    }
}