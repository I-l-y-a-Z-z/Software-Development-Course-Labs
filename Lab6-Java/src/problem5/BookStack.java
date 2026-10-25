package problem5;

import java.util.LinkedList;

public class BookStack {
    private LinkedList<Book> stack;

    public BookStack(){
        stack = new LinkedList<>();
    }

    public void push(Book book){
        stack.addFirst(book);
    }

    public Book pop(){
        if (isEmpty()){
            return null;
        }
        return stack.removeFirst();
    }

    public Book peek(){
        if (isEmpty()){
            return null;
        }
        return stack.getFirst();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public void display(){
        System.out.println("Stack contents (top to bottom):");
        for (Book book : stack){
            System.out.println("- " + book);
        }
    }
}
