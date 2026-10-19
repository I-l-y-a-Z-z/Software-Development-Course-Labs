package problem2;

import java.util.ArrayList;

public class Customer {
    private String name;
    private ArrayList<Double> transactions;

    public Customer(String name){
        this.name = name;
        transactions = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public ArrayList<Double> getTransactions(){
        return transactions;
    }

    public void addTransaction(double amount){
        transactions.add(amount);
    }
}
