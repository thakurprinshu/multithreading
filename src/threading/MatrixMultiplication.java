package threading;

class MatrixMultiplier implements Runnable {
    private final int[][] A, B, result;
    private final int row;

    MatrixMultiplier(int[][] A, int[][] B, int[][] result, int row) {
        this.A = A;
        this.B = B;
        this.result = result;
        this.row = row;
    }

    @Override
    public void run() {
        int colsB = B[0].length;
        int colsA = A[0].length;
        for (int j = 0; j < colsB; j++) {
            int sum = 0;
            for (int k = 0; k < colsA; k++) {
                sum += A[row][k] * B[k][j];
            }
            result[row][j] = sum; // unique cell → no race condition
        }
    }
}

public class MatrixMultiplication {
    public static void main(String[] args) throws InterruptedException {
        int[][] A = {{1, 2}, {3, 4}};
        int[][] B = {{5, 6}, {7, 8}};
        int rows = A.length, colsB = B[0].length;

        int[][] result = new int[rows][colsB];
        Thread[] threads = new Thread[rows];

        for (int i = 0; i < rows; i++) {
            threads[i] = new Thread(new MatrixMultiplier(A, B, result, i));
            threads[i].start();
        }

        for (Thread t : threads) t.join();

        System.out.println("Result:");
        for (int[] row : result) {
            for (int val : row) System.out.print(val + " ");
            System.out.println();
        }
    }
}

