package ex4;

class Main {
    public static void main(String[] args){
        int[][] matrix = new int[6][8];
        int count = 0;
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 8; j++){
                matrix[i][j] = count;
                count++;
            }
        }
        System.out.println("Before :");
        ManageMatrix.printMatrix(matrix);
        ManageMatrix.copy(matrix);
        System.out.println("After :");
        ManageMatrix.printMatrix(matrix);
    }
}
