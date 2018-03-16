package sapper;

import java.awt.*;

public class Constants {

    protected static final int WIDTH = 30;
    protected static final int HIGHT = 20;
    protected static int MAX_BOMBS = 255;
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

    protected Constants() {
        if (MAX_BOMBS < 1 || MAX_BOMBS > (WIDTH * HIGHT / 4)) {
            MAX_BOMBS = WIDTH * HIGHT / 2;
        }
        size = new Coord(WIDTH, HIGHT);
        new MatrixBomb();
        new MatrixField();
    }


}
