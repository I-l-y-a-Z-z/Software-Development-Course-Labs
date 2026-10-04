package ex7;

public class Stdev {
    public static double stdev(int[] array){
        double sum = 0;
        for (int i = 0; i < array.length; i++){
            sum += array[i];
        }
        double average = sum / array.length;

        double sumSquares = 0;
        for (int i = 0; i < array.length; i++){
            double difference = array[i] - average;
            sumSquares += difference * difference;
        }

        return Math.sqrt(sumSquares / (array.length - 1));
    }
}
