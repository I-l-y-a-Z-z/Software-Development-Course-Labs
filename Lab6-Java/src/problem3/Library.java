package problem3;

import java.util.ArrayList;

public class Library {
    private int capacity;
    private ArrayList<Document> documents;

    public Library(int capacity){
        this.capacity = capacity;
        documents = new ArrayList<>(capacity);
    }

    public void displayDocuments(){
        for (Document doc : documents){
            System.out.println(doc);
        }
    }

    public boolean add(Document doc){
        if (doc == null || documents.size() >= capacity || documents.contains(doc)){
            return false;
        }
        documents.add(doc);
        return true;
    }

    public boolean delete(Document doc){
        return documents.remove(doc);
    }

    public Document document(int numEnrg){
        for (Document doc : documents){
            if (doc.getNumRec() == numEnrg){
                return doc;
            }
        }
        return null;
    }

    public void displayAuthors(){
        for (Document doc : documents){
            if (doc instanceof Book){
                Book book = (Book) doc;
                System.out.println(book.getAuthor());
            }
        }
    }
}
