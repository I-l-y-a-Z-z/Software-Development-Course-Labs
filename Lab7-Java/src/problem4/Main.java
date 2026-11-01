package problem4;

public class Main {
    public static void main(String[] args) {
        demonstrate("Sorted linked list", new MyLinkedList(null));
        demonstrate("Binary search tree", new SearchTree(null));
    }

    private static void demonstrate(String title, NodeList list) {
        System.out.println(title);
        for (String value : new String[]{"D", "B", "F", "A", "C", "E", "G"}) {
            list.addItem(new Node(value));
        }
        System.out.println("Duplicate added: " + list.addItem(new Node("D")));
        list.traverse(list.getRoot());
        for (String value : new String[]{"A", "B", "D"}) {
            System.out.println("Removed " + value + ": " + list.removeItem(new Node(value)));
        }
        System.out.println("After removal:");
        list.traverse(list.getRoot());
    }
}
