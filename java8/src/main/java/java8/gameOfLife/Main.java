package java8.gameOfLife;

public class Main {


    public static void main(String[] args) {
        int[][] board = new int[][]{
//                {0, 0, 1, 0, 0},
//                {0, 0, 1, 0, 0},
//                {0, 0, 1, 0, 0},
//                {0, 0, 1, 0, 0},
//                {0, 0, 1, 0, 0},

                {1, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 0},
                {1, 0, 0, 0, 1, 0},
                {1, 1, 1, 1, 0, 1},
                {0, 1, 1, 0, 1, 0},
                {1, 0, 1, 0, 1, 1},
                {1, 0, 0, 1, 1, 1},
                {1, 1, 0, 0, 0, 0}
        };

        System.out.println();
        System.out.println();
        for (int i = 0; i < 1; i++) {
            GameFunctionalLogic.gameSnapshotForStep(board, 6);
        }
    }
}
