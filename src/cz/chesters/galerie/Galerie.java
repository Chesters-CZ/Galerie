package cz.chesters.galerie;

import javax.swing.*;

public class Galerie {
    JFrame frame = new JFrame("Galerie");
    LayeredPanel onion = new LayeredPanel(this);


    public void open() {
        frame.setSize(800, 638); // 4:3 plus horní lišta na windowsech @ 150% scaling
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setContentPane(onion);
        frame.setResizable(false);

        onion.setBounds(0, 20, 800, 600);
        onion.setVisible(true);

        frame.setVisible(true);
    }
}
