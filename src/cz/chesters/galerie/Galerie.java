package cz.chesters.galerie;

import javax.swing.*;

public class Galerie {
    JFrame frame = new JFrame("Galerie");

    Cibuloid onion = new Cibuloid(this);




    public void doShit() {
        galerijuj();
    }

    public void galerijuj() {
        frame.setSize(800, 638); // 4:3 plus horní lišta na windowsech
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setContentPane(onion);
        frame.setResizable(false);

        onion.setBounds(0, 20, 800, 600);
        onion.setVisible(true);

        frame.setVisible(true);
    }



}
