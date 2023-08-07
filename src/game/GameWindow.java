package game;

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
        JLabel currentPlayer = new JLabel("Player 1");
        Font font = new Font("Planc", Font.BOLD,40);
        title.setFont(font);

        panel.setPreferredSize(new Dimension(1000, 1000));
        // Add button to panel (The graphic on the actual window)
        panel.add(title);
        setLayout(new GridBagLayout());

        // Add the panel to the center of the frame using GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(panel, gbc);

        pack(); // Pack the components to fit the preferred size

        setVisible(true);
        System.out.println(this.getSize());
    }

}
