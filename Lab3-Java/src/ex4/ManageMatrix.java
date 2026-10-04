package ex4;

class ManageMatrix {
    public static void copy(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            matrix[i][4] = matrix[i][1];
        }
    }
    public static void printMatrix(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
