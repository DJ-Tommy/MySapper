package sapper;

public class MatrixField {
    private static int[][] closedFieldMatrix; // статус 0 - открыто, 1 - закрыто, 2 - флаг бомбы,
    private static int[][] openedFieldMatrix;

    protected MatrixField() {
        closedFieldMatrix = new int[Constants.size.x][Constants.size.y];
        openedFieldMatrix = new int[Constants.size.x][Constants.size.y];
        setStartField();
    }

    protected static void setStartField() {
        for (int x = 0; x < Constants.size.x; x++) {
            for (int y = 0; y < Constants.size.y; y++) {
                closedFieldMatrix[x][y] = 1;
            }
        }
        setEmptyFieldMatrix();
        testOpenBoxOnField();
    }

    protected static int getFieldMatrix(Coord coord) {
        return closedFieldMatrix[coord.x][coord.y];
    }

    protected static void setFieldMatrix(Coord coord, int status) {
        closedFieldMatrix[coord.x][coord.y] = status;
    }

    private static void setEmptyFieldMatrix() {
        for (int x = 0; x < Constants.size.x; x++) {
            for (int y = 0; y < Constants.size.y; y++) {
                int count = 0;
                if (MatrixBomb.getBombMatrix(new Coord(x, y)) == 0) {
                    for (int xx = x - 1; xx < x + 2; xx++) {
                        for (int yy = y - 1; yy < y + 2; yy++) {
                            if (xx >= 0 && xx < Constants.size.x && yy >= 0 && yy < Constants.size.y) {
                                if (xx == x && yy == y) {
                                    continue;
                                }
                                if (MatrixBomb.getBombMatrix(new Coord(xx, yy)) > 0) {
                                    count++;
                                }
                            }
                        }
                    }
                }
                openedFieldMatrix[x][y] = count;
            }
        }

    }

    protected static int getOpenedField(Coord coord) {
        return openedFieldMatrix[coord.x][coord.y];
    }

    private static void testOpenBoxOnField() {
        for (int i = 0; i < Constants.MAX_BOMBS; i++) {
            boolean check = true;
            while (check) {
                int x = (int) (Math.random() * Constants.size.x);
                int y = (int) (Math.random() * Constants.size.y);
                int color = (int) (Math.random() * Constants.COLOR_BOMBS.length);
                if (closedFieldMatrix[x][y] != 0) {
                    closedFieldMatrix[x][y] = 0;
                    check = false;
                }
            }
        }
    }

    private static void testFlagBoxOnField() {
        for (int i = 0; i < Constants.MAX_BOMBS; i++) {
            boolean check = true;
            while (check) {
                int x = (int) (Math.random() * Constants.size.x);
                int y = (int) (Math.random() * Constants.size.y);
                if (closedFieldMatrix[x][y] == 1) {
                    closedFieldMatrix[x][y] = 2;
                    check = false;
                }
            }
        }
    }

}
