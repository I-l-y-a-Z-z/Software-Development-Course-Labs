package problem4;

public class SearchTree implements NodeList {
    private ListItem root;

    public SearchTree(ListItem root) { this.root = root; }
    public ListItem getRoot() { return root; }

    public boolean addItem(ListItem item) {
        if (item == null) return false;
        if (root == null) { root = item; return true; }
        ListItem current = root;
        while (true) {
            int comparison = current.compareTo(item);
            if (comparison == 0) return false;
            if (comparison > 0) {
                if (current.previous() == null) { current.setPrevious(item); return true; }
                current = current.previous();
            } else {
                if (current.next() == null) { current.setNext(item); return true; }
                current = current.next();
            }
        }
    }

    public boolean removeItem(ListItem item) {
        if (item == null) return false;
        ListItem current = root;
        ListItem parent = null;
        while (current != null) {
            int comparison = current.compareTo(item);
            if (comparison == 0) { performRemoval(current, parent); return true; }
            parent = current;
            current = comparison > 0 ? current.previous() : current.next();
        }
        return false;
    }

    private void performRemoval(ListItem item, ListItem parent) {
        if (item.previous() != null && item.next() != null) {
            ListItem successorParent = item;
            ListItem successor = item.next();
            while (successor.previous() != null) {
                successorParent = successor;
                successor = successor.previous();
            }
            item.setValue(successor.getValue());
            performRemoval(successor, successorParent);
            return;
        }
        ListItem replacement = item.previous() != null ? item.previous() : item.next();
        if (parent == null) root = replacement;
        else if (parent.previous() == item) parent.setPrevious(replacement);
        else parent.setNext(replacement);
        item.setPrevious(null);
        item.setNext(null);
    }

    public void traverse(ListItem root) {
        if (root == null) return;
        traverse(root.previous());
        System.out.println(root.getValue());
        traverse(root.next());
    }
}
