package main;

import javax.swing.*;


// This class is used to create a GUI, a window.
public class GameWindow extends JFrame {
    public GameWindow(GamePanel panel) {
        setTitle("Chess");

        // Terminate code when window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1920,1080);
        JButton button1 = new JButton("Button 1");

        // Add button to panel (The graphic on the actual window)
        panel.add(button1);
        add(panel);



        setVisible(true);
    }
}
