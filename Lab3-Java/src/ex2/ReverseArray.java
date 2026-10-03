package ex2;

class ReverseArray {
    private static void _swap(int[] array, int i, int j){
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public static void printAndReverse(int[] array){
        System.out.println("Unreversed Array : ");
        for (int num : array){
            System.out.println("- " + num);
        }
        for (int j = array.length - 1, i = 0; i < j; i++, j--){
            _swap(array, i, j);
        }
        System.out.println("Reversed Array : ");
        for (int num : array){
            System.out.println("- " + num);
        }
    }
}
