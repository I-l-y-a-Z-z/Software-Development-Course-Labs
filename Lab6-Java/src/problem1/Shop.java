package problem1;

import java.util.ArrayList;
import java.util.Scanner;
public class Shop
{
    public static void main (String[] args)
    {
        ArrayList<Item> cart = new ArrayList<>();
        Item item;
        String itemName;
        double itemPrice;
        int quantity;
        Scanner scan = new Scanner(System.in);
        String keepShopping = "y";
        do
        {
            System.out.print ("Enter the name of the item: ");
            itemName = scan.nextLine();
            System.out.print ("Enter the unit price: ");
            itemPrice = scan.nextDouble();
            System.out.print ("Enter the quantity: ");
            quantity = scan.nextInt();
            scan.nextLine();

            item = new Item(itemName, itemPrice, quantity);
            cart.add(item);

            System.out.println("\nShopping cart:");
            System.out.println("Name\tPrice\tQuantity\tTotal");
            double totalPrice = 0;
            for (Item cartItem : cart)
            {
                System.out.println(cartItem);
                totalPrice += cartItem.getPrice() * cartItem.getQuantity();
            }
            System.out.printf("Total price: %.2f%n", totalPrice);

            System.out.print ("Continue shopping (y/n)? ");
            keepShopping = scan.nextLine();
        }
        while (keepShopping.equals("y"));
    }
}
