package pl.msi;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    public App() throws HeadlessException {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        setVisible(true);

        Border border = new Border(0, 4000, 500);
        Square square = new Square(50, 400, 100, border);

        GameMechanics gameMechanics = new GameMechanics(border, square, 200);
        add(gameMechanics);

        repaint();
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame ex = new App();
                ex.setVisible(true);
            }
        });
    }
}

