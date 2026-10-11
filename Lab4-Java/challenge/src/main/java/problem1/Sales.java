package problem1;
import java.util.Scanner;
public class Sales
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of salespeople: ");
        int salespeople = scan.nextInt();
        int[] sales = new int[salespeople];
        int sum;
        int maxId = 0, maxSale = 0;
        int minId = 0, minSale = Integer.MAX_VALUE;
        for (int i=0; i<sales.length; i++)
        {
            System.out.print("Enter sales for salesperson " + (i+1) + ": ");
            sales[i] = scan.nextInt();
            if (sales[i] > maxSale) {maxSale = sales[i]; maxId = i;}
            if (sales[i] < minSale) {minSale = sales[i]; minId = i;}
        }
        System.out.println("\nSalesperson Sales");
        System.out.println("--------------------");
        sum = 0;
        for (int i=0; i<sales.length; i++)
        {
            System.out.println(" " + (i+1) + " " + sales[i]);
            sum += sales[i];
        }
        System.out.println("\nTotal sales: " + sum);
        System.out.println("\nAverage sale: " + (double) sum/sales.length);
        System.out.println("\nMax sale: " + (maxId+1) + " " + maxSale);
        System.out.println("\nMin sale: " + (minId+1) + " " + minSale);
        System.out.print("\nEnter a value: ");
        int value = scan.nextInt();
        int count = 0;
        for (int i=0; i<sales.length; i++)
        {
            if (sales[i] > value)
            {
                System.out.println(" " + (i+1) + " " + sales[i]);
                count++;
            }
        }
        System.out.println("\nTotal salespeople who exceeded the value: " + count);
    }
}
