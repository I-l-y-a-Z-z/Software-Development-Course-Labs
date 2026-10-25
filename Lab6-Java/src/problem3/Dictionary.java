package problem3;

public class Dictionary extends Document {
    private String language;

    public Dictionary(String title, String language){
        super(title);
        this.language = language;
    }

    public String getLanguage(){
        return language;
    }

    public void setLanguage(String language){
        this.language = language;
    }

    public String toString(){
        return super.toString() + ", language: " + language;
    }
}
