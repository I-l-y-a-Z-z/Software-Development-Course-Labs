package problem2;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Customer methods ===");
        Customer customer = new Customer("Amine");
        System.out.println("Customer: " + customer.getName());
        System.out.println("Initial transactions: " + customer.getTransactions());

        customer.addTransaction(200.00);
        customer.addTransaction(-50.00);
        System.out.println("Transactions after credit and debit: " + customer.getTransactions());

        System.out.println("\n=== Bank methods ===");
        Bank bank = new Bank("Course Bank");
        System.out.println("Bank: " + bank.getName());
        System.out.println("Add Ilyas: " + bank.addCustomer("Ilyas"));
        System.out.println("Add Youness: " + bank.addCustomer("Youness"));
        System.out.println("Add duplicate ILYAS: " + bank.addCustomer("ILYAS"));

        System.out.println("Credit Ilyas: " + bank.addTransaction("Ilyas", 500.00));
        System.out.println("Debit Ilyas: " + bank.addTransaction("Ilyas", -125.50));
        System.out.println("Credit Youness: " + bank.addTransaction("Youness", 300.00));
        System.out.println("Transaction for unknown customer: " + bank.addTransaction("Unknown", 100.00));

        System.out.println("\n=== Statements ===");
        bank.printStatement("Ilyas");
        System.out.println();
        bank.printStatement("Youness");
        System.out.println("Statement for unknown customer: " + bank.printStatement("Unknown"));
    }
}
