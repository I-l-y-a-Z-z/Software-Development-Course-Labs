package ex3;

class ArrayBuilder {
    public static int[][] builder(int numLines){
        int count = 1;
        int[][] array = new int[numLines][];
        for(int i = 0; i < array.length; i++){
            array[i] = new int[i + 1];
            for(int j = 0; j < i + 1; j++){
                array[i][j] = count;
                count++;
            }
        }
        return array;
    }
    public static void printer(int[][] array){
        for(int[] row : array){
            for (int num : row){
                System.out.print(num + " ");
            }
            System.out.print("\n");
       }
    }
}
