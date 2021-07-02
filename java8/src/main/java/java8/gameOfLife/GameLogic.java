package java8.gameOfLife;

public class GameLogic {

    public static void gameBoardInOneStep(int[][] inputBoard) {
        int length = inputBoard.length;
        int breadth = length > 0 ? inputBoard[0].length : 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < breadth; j++) {
                //count neighbor number
                int count = 0;
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (k + i >= 0 && k + i < length && l + j >= 0 && l + j < breadth && !(k == 0 && l == 0)) {
                            int cellValue = inputBoard[k + i][l + j];
                            count += cellValue > 0 ? 1 : 0;
                        }
                    }
                }
                int sign = inputBoard[i][j] == 0 ? -1 : 1;
                if (sign == 1 && count == 0) {
                    inputBoard[i][j] = 9;
                } else {
                    inputBoard[i][j] = sign * (count);
                }
                System.out.print(inputBoard[i][j]);
                System.out.print(' ');

            }
            System.out.println();
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < breadth; j++) {
                //find what to and update output
                if (Math.abs(inputBoard[i][j]) == 2 && Integer.signum(inputBoard[i][j]) == 1) {
                    inputBoard[i][j] = 1;
                } else if (Math.abs(inputBoard[i][j]) == 3) {
                    inputBoard[i][j] = 1;
                } else {
                    inputBoard[i][j] = 0;
                }
            }
        }
    }

    public static void gameSnapshotForStep(int[][] inputBoard, int steps) {
        for (int i = 0; i <= steps; i++) {
            gameBoardInOneStep(inputBoard);
        }
    }
}
