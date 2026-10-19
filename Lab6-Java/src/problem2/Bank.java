package problem2;

import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<Customer> customers;

    public Bank(String name){
        this.name = name;
        customers = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public boolean addCustomer(String customerName){
        if (findCustomer(customerName) != null){
            return false;
        }
        customers.add(new Customer(customerName));
        return true;
    }

    public boolean addTransaction(String customerName, double amount){
        Customer customer = findCustomer(customerName);
        if (customer == null){
            return false;
        }
        customer.addTransaction(amount);
        return true;
    }

    public boolean printStatement(String customerName){
        Customer customer = findCustomer(customerName);
        if (customer == null){
            return false;
        }
        System.out.println("Bank: " + name);
        System.out.println("Customer: " + customer.getName());
        for (double amount : customer.getTransactions()){
            System.out.printf("%.2f%n", amount);
        }
        return true;
    }

    private Customer findCustomer(String customerName){
        for (Customer customer : customers){
            if (customer.getName().equalsIgnoreCase(customerName)){
                return customer;
            }
        }
        return null;
    }
}
