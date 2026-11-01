package problem4;

public class MyLinkedList implements NodeList {
    private ListItem root;

    public MyLinkedList(ListItem root) { this.root = root; }
    public ListItem getRoot() { return root; }

    public boolean addItem(ListItem item) {
        if (item == null) return false;
        ListItem current = root;
        ListItem previous = null;
        while (current != null && current.compareTo(item) < 0) {
            previous = current;
            current = current.next();
        }
        if (current != null && current.compareTo(item) == 0) return false;
        item.setPrevious(previous);
        item.setNext(current);
        if (previous == null) root = item;
        else previous.setNext(item);
        if (current != null) current.setPrevious(item);
        return true;
    }

    public boolean removeItem(ListItem item) {
        if (item == null) return false;
        ListItem current = root;
        while (current != null) {
            int comparison = current.compareTo(item);
            if (comparison > 0) return false;
            if (comparison == 0) {
                if (current.previous() == null) root = current.next();
                else current.previous().setNext(current.next());
                if (current.next() != null) current.next().setPrevious(current.previous());
                current.setNext(null);
                current.setPrevious(null);
                return true;
            }
            current = current.next();
        }
        return false;
    }

    public void traverse(ListItem root) {
        if (root == null) System.out.println("The list is empty");
        for (ListItem current = root; current != null; current = current.next()) {
            System.out.println(current.getValue());
        }
    }
}
