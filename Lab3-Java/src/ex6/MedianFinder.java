package ex6;

import java.util.Arrays;

class MedianFinder {
    public static int median(int[] array){
        Arrays.sort(array);
        return array[array.length / 2];
    }
}
