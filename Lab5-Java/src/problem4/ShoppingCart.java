package problem4;

public class ShoppingCart {
    private Item[] cart;
    private int itemCount;
    private int capacity;
    private double totalPrice;

    public ShoppingCart(){
        capacity = 5;
        cart = new Item[capacity];
        itemCount = 0;
        totalPrice = 0;
    }

    private void increaseSize(){
        capacity = capacity + capacity / 2;
        Item[] newCart = new Item[capacity];
        for (int i = 0; i < itemCount; i++){
            newCart[i] = cart[i];
        }
        cart = newCart;
    }

    public void addToCart(String name, double price, int quantity){
        if (itemCount == capacity){
            increaseSize();
        }
        cart[itemCount] = new Item(name, price, quantity);
        itemCount++;
        totalPrice += price * quantity;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public String toString(){
        String result = "Shopping cart:\nName\tPrice\tQuantity\tTotal\n";
        for (int i = 0; i < itemCount; i++){
            result += cart[i] + "\n";
        }
        result += String.format("Total price: %.2f", totalPrice);
        return result;
    }
}
