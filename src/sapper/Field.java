package sapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Field extends JFrame{

    JPanel panelField;
    JLabel label;


    public Field() {
        new Constants();
        initLabel();
        initGameField();
        initFrame();


    }

    private void changeLabel() {
        if (!GameLogic.ifGameOver()) {
            label.setText("Осталось найти мин: " + Constants.getCountOfBombs() + "  Осталось открыть полей: " + (Constants.getVolumeBoxOnField() - Constants.getOpenBoxOnTheField() - Constants.MAX_BOMBS));
        }
        if (GameLogic.ifGameOver()) {
            label.setText("Game Over");
        }
        if (!GameLogic.ifGameOver() && GameLogic.checkGameWin()) {
            label.setText("Ура. Вы победили!!!");
        }

    }

    private void initLabel() {
        label = new JLabel();
        changeLabel();
        add(label, BorderLayout.SOUTH);

    }

    private void initGameField() {
        panelField = new CanvasGameField();
        panelField.setPreferredSize(new Dimension(Constants.WIDTH * Constants.SIZE_BLOCK + 1, Constants.HIGHT * Constants.SIZE_BLOCK + 2));
        panelField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelField.setBackground(Color.lightGray);
        panelField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = (int) (e.getX() / Constants.SIZE_BLOCK);
                    int y = (int) (e.getY() / Constants.SIZE_BLOCK);
                if (e.getButton() == 3 && !GameLogic.ifGameOver() && !GameLogic.checkGameWin()) {
                    GameLogic.clickRightButton(new Coord(x, y));
                }
                if (e.getButton() == 1 && !GameLogic.ifGameOver() && !GameLogic.checkGameWin()) {
                    GameLogic.clickLeftButton(new Coord(x, y));
                }
                if (e.getButton() == 2) {
                    GameLogic.clickCenterButton(new Coord(x, y));
                }

                changeLabel();
                panelField.repaint();

            }
        });
        add(panelField, BorderLayout.CENTER);

    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setTitle(Constants.TITLE);
        pack();
        setLocationRelativeTo(null);
    }
}

