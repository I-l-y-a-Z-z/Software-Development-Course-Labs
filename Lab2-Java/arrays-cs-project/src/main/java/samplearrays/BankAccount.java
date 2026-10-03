package samplearrays;

public class BankAccount {

    private String name;
    private double currentBalance;
    //TO-DO: Initialize an Array with 1000 in size that stores Double called 'transactions' to keep track of the user's transactions
    private double[] transactions = new double[1000];
    private int numTransactions = 0;
    public BankAccount(String name, int startingBalance){
        this.name = name;
        this.currentBalance = startingBalance;
    }

    public void deposit(double amount){
        if (amount <= 0) System.out.println("Erreur : Le montant doit être strictement positif.");
        else {
            transactions[numTransactions] = amount;
            this.currentBalance += amount;
            numTransactions++;
        }
    }

    public void withdraw(double amount){
        if (amount <= 0 || this.currentBalance < amount) System.out.println("Erreur : Montant insuffisant ou Valeur négative detectée");
        else {
            transactions[numTransactions] = -amount;
            this.currentBalance -= amount;
            numTransactions++;
        }
    }

    public void displayTransactions(){
        System.out.println("----------Transactions-----------");
        for (int i = 0; transactions[i] != 0 ; i++){
            System.out.println("Transaction " + i + " Value : " + transactions[i]);
        }
        System.out.println("---------------------------------");
    }

    public void displayBalance(){
        System.out.println("Current Balance : " + this.currentBalance);
    }

    public static void main(String[] args) {

        BankAccount john = new BankAccount("John Doe", 100);

        // ----- DO NOT CHANGE -----

        //Testing..
        john.displayBalance();
        john.deposit(0.25);
        john.withdraw(100.50);
        john.withdraw(40.90);
        john.deposit(-90.55);
        john.deposit(3000);
        john.displayTransactions();
        john.displayBalance();

        // ----- DO NOT CHANGE -----

    }

}
