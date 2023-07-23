package main;

import javax.swing.*;
import java.awt.*;


// This class is used to create a GUI, a window.
public class GameWindow extends JFrame {
    public GameWindow(GamePanel panel) {
        setTitle("Chess");

        // Terminate code when window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1000,1000);

        // Text above the chess board
        JLabel title = new JLabel("Chess");
        Font font = new Font("Planc", Font.BOLD,40);
        title.setFont(font);

        // Add button to panel (The graphic on the actual window)
        panel.add(title);
        add(panel);

        setVisible(true);
    }
}
