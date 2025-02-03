import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Token {
    private String name;
    private double totalSupply;

    public Token(String name, double totalSupply) {
        this.name = name;
        this.totalSupply = totalSupply;
    }

    public String getName() {
        return name;
    }

    public double getTotalSupply() {
        return totalSupply;
    }
}

class Account {
    private String accountId;
    private double balance;

    public Account(String accountId) {
        this.accountId = accountId;
        this.balance = 0.0;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void credit(double amount) {
        balance += amount;
    }

    public boolean debit(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class TokenManager {
    private Token token;
    private Map<String, Account> accounts;

    public TokenManager(Token token) {
        this.token = token;
        this.accounts = new HashMap<>();
    }

    public void createAccount(String accountId) {
        if (!accounts.containsKey(accountId)) {
            accounts.put(accountId, new Account(accountId));
            System.out.println("Account created: " + accountId);
        } else {
            System.out.println("Account already exists: " + accountId);
        }
    }

    public void transfer(String fromAccountId, String toAccountId, double amount) {
        Account fromAccount = accounts.get(fromAccountId);
        Account toAccount = accounts.get(toAccountId);

        if (fromAccount == null || toAccount == null) {
            System.out.println("One of the accounts does not exist.");
            return;
        }

        if (fromAccount.debit(amount)) {
            toAccount.credit(amount);
            System.out.println("Transferred " + amount + " tokens from " + fromAccountId + " to " + toAccountId);
        } else {
            System.out.println("Insufficient balance in account: " + fromAccountId);
        }
    }

    public void displayBalance(String accountId) {
        Account account = accounts.get(accountId);
        if (account != null) {
            System.out.println("Balance of " + accountId + ": " + account.getBalance() + " tokens");
        } else {
            System.out.println("Account does not exist: " + accountId);
        }
    }
}

public class FungibleTokenSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Token token = new Token("MyToken", 1000000); // Create a token with a total supply
        TokenManager tokenManager = new TokenManager(token);

        while (true) {
            System.out.println("1. Create Account");
            System.out.println("2. Transfer Tokens");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter account ID: ");
                    String accountId = scanner.nextLine();
                    tokenManager.createAccount(accountId);
                    break;
                case 2:
                    System.out.print("Enter from account ID: ");
                    String fromAccountId = scanner.nextLine();
                    System.out.print("Enter to account ID: ");
                    String toAccountId = scanner.nextLine();
                    System.out.print("Enter amount to transfer: ");
                    double amount = scanner.nextDouble();
                    tokenManager.transfer(fromAccountId, toAccountId, amount);
                    break;
                case 3:
                    System.out.print("Enter account ID to check balance: ");
                    String balanceAccountId = scanner.nextLine();
                    tokenManager.displayBalance(balanceAccountId);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}