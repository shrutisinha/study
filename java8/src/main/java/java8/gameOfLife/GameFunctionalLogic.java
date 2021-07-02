package java8.gameOfLife;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java8.gameOfLife.Field.*;

public class GameFunctionalLogic {

    public static List<Field> initGame(int[][] inputBoard) {
        int ySize = inputBoard.length;
        int xSize = ySize > 0 ? inputBoard[0].length : 0;
        List<Field> game = new LinkedList<>();
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                boolean value = inputBoard[i][j] == 1;
                game.add(new Field(j, i, value));
            }
        }
        return game;
    }

    public static int[][] gameResult(List<Field> outputBoard, int ySize, int xSize) {
        int[][] resultBoard = new int[ySize][xSize];
        for (Field field : outputBoard) {
            resultBoard[field.getY()][field.getX()] = field.getAlive() ? 1 : 0;
        }
        return resultBoard;
    }

    public static void print(int[][] board) {
        Stream.of(board)
                .forEach(i -> {
                    System.out.println();
                    IntStream.of(i)
                            .forEach(System.out::print);
                });
    }


    public static Function<Field, List<Field>> livingNeighboursIn(List<Field> game) {
        return (field) -> game
                .stream()
                .filter(isLivingNeighbour(field))
                .collect(Collectors.toList());
    }

    public static List<Field> gameBoardInOneStep(List<Field> inputBoard) {

        // ALGO:
        return inputBoard
                .stream()
                .map(toDeadField(isAlive().and(
                        hasLessThanTwo(livingNeighboursIn(inputBoard)).or(
                                hasMoreThanThree(livingNeighboursIn(inputBoard)))
                )))
                .map(toAliveField(isDead().and(
                        hasExactThree(livingNeighboursIn(inputBoard)))
                ))
                .collect(Collectors.toList());
    }

    public static void gameSnapshotForStep(int[][] inputBoard, int steps) {
        List<Field> game = initGame(inputBoard);

        for (int i = 0; i <= steps; i++) {
            print(inputBoard);
            System.out.println();
            game = gameBoardInOneStep(game);

            //TODO: Change values of actual array. This will not replace values of array inputBoard.
            inputBoard = gameResult(game, inputBoard.length, inputBoard[0].length);
        }
    }

    private static Predicate<Field> hasMoreThanThree(Function<Field, List<Field>> neighbours) {
        return (field) -> neighbours.apply(field).size() > 3;
    }

    private static Predicate<Field> hasExactThree(Function<Field, List<Field>> neighbours) {
        return (field) -> neighbours.apply(field).size() == 3;
    }

    private static Predicate<Field> hasLessThanTwo(Function<Field, List<Field>> neighbours) {
        return (field) -> neighbours.apply(field).size() < 2;
    }
}
