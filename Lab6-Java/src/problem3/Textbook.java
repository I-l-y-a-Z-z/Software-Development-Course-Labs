package problem3;

public class Textbook extends Book {
    private String level;

    public Textbook(String title, String author, int nbrPages, String level){
        super(title, author, nbrPages);
        this.level = level;
    }

    public String getLevel(){
        return level;
    }

    public void setLevel(String level){
        this.level = level;
    }

    public String toString(){
        return super.toString() + ", level: " + level;
    }
}
