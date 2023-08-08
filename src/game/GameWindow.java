package game;

import javax.swing.*;
import java.awt.*;


// This class is used to create a GUI, a window.
public class GameWindow extends JFrame {
    public GameWindow(GamePanel panel) {
        // Window options
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Chess");
        setSize(1000,1000);
        setLayout(new GridBagLayout());

        // Add the panel to the center of the frame using GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(panel, gbc);
        pack(); // Pack the components to fit the preferred size
        setVisible(true);
    }
}
