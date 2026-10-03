package ex1;

class ManageArray {
    public static void printArray(int[] array){
        for (int i = 0; i < array.length; i++){
            System.out.println("Element " + i + " contents " +  array[i]);
        }
    }

    public static int[] sortIntegers(int[] array){
        int[] newArray = new int[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        int temp;
        for (int i = 0; i < newArray.length; i++){
            int min = i;
            for (int j = i; j < newArray.length; j++){
                if (newArray[j] < newArray[min]){
                    min = j;
                }
            }
            temp = newArray[i];
            newArray[i] = newArray[min];
            newArray[min] = temp;
        }

        return newArray;
    }
}
