package sapper;

import java.awt.*;

public class Constants {

    protected static final int WIDTH = 10;
    protected static final int HIGHT = 10;
    protected static int MAX_BOMBS = 5;
    protected static final int SIZE_BLOCK = 30;
    protected static Coord size;
    protected static String TITLE = "Sapper v.0.2";
    protected static Color[] COLOR_BOMBS = {Color.BLACK,
                                            Color.GREEN,
                                            Color.RED,
                                            Color.PINK,
                                            Color.ORANGE,
                                            Color.CYAN};
    protected static Color[] COLOR_NUMBERS = {Color.BLACK,
                                            Color.ORANGE,
                                            Color.RED,
                                            Color.MAGENTA,
                                            Color.GREEN,
                                            Color.CYAN,
                                            Color.YELLOW,
                                            Color.PINK};
    private static int countOfBombs;
    private static int openBoxOnTheField;
    private static int volumeBoxOnField;

    protected Constants() {
        if (MAX_BOMBS < 1 || MAX_BOMBS > (WIDTH * HIGHT / 4)) {
            MAX_BOMBS = WIDTH * HIGHT / 4;
        }
        size = new Coord(WIDTH, HIGHT);
        setStartCount();

        new MatrixBomb();
        new MatrixField();
    }

    protected static int getCountOfBombs() {
        return countOfBombs;
    }

    protected static void setCountOfBombs(int i) {
        countOfBombs += i;
    }

    protected static void setStartCount() {
        countOfBombs = MAX_BOMBS;
        volumeBoxOnField = WIDTH * HIGHT;
        openBoxOnTheField = 0;
    }

    protected static void setOpenBoxOnTheField(int i) {
        openBoxOnTheField = i;
    }

    protected static int getOpenBoxOnTheField() {
        return openBoxOnTheField;
    }

    protected static int getVolumeBoxOnField() {
        return volumeBoxOnField;
    }
}
