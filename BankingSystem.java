import java.util.*;
class Account {
    int accNo;
    String name;
    int password;
    double balance;
    Account(int accNo, String name, int password, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.password = password;
        this.balance = balance;
    }
}
public class BankingSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Account> accounts = new ArrayList<>();
    static ArrayList<String> transactionLog = new ArrayList<>();

    public static void main(String[] args) {
        int choose;
        do {
            System.out.println("Simple Banking System");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("choose: ");
            choose = sc.nextInt();
            switch (choose) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    userLogin();
                    break;
                case 3:
                    System.out.println("BYEEE!");
                    break;
                default:
                    System.out.println("Invalid!!");
            }
        } while (choose != 3);
    }
    static void adminLogin() {
        System.out.print("Enter Admin Password: ");
        int pass = sc.nextInt();
        if (pass != 12345) {
            System.out.println("CHORR!");
            return;
        }
        int ch;
        do {
            System.out.println("\nAdmin Menu");
            System.out.println("1. Create Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. View All Transactions");
            System.out.println("4. Logout");
            System.out.print("Choose: ");
            ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    viewAllAccounts();
                    break;
                case 3:
                    viewAllTransactions();
                    break;
                case 4:
                    System.out.println("Logout..");
                    break;
                default:
                    System.out.println("Invalid!!");
            }
        } while (ch != 4);
    }

    static void createAccount() {
        System.out.println("Enter Account Number: ");
        int accNo = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Name: ");
        String name = sc.nextLine();
        System.out.println("Enter Password: ");
        int pass = sc.nextInt();
        System.out.println("Enter Initial Deposit: ");
        double balance = sc.nextDouble();
        accounts.add(new Account(accNo, name, pass, balance));
        transactionLog.add(accNo + " Account credit " + balance);
        System.out.println("DONE!");
    }
    static void viewAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("Nothing is there...");
        }
        System.out.println(" All Accounts ");
        for (Account a : accounts) {
            System.out.println("AccNo: " + a.accNo + ", Name: " + a.name + ", Balance: " + a.balance);
        }
    }
    static void viewAllTransactions() {
        if (transactionLog.isEmpty()) {
            System.out.println("No transactions yet");
            return;
        }
        System.out.println(" Transaction Logs");
        for (String log : transactionLog) {
            System.out.println(log);
        }
    }
    static void userLogin() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        System.out.print("Enter Password: ");
        int pass = sc.nextInt();
        Account acc = findAccount(accNo, pass);
        if (acc == null) {
            System.out.println("Invalid!");
            return;
        }
        int ch;
        do {
            System.out.println("\n User Menu ");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View My Transactions");
            System.out.println("5. Logout");
            System.out.print("Choose: ");
            ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Your Balance: " + acc.balance);
                    break;
                case 2:
                    deposit(acc);
                    break;
                case 3:
                    withdraw(acc);
                    break;
                case 4:
                    viewUserTransactions(acc.accNo);
                    break;
                case 5:
                    System.out.println("Logged ou...");
                    break;
                default:
                    System.out.println("Invalid!");
            }
        } while (ch != 5);
    }
    static Account findAccount(int accNo, int pass) {
        for (Account a : accounts) {
            if (a.accNo == accNo && a.password == pass) {
                return a;
            }
        }
        return null;
    }
    static void deposit(Account acc) {
        System.out.print("Enter amount to deposit: ");
        double amt = sc.nextDouble();
        sc.nextLine();
        acc.balance += amt;
        transactionLog.add(acc.accNo + " Deposited " + amt + ", New Balance: " + acc.balance);
        System.out.println("Amount deposited");
    }
    static void withdraw(Account acc) {
        System.out.print("Enter amount to withdraw: ");
        double amt = sc.nextDouble();
        sc.nextLine();
        if (amt > acc.balance) {
            System.out.println("Not enough!");
        } else {
            acc.balance -= amt;
            transactionLog.add(acc.accNo + "Withdrawn " + amt + ", New Balance: " + acc.balance);
            System.out.println("Amount withdrawn!");
        }
    }
    static void viewUserTransactions(int accNo) {
        boolean found = false;
        System.out.println("Your Transactions");
        for (String log : transactionLog) {
            if (log.startsWith(String.valueOf(accNo))) {
                System.out.println(log);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No transactions found for your account.");
        }
    }
}

