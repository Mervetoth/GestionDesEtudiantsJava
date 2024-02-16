package Graphique;

import java.awt.*;
import javax.swing.*;

public class BackgroundImage extends JPanel {
    private Image pic; // declare an Image variable

    public BackgroundImage() {
        ImageIcon obj = new ImageIcon("src/network.jpg"); // corrected file name
        pic = obj.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(pic, 0, 0, getWidth(), getHeight(), this); // stretch the image to fit the panel
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Background Image");
        BackgroundImage pic = new BackgroundImage();
        f.setSize(365, 395);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(pic);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
