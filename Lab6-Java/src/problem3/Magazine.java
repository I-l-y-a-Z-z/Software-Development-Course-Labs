package problem3;

public class Magazine extends Document {
    private String month;
    private int year;

    public Magazine(String title, String month, int year){
        super(title);
        this.month = month;
        this.year = year;
    }

    public String getMonth(){
        return month;
    }

    public void setMonth(String month){
        this.month = month;
    }

    public int getYear(){
        return year;
    }

    public void setYear(int year){
        this.year = year;
    }

    public String toString(){
        return super.toString() + ", month: " + month + ", year: " + year;
    }
}
