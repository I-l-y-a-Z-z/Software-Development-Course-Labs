package ex1;

class Main {
    public static void main(String[] args){
        int[] array = {106, 26, 81, 5, 15};
        System.out.println("Unsorted array : ");
        ManageArray.printArray(array);
        array = ManageArray.sortIntegers(array);
        System.out.println("Sorted array : ");
        ManageArray.printArray(array);
    }
}
