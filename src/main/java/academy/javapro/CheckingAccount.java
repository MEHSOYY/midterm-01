package academy.javapro;

/**
 * Project: Banking System
 * Class: CheckingAccount.java
 * Author: MEHMET SOYDAN
 * Date: 03/19/2025
 * This class represents a checking account with overdraft protection and transaction fees.
 */
public class CheckingAccount extends Account {
    private double overdraftLimit;
    private static final double TRANSACTION_FEE = 1.50; // Fixed transaction fee for withdrawals

    /**
     * Constructor for creating a new checking account.
     *
     * @param accountNumber   The account number
     * @param customerName    The name of the account holder
     * @param initialBalance  The initial balance
     * @param overdraftLimit  The overdraft limit
     */
    public CheckingAccount(String accountNumber, String customerName, double initialBalance, double overdraftLimit) {
        super(accountNumber, customerName, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    /**
     * Overrides the withdraw method to implement overdraft protection and transaction fees.
     *
     * @param amount The amount to withdraw
     */
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }

        double totalAmount = amount + TRANSACTION_FEE;
        double availableBalance = getBalance() + overdraftLimit;

        if (totalAmount > availableBalance) {
            System.out.println("Withdrawal failed. Exceeds overdraft limit.");
        } else {
            setBalance(getBalance() - totalAmount);
            logTransaction("WITHDRAWAL", amount);
            logTransaction("FEE", TRANSACTION_FEE);
            System.out.println("Withdrew $" + amount + " from checking account.");
            System.out.println("Transaction fee: $" + TRANSACTION_FEE);
            if (getBalance() < 0) {
                System.out.println("Account is in overdraft. Current balance: $" + getBalance());
            }
        }
    }

    /**
     * Updates the overdraft limit.
     *
     * @param newLimit The new overdraft limit
     */
    public void setOverdraftLimit(double newLimit) {
        this.overdraftLimit = newLimit;
        System.out.println("Overdraft limit updated to $" + newLimit);
    }

    /**
     * Overrides the displayInfo method to include checking account-specific details.
     */
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Account Type: Checking Account");
        System.out.println("Overdraft Limit: $" + overdraftLimit);
        System.out.println("Transaction Fee: $" + TRANSACTION_FEE);
    }
}
