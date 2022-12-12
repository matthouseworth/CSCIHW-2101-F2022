package Project1;
import java.text.BreakIterator;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;

/*
 * This is a Java Doc
 * This is a multi-line comment
 *  
 * Our BankAccount class will represent a bank account
 * It will have a balance and a name
 * It will have methods to deposit and withdraw money
 * It will have a method to print the balance
 * it will have a menu that the user can interact with
 * 
 * 
 */

public class BankAccount {
    private double balance;
    private String name;
    private double interestRate = 0.01;
    private int period = 4; // quarterly


    // Constructor
    public BankAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
    public BankAccount(String name) {
        this.name = name;
        this.balance = 0;
    }
    public BankAccount(double balance){
        this.balance = balance;
        this.name = "No Name";
    }
    // Default Constructor
    // Make a BankAccount without Specifying any parameters
    public BankAccount(){
        this.balance = 0;
        this.name = "No Name";
    }

    // Create getters and setters for balance
    public double getBalance() {
        return balance;
    }

    // Create a method for printing the balance as a String
    public String printBalance() {
        return "Your Balance is: $" + balance;
    }

    // public void setBalance(double balance) {
    //     this.balance = balance;
    // }
    // Set balance is flawed in that we would never
    // be able to actually set the balance. Instead we need 
    // to create methods to deposit and withdraw money

    // Create getters and setters for name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    

    // Create a method to deposit money
    public void deposit(double amount) {
        this.balance += amount;
        // above is the same as this.balance = this.balance + amount;
        // balance is 10,000
        // amount  is 2,000
        // balance = balance + amount
        // 10000 = 10000 + 2000
    }

    // Create a method to withdraw money
    public void withdraw(double amount) {
        this.balance -= amount;
        // above is the same as this.balance = this.balance - amount;
        // balance is 10,000
        // amount  is 2,000
        // balance = balance - amount
        // 10000 = 10000 - 2000
    }

    // Create a method that will subtract a monthly fee from the balance
    // This method will take a double as an argument
    // This method will subtract the fee from the balance
    public void monthlyFee(double fee) {
        withdraw(fee);
    }


    // Create a method that will calculate the interest on the balance using compound interest
    // P(1 + R/n)^(nt) - P 
    // P = Principal
    // R = Rate
    // n = number of times interest is compounded per year (annum)
    // t = number of years
    // This method will take a double as an argument
    // This method will return a double

    // P can be this.balance or getBalance() if you would like to use a getter


    public void calcInterest(double principal, int time, double rate, int annum){
        double amount = principal * Math.pow(1+ (rate/annum), annum*time);
        double roundedamount = Math.round(amount * 100.0) / 100.0;
        double compinterest = amount - principal;
        compinterest = Math.round(compinterest * 100.0) / 100.0;
        System.out.println("Current Rate is: "+rate+"%");
        System.out.println("Current Period is: "+annum+" times per year");
        System.out.println("Selected Time is: "+time+" years");
        System.out.println("Compound Interest after " + time + " years: $" + compinterest);
        System.out.println("Amount after " + time + " years: $" + roundedamount);
    }
    //Overdraft Fee and Warning
    public void overDraft(BankAccount bank, double amount){
        String feeChoice;
        if (bank.balance < 0){
        System.out.println("You have insufficient funds to withdraw $" + amount + ", you have been charged an overdraft fee of $35");
        this.balance = balance -= 35;
        }else if (bank.balance < amount){
            System.out.println("If you complete this transaction, your account will be in the negative, are you sure you want to continue? You will be charged a fee");
            Scanner overDraftFee = new Scanner(System.in);
            feeChoice = overDraftFee.nextLine();
            if (feeChoice == "Yes"){
            System.out.println("Your new balance is " + (balance - (amount + 35)));
            }else if (feeChoice == "No"){
            System.out.println("This transaction has been cancelled");
            }else{
            System.out.println("This is not a valid response");
            }
        }else{
        this.balance = balance -= amount;
        }
    }

    //Transfer Function
    public void transfer(BankAccount bank, double SecondaryAccount, double AmntToTransfer){
        SecondaryAccount = 25000;
        this.balance =+ AmntToTransfer;
        SecondaryAccount =- AmntToTransfer;
        System.out.println("The balance of your bank account is now $" + balance + " and the new value of the Secondary account is now $" + SecondaryAccount);
    }

    //Simple Interest Calc
    public void simpleInterest(double principal, double rate, int time){
        double amount = principal * (1 + (rate + time));
        double roundedamount = Math.round(amount * 100.0) / 100.0;
        double simpleInterest = amount - principal;
        System.out.println("Current Rate is: " + rate + "%");
        System.out.println("Selected Time is: " + time + " years");
        System.out.println("Simple Interest after " + time + "years: $" + simpleInterest);
        System.out.println("Amount after " + time + " years: $" + roundedamount);
    }

    public void createSavingsAccount(double initialDeposit, String interestType, double principal, int years, double interest) {
        String savingsOption;
        String interestType;
        double principal;
        int years;
        double interest;
        double total;
        if (initialDeposit >= 10000) {
            System.out.println("Do you want to create a savings account?");
            Scanner savingsAcc = new Scanner(System.in);
            savingsOption = savingsAcc.nextLine();
            if (savingsOption == "Yes"){
                if (interestType == "compound") {
                    interest = principal * Math.pow(1 + 0.0001 / 4, 4 * years);
                    total = principal + interest;
                    System.out.println(total);
                }else if (interestType == "simple") {
                    interest = principal * 0.001 * years;
                    total = principal + interest;
                    System.out.println(total);
                }else {
                    System.out.println("Invalid interest type. Please choose either 'compound' or 'simple'.");
            }else{
            System.out.println("No savings account will be created");
            }
            }else {
            System.out.println("Initial deposit must be at least 10,000 to create a savings account.");
        }
    }
}

    // Create a method that will print a menu to the user
    // this is called a helper method
    public static int menu(){
        int choice;
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Bank Account Menu");
        System.out.println("--------------------------------");
        System.out.println("1. Deposit Money");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Print Balance");
        System.out.println("4. Calculate Interest");
        System.out.println("0. Exit");
        System.out.println("Please enter your choice: ");
        choice = input.nextInt();
        return choice;
    }
    // Create a method that will interact with the user based on their choice from the menu
    // This method will take a BankAccount object as an argument
    public static void interact(BankAccount account){
        // get the choice from the menu
        int choice = menu();
        // create a scanner object
        Scanner input = new Scanner(System.in);
        // use that choice and run the method associated with that choice
        // in class we learned about recursion and how to use it
        // we will use recursion to keep the menu running until the user chooses to exit
        // This is outside of the scope of this class but it is worth knowing about

        if (choice == 1) {
            System.out.println("How much would you like to deposit?");
            double amount = input.nextDouble();
            account.deposit(amount);
            System.out.println(account.printBalance());
            // interact(account);
        } else if (choice == 2) {
            System.out.println("How much would you like to withdraw?");
            double amount = input.nextDouble();
            account.withdraw(amount);
            System.out.println(account.printBalance());
            // interact(account);
        } else if (choice == 3) {
            System.out.println(account.printBalance());
            // interact(account);
        } else if (choice == 4) {
            System.out.println("What is the time in years to invest (Whole Numbers Only)?");
            int time = input.nextInt();
            // double principal, int time, double rate, int period
            account.calcInterest(account.getBalance(), time, account.interestRate, account.period);

            System.out.println("... Thank you for using the Bank Account Menu. Have a nice day! ...");

        }else if (choice == 0) {
            System.out.println("Thank you for using the Bank Account Menu");
        } else { // this would catch 69420
            System.out.println("Invalid Choice");
            // interact(account);
        }

    }
}
