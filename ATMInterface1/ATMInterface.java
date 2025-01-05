class BankAccount 
{
    private double balance;
    public BankAccount(double initialBalance)
     {
        this.balance = initialBalance;
    }

    public double getBalance() 
    {
        return balance;
    }

    public void deposit(double amount)
     {
        if (amount > 0) 
        {
            balance += amount;
            System.out.println("Deposit successful! Your new balance is: $" + balance);
        } else
         {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
        }
    }

    public void withdraw(double amount)
     {
        if (amount > 0 && amount <= balance)
         {
            balance -= amount;
            System.out.println("Withdrawal successful! Your new balance is: $" + balance);
        } else if (amount > balance) 
        {
            System.out.println("Insufficient funds. Your balance is: $" + balance);
        } else
         {
            System.out.println("Invalid withdrawal amount. Please enter a positive value.");
        }
    }
}

class ATM 
{
    private BankAccount bankAccount;
    public ATM(BankAccount account) 
    {
        this.bankAccount = account;
    }

    public void showMenu() 
    {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int choice;

        do 
        {
            
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice)
             {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private void checkBalance() 
    {
        System.out.println("Your current balance is: $" + bankAccount.getBalance());
    }

    private void deposit(double amount)
    {
        bankAccount.deposit(amount);
    }

    private void withdraw(double amount) {
        bankAccount.withdraw(amount);
    }
}

public class ATMInterface {
    public static void main(String[] args) 
    {
        BankAccount account = new BankAccount(500.00);
        ATM atm = new ATM(account);
        atm.showMenu();
    }
}