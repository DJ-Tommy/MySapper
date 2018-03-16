package sapper;

import javax.swing.*;
import java.awt.*;

public class Field extends JFrame{

    JPanel panelField;
    JLabel label;


    public Field() {
        new Constants();
        initLabel();
        initGameField();
        initFrame();


    }

    private void initLabel() {
        label = new JLabel();
        label.setText("Всего поставлено бомб: " + Constants.MAX_BOMBS);
        add(label, BorderLayout.SOUTH);

    }

    private void initGameField() {
        panelField = new CanvasGameField();
        panelField.setPreferredSize(new Dimension(Constants.WIDTH * Constants.SIZE_BLOCK + 1, Constants.HIGHT * Constants.SIZE_BLOCK + 2));
        panelField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelField.setBackground(Color.lightGray);
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

