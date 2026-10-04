package ex5;

class MatrixAdder {
    public static int[][] MatrixAdd(int[][] matrix1, int[][] matrix2){
        if (matrix1.length == 0) return new int[0][0];
        int[][] matrix3 = new int[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++){
            for (int j = 0; j < matrix1[0].length; j++){
                matrix3[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return matrix3;
    }
}
