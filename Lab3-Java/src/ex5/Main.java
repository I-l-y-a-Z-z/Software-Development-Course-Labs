package ex5;

class Main {
    public static void main(String[] args){
        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}};
        int[][] matrix2 = {{7, 8, 9}, {10, 11, 12}};
        int[][] matrix3 = MatrixAdder.MatrixAdd(matrix1, matrix2);

        for (int i = 0; i < matrix3.length; i++){
            for (int j = 0; j < matrix3[i].length; j++){
                System.out.print(matrix3[i][j] + " ");
            }
            System.out.println();
        }
    }
}
