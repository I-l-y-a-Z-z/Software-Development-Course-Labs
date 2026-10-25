package problem3;

public class Document {
    private static int count = 0;
    private int numRec;
    private String title;

    public Document(String title){
        count++;
        numRec = count;
        this.title = title;
    }

    public int getNumRec(){
        return numRec;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String toString(){
        return "Record: " + numRec + ", title: " + title;
    }
}
