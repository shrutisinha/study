package java8.gameOfLife;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GameFunctionalLogicFailed {

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


        Stream stream = Arrays.stream(inputBoard);
//        IntFunction<int>
//        Stream.of(board)
//                .forEach(i -> {
//                    System.out.println();
//                    IntStream.of(i).map(ij->ij+1)
//                            .forEach(System.out::print);
//                });
//        stream.map(intList->
        int[][] result = Arrays.asList(inputBoard).parallelStream().map(intList -> Arrays.stream(intList).parallel()
                .map(value -> {
                    //find what to and update output
                    if (Math.abs(value) == 2 && Integer.signum(value) == 1) {
                        return 1;
                    } else if (Math.abs(value) == 3) {
                        return 1;
                    } else {
                        return 0;
                    }
                }).toArray()
        ).toArray(int[][]::new);

        //NOTE: Cannot change value of inputBoard inside lambda as
        // Variable used in lambda expression should be final or effectively final
        IntStream.range(0, length).parallel().forEach(x -> IntStream.range(0, breadth)
                        .parallel().forEach(y -> {
                            //ERROR CODE
//                    if (Math.abs(inputBoard[x][y]) == 2 && Integer.signum(inputBoard[x][y]) == 1) {
//                    inputBoard[x][y] = 1;
//                } else if (Math.abs(inputBoard[x][y]) == 3) {
//                    inputBoard[x][y] = 1;
//                } else {
//                    inputBoard[x][y] = 0;
//                }
                        })
        );
        inputBoard = new int[length][breadth];
        Stream.of(inputBoard)
                .forEach(i -> {
                    System.out.println();
                    IntStream.of(i)
                            .forEach(System.out::print);
                });

        inputBoard = Arrays.stream(result).map(int[]::clone).toArray(int[][]::new);
        Stream.of(inputBoard)
                .forEach(i -> {
                    System.out.println();
                    IntStream.of(i)
                            .forEach(System.out::print);
                });

        Stream.of(result)
                .forEach(i -> {
                    System.out.println();
                    IntStream.of(i)
                            .forEach(System.out::print);
                });

//        inputBoard=result;
//        inputBoard = Arrays.stream(result).map(int[]::clone).toArray(int[][]::new);


//        List<IntStream> output =Stream.of(inputBoard).map(intList->
//            IntStream.of(intList).map(value->value+1)
//                    .collect(Collectors.toList())
//        ).collect(Collectors.toList());
//        int[][] result = stream.mapToObj(i -> pattern[i]).toArray(boolean[][]::new);

//        for (int i = 0; i < length; i++) {
//            for (int j = 0; j < breadth; j++) {
////                int value = inputBoard[i][j] == -9 ? 0 : inputBoard[i][j];
//
//
//                //find what to and update output
//                if (Math.abs(inputBoard[i][j]) == 2 && Integer.signum(inputBoard[i][j]) == 1) {
//                    inputBoard[i][j] = 1;
//                } else if (Math.abs(inputBoard[i][j]) == 3) {
//                    inputBoard[i][j] = 1;
//                } else {
//                    inputBoard[i][j] = 0;
//                }
//            }
//        }
    }

    public static void gameSnapshotForStep(int[][] inputBoard, int steps) {
        for (int i = 0; i < steps; i++) {
            gameBoardInOneStep(inputBoard);
        }
    }
}
