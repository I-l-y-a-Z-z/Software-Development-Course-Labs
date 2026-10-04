package ex9;

class Point {
    private int x;
    private int y;

    public Point(){
        this.x = 0;
        this.y = 0;
    }

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public double distance(){
        return distance(0, 0);
    }

    public double distance(Point point){
        return distance(point.getX(), point.getY());
    }

    public double distance(int x, int y){
        double differenceX = (double) this.x - x;
        double differenceY = (double) this.y - y;
        return Math.sqrt(differenceX * differenceX + differenceY * differenceY);
    }
}
