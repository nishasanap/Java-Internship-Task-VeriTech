import java.util.Scanner;

// User class to represent each user of the ATM
class User {
    private String userID;
    private String userPIN;
    private double accountBalance;

    // Constructor to initialize user attributes
    public User(String userID, String userPIN, double accountBalance) {
        this.userID = userID;
        this.userPIN = userPIN;
        this.accountBalance = accountBalance;
    }

    // Getter methods for user attributes
    public String getUserID() {
        return userID;
    }

    public String getUserPIN() {
        return userPIN;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    // Setter method for updating account balance
    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}

// ATM class encapsulating ATM functionalities
class ATM {
    private User currentUser;

    // Method to authenticate the user
    public void authenticateUser(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String userPIN = scanner.nextLine();

        if (userID.equals(user.getUserID()) && userPIN.equals(user.getUserPIN())) {
            currentUser = user;
            System.out.println("Authentication successful.");
        } else {
            System.out.println("Invalid user ID or PIN. Please try again.");
        }
    }

    // Method to check account balance
    public void checkBalance() {
        if (currentUser != null) {
            System.out.println("Your account balance is: $" + currentUser.getAccountBalance());
        } else {
            System.out.println("Please authenticate first.");
        }
    }

    // Method to withdraw money from the account
    public void withdraw(double amount) {
        if (currentUser != null) {
            if (amount > 0 && amount <= currentUser.getAccountBalance()) {
                currentUser.setAccountBalance(currentUser.getAccountBalance() - amount);
                System.out.println("Withdrawal successful. Remaining balance: $" + currentUser.getAccountBalance());
            } else {
                System.out.println("Invalid amount or insufficient funds.");
            }
        } else {
            System.out.println("Please authenticate first.");
        }
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        if (currentUser != null) {
            if (amount > 0) {
                currentUser.setAccountBalance(currentUser.getAccountBalance() + amount);
                System.out.println("Deposit successful. Updated balance: $" + currentUser.getAccountBalance());
            } else {
                System.out.println("Invalid amount.");
            }
        } else {
            System.out.println("Please authenticate first.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Creating a sample user
        User user = new User("123456", "7890", 1000.00);

        // Creating an instance of ATM
        ATM atm = new ATM();

        // Authenticating the user
        atm.authenticateUser(user);

        // Simple user interface
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        // Exiting the program
        System.out.println("Thank you for using our ATM.");
    }
}
