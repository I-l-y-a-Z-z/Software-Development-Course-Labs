package problem4;

public class Node extends ListItem {
    public Node(Object value) { super(value); }

    @Override
    ListItem next() { return rightLink; }
    @Override
    ListItem setNext(ListItem item) { rightLink = item; return rightLink; }
    @Override
    ListItem previous() { return leftLink; }
    @Override
    ListItem setPrevious(ListItem item) { leftLink = item; return leftLink; }
    @Override
    int compareTo(ListItem item) {
        return value.toString().compareTo(item.getValue().toString());
    }
}
