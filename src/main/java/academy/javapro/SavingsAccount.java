package academy.javapro;

/**
 * Project: Banking System
 * Class: SavingsAccount.java
 * Author: MEHMET SOYDAN
 * Date: 03/19/2025
 * This class represents a savings account with interest calculation and minimum balance requirements.
 */
public class SavingsAccount extends Account {
    private final double interestRate;
    private static final double MIN_BALANCE = 100.0; // Minimum balance requirement

    /**
     * Constructor for creating a new savings account.
     *
     * @param accountNumber   The account number
     * @param customerName    The name of the account holder
     * @param initialBalance  The initial balance
     * @param interestRate    The annual interest rate (%)
     */
    public SavingsAccount(String accountNumber, String customerName, double initialBalance, double interestRate) {
        super(accountNumber, customerName, initialBalance);
        this.interestRate = interestRate;
    }

    /**
     * Calculates the interest amount based on the current balance.
     *
     * @return The calculated interest amount
     */
    public double calculateInterest() {
        return getBalance() * (interestRate / 100);
    }

    /**
     * Applies the calculated interest to the account balance.
     */
    public void applyInterest() {
        double interest = calculateInterest();
        setBalance(getBalance() + interest);
        logTransaction("INTEREST", interest);
        System.out.println("Interest applied: $" + interest);
    }

    /**
     * Overrides the withdraw method to enforce the minimum balance requirement.
     *
     * @param amount The amount to withdraw
     */
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }

        if (getBalance() - amount < MIN_BALANCE) {
            System.out.println("Cannot withdraw $" + amount + ". Minimum balance of $" + MIN_BALANCE + " must be maintained.");
        } else {
            setBalance(getBalance() - amount);
            logTransaction("WITHDRAWAL", amount);
            System.out.println("Withdrew $" + amount + " from savings account.");
        }
    }

    /**
     * Overrides the displayInfo method to include savings account-specific details.
     */
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Account Type: Savings Account");
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Minimum Balance Requirement: $" + MIN_BALANCE);
    }
}
