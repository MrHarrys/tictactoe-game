package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    final static char X_char = 'X';
    final static char O_char = 'O';

    static char[] DELIMETRS = {'|', '-'};

    static char[][] TILE = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    final static int[] COORDINATES = {11, 12, 13, 21, 22, 23, 31, 32, 33};

    public static void main(String[] args) {
        while (checkTile()) {
            drawTile();
            checkHuman();
            checkComputer();
        }
    }

    private static void drawTile() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(TILE[i][j]);
                if (j != 2) {
                    System.out.print(DELIMETRS[0]);
                } else
                    System.out.println();
            }
            if (i != 2)
                System.out.println(DELIMETRS[1] + " " + DELIMETRS[1] + " " + DELIMETRS[1]);
        }
    }

    private static void checkHuman() {
        System.out.print("Enter coordinates to insert X (XY only): ");
        Scanner scanner = new Scanner(System.in);
        Integer i = scanner.nextInt();
        if (!(i instanceof Integer ||
                i != 11 || i != 12 || i != 13 ||
                i != 21 || i != 22 || i != 23 ||
                i != 31 || i != 32 || i != 33)) {
            System.out.print("Wrong coordinates, try again");
        } else {
            if (checkEmptyTiles((i / 10) - 1, (i % 10) - 1)) {
                insertIntoTile(i, X_char);
            } else checkHuman();
        }
    }

    private static void checkComputer() {
        System.out.println("Computer pass");
        Random computerRandom = new Random();
        int i = COORDINATES[computerRandom.nextInt(COORDINATES.length)];
        if (checkEmptyTiles((i / 10) - 1, (i % 10) - 1)) {
            insertIntoTile(COORDINATES[computerRandom.nextInt(COORDINATES.length)], O_char);
        } else checkComputer();
    }

    private static void insertIntoTile(int coord, char c) {
        int x = (coord / 10) - 1;
        int y = (coord % 10) - 1;
        TILE[x][y] = c;
        checkWin(c);
    }

    private static boolean checkTile() {
        int checkFull = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (TILE[i][j] == 'X' || TILE[i][j] == 'O')
                    checkFull++;
            }
        }
        if (checkFull == 9) {
            System.out.println("EndGame, tile is full");
            return false;
        } else return true;
    }

    private static boolean checkEmptyTiles(int x, int y) {
        if (TILE[x][y] == ' ')
            return true;
        else return false;
    }

    private static void checkWin(char c) {
        if (
                (TILE[0][0] == c && TILE[1][1] == c && TILE[2][2] == c) ||
                        (TILE[0][2] == c && TILE[1][1] == c && TILE[2][0] == c) ||
                        (TILE[0][0] == c && TILE[0][1] == c && TILE[0][2] == c) ||
                        (TILE[1][0] == c && TILE[1][1] == c && TILE[1][2] == c) ||
                        (TILE[2][0] == c && TILE[2][1] == c && TILE[2][2] == c) ||
                        (TILE[0][0] == c && TILE[1][0] == c && TILE[2][0] == c) ||
                        (TILE[0][1] == c && TILE[1][1] == c && TILE[2][1] == c) ||
                        (TILE[0][2] == c && TILE[1][2] == c && TILE[2][2] == c)
        ) {
            drawTile();
            if (c == X_char) {
                System.out.println("YOU WIN!!!!");
                System.exit(0);
            } else if (c == O_char){
                System.out.println("Computer win, you lose!!!!");
                System.exit(0);
            }
        }
    }
}