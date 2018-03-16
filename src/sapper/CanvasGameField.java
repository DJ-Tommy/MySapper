package sapper;

import javax.swing.*;
import java.awt.*;

public class CanvasGameField extends JPanel {
    Font font_number = new Font("Times New Roman", 1, Constants.SIZE_BLOCK / 3 * 2);

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(font_number);
        for (int x = 0; x < Constants.size.x; x++) {
            for (int y = 0; y < Constants.size.y; y++) {
                Coord coord = new Coord(x, y);
                if (MatrixField.getFieldMatrix(coord) > 0) {
                    paintCloseBox(coord, g);
                    if (MatrixField.getFieldMatrix(coord) == 2) {
                        paintFlagOnField(coord, g);
                    }
                }

                if (MatrixField.getFieldMatrix(coord) == 0) {
                    paintOpenBox(coord, g);
                    if (MatrixBomb.getBombMatrix(coord) > 0) {
                        paintBombOnField(coord, g);
                        paintExplodedBombOnField(coord, g);
                    }
                    if (MatrixField.getOpenedField(coord) > 0) {
                        paintNumberOnField(coord, g);
                    }
                }
            }
        }
    }

    private void paintCloseBox(Coord coord, Graphics g) {
        int x = coord.x;
        int y = coord.y;
        g.setColor(Color.DARK_GRAY);
        g.drawLine(x * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK - 2, y * Constants.SIZE_BLOCK + 2,
                x * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK - 2, y * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK - 1);
        g.drawLine(x * Constants.SIZE_BLOCK + 2, y * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK - 1,
                x * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK - 2, y * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK - 1);
        g.setColor(Color.WHITE);
        g.drawLine(x * Constants.SIZE_BLOCK + 1, y * Constants.SIZE_BLOCK + 2,
                x * Constants.SIZE_BLOCK + 1, y * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK - 2);
        g.drawLine(x * Constants.SIZE_BLOCK + 1, y * Constants.SIZE_BLOCK + 2,
                x * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK - 2, y * Constants.SIZE_BLOCK + 2);

    }

    private void paintOpenBox(Coord coord, Graphics g) {
        int x = coord.x;
        int y = coord.y;
        g.setColor(Color.WHITE);
        g.drawLine(x * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK - 2, y * Constants.SIZE_BLOCK + 2,
                x * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK - 2, y * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK - 1);
        g.drawLine(x * Constants.SIZE_BLOCK + 2, y * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK - 1,
                x * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK - 2, y * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK - 1);
        g.setColor(Color.DARK_GRAY);
        g.drawLine(x * Constants.SIZE_BLOCK + 1, y * Constants.SIZE_BLOCK + 2,
                x * Constants.SIZE_BLOCK + 1, y * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK - 2);
        g.drawLine(x * Constants.SIZE_BLOCK + 1, y * Constants.SIZE_BLOCK + 2,
                x * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK - 2, y * Constants.SIZE_BLOCK + 2);
    }

    private void paintNumberOnField(Coord coord, Graphics g) {
        int x = coord.x;
        int y = coord.y;
        g.setColor(Constants.COLOR_NUMBERS[MatrixField.getOpenedField(new Coord(x, y)) - 1]);
        g.drawString(String.valueOf(MatrixField.getOpenedField(new Coord(x, y))),
                x * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK / 3, (y + 1) * Constants.SIZE_BLOCK - Constants.SIZE_BLOCK / 4);

    }

    private void paintBombOnField (Coord coord, Graphics g) {
        int x = coord.x;
        int y = coord.y;
        g.setColor(Constants.COLOR_BOMBS[0]);
        g.fillOval(x * Constants.SIZE_BLOCK + 6, y * Constants.SIZE_BLOCK +6,
                Constants.SIZE_BLOCK - 11, Constants.SIZE_BLOCK - 11);
    }

    private void paintFlagOnField (Coord coord, Graphics g) {
        int x = coord.x;
            int y = coord.y;
        int fx1 = x * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK / 4 ;
            int fy1 = (y + 1) * Constants.SIZE_BLOCK - Constants.SIZE_BLOCK / 8 - 1;
        int fx2 = x * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK / 4;
            int fy2 = y * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK / 8 + 1;
        int fx3 = (x + 1) * Constants.SIZE_BLOCK - Constants.SIZE_BLOCK / 4;
            int fy3 = y * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK / 8 * 3;
        int fx4 = x * Constants.SIZE_BLOCK + Constants.SIZE_BLOCK / 4;
            int fy4 = (y + 1) * Constants.SIZE_BLOCK - Constants.SIZE_BLOCK / 8 * 3;
        int[] fx = {fx2, fx3, fx4};
            int[] fy = {fy2, fy3, fy4};
        g.setColor(Color.RED);
        g.fillPolygon(fx, fy, 3);
        g.setColor(Color.BLACK);
        g.drawLine(fx1, fy1, fx2, fy2);
        g.drawLine(fx1 + 1, fy1, fx2 + 1, fy2);
    }

    private void paintExplodedBombOnField (Coord coord, Graphics g) {
        int x = coord.x;
        int y = coord.y;
        g.setColor(Color.RED);
        g.drawLine(x * Constants.SIZE_BLOCK + 4, y * Constants.SIZE_BLOCK + 4,
                (x + 1) * Constants.SIZE_BLOCK - 4, (y + 1) * Constants.SIZE_BLOCK - 4);
        g.drawLine(x * Constants.SIZE_BLOCK + 5, y * Constants.SIZE_BLOCK + 4,
                (x + 1) * Constants.SIZE_BLOCK - 3, (y + 1) * Constants.SIZE_BLOCK - 4);
        g.drawLine(x * Constants.SIZE_BLOCK + 3, y * Constants.SIZE_BLOCK + 4,
                (x + 1) * Constants.SIZE_BLOCK - 5, (y + 1) * Constants.SIZE_BLOCK - 4);

        g.drawLine((x + 1) * Constants.SIZE_BLOCK - 4, y * Constants.SIZE_BLOCK + 4,
                x * Constants.SIZE_BLOCK + 4, (y + 1) * Constants.SIZE_BLOCK - 4);
        g.drawLine((x + 1) * Constants.SIZE_BLOCK - 3, y * Constants.SIZE_BLOCK + 4,
                x * Constants.SIZE_BLOCK + 5, (y + 1) * Constants.SIZE_BLOCK - 4);
        g.drawLine((x + 1) * Constants.SIZE_BLOCK - 5, y * Constants.SIZE_BLOCK + 4,
                x * Constants.SIZE_BLOCK + 3, (y + 1) * Constants.SIZE_BLOCK - 4);
    }



}
