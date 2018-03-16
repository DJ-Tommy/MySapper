package sapper;

public class MatrixBomb {
    private static int[][] bombMatrix; // статус 0 - нет бомбы, 1 - есть бомба

    protected MatrixBomb() {
        bombMatrix = new int[Constants.size.x][Constants.size.y];
        setStartBombs();
    }

    protected static void setStartBombs() {
        for (int x = 0; x < Constants.size.x; x++) {
            for (int y = 0; y < Constants.size.y; y++) {
                bombMatrix[x][y] = 0;
            }
        }

        for (int i = 0; i < Constants.MAX_BOMBS; i++) {
            boolean check = true;
            while (check) {
                int x = (int) (Math.random() * Constants.size.x);
                int y = (int) (Math.random() * Constants.size.y);
                int color = (int) (Math.random() * Constants.COLOR_BOMBS.length);
                if (bombMatrix[x][y] == 0) {
                    bombMatrix[x][y] = color;
                    check = false;
                }
            }
        }
    }

    public static int getBombMatrix(Coord coord) {
        return bombMatrix[coord.x][coord.y];

    }
}
