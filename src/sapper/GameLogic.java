package sapper;

public class GameLogic {

    private static boolean gameOver = false;
    private static boolean gameWin = false;

    protected static void clickRightButton(Coord coord) {
        if (MatrixField.getFieldMatrix(coord) == 2) {
            MatrixField.setFieldMatrix(coord, 1);
            Constants.setCountOfBombs(1);
            return;
        }
        if (MatrixField.getFieldMatrix(coord) == 1) {
            MatrixField.setFieldMatrix(coord, 2);
            Constants.setCountOfBombs(-1);
            return;
        }
    }

    protected static void clickLeftButton(Coord coord) {
        if (MatrixField.getFieldMatrix(coord) == 1) {
            if (MatrixField.getOpenedField(coord) == 0 && MatrixBomb.getBombMatrix(coord) == 0) {
                openArroundBlocks(coord);
            }
            MatrixField.setFieldMatrix(coord,0);
        }
        if (MatrixField.getFieldMatrix(coord) == 0 && MatrixField.getOpenedField(coord) > 0 && checkAroundNumber(coord)) {
            openArroundBlocks(coord);
        }
        chekGameOver(coord);
        checkGameWin();
    }

    protected static void clickCenterButton(Coord coord) {
        MatrixBomb.setStartBombs();
        MatrixField.setStartField();
        Constants.setStartCount();
        gameOver = false;
        gameWin = false;
    }

    protected static boolean ifGameOver() {
        return gameOver;
    }

    protected static boolean chekGameOver(Coord coord) {
        if (MatrixField.getFieldMatrix(coord) == 0 && MatrixBomb.getBombMatrix(coord) > 0) {
            gameOver = true;
            Coord coord1;
            for (int x = 0; x < Constants.size.x; x++) {
                for (int y = 0; y < Constants.size.y; y++) {
                    coord1 = new Coord(x, y);
                    if (MatrixField.getFieldMatrix(coord1) > 0 && MatrixBomb.getBombMatrix(coord1) > 0) {
                        MatrixField.setFieldMatrix(coord1, 0);
                    }
                }
            }
        }
        return gameOver;
    }

    private static boolean checkAroundNumber(Coord coord) {
        int count = 0;
        for (int xx = coord.x - 1; xx < coord.x + 2; xx++) {
            for (int yy = coord.y - 1; yy < coord.y + 2; yy++) {
                if (xx >= 0 && xx < Constants.size.x && yy >= 0 && yy < Constants.size.y &&
                        MatrixField.getFieldMatrix(new Coord(xx, yy)) == 2) {
                    if (xx == coord.x && yy == coord.y) {
                        continue;
                    }
                    count++;
                    }
                }
            }
        if (MatrixField.getOpenedField(coord) == count) {
            return true;
        } else {
            return false;
        }
    }

    private static void openArroundBlocks(Coord coord) {
            for (int xx = coord.x - 1; xx < coord.x + 2; xx++) {
                for (int yy = coord.y - 1; yy < coord.y + 2; yy++) {
                    if (xx >= 0 && xx < Constants.size.x && yy >= 0 && yy < Constants.size.y &&
                            MatrixField.getFieldMatrix(new Coord(xx, yy)) == 1) {
                        if (xx == coord.x && yy == coord.y) {
                            continue;
                        }
                        MatrixField.setFieldMatrix(new Coord(xx, yy), 0);
                        chekGameOver(new Coord(xx, yy));
                        if (MatrixField.getOpenedField(new Coord(xx, yy)) == 0 && MatrixBomb.getBombMatrix(new Coord(xx, yy)) == 0) {
                            openArroundBlocks(new Coord(xx, yy));
                        }
                    }
                }
            }
    }



    protected static boolean checkGameWin() {
        int count = 0;
        for (int x = 0; x < Constants.size.x; x++) {
            for (int y = 0; y < Constants.size.y; y++) {
                if (MatrixField.getFieldMatrix(new Coord(x, y)) == 0) {
                    count++;
                }
            }
        }
        Constants.setOpenBoxOnTheField(count);
        if (count + Constants.MAX_BOMBS >= Constants.getVolumeBoxOnField()) {
            gameWin = true;
        }
        return gameWin;
    }

}
