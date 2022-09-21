package cz.chesters.stopky;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class Galerie {
    JFrame frame = new JFrame("Je čas měřit čas");

    JLayeredPane onion = new JLayeredPane();
    ArrayList<JButton> menuBtns = new ArrayList<>();
    JLabel display = new JLabel("Select an image to display");
    File[] obrazky = new File("./imgs/").listFiles();
    File[] thumbnaily = new File("./imgs/thumbnails").listFiles();
    JButton test = new JButton(new ImageIcon("imgs/thumbnails/IMG_7456.JPG"));
    JLabel bigPic = new JLabel();

    public void doShit() {
        galerijuj();
    }

    public void galerijuj() {
        frame.setSize(800, 638); // 4:3 plus horní lišta na windowsech
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setContentPane(n);
        frame.setResizable(false);

        onion.setPreferredSize(new Dimension(800,600));
        frame.add(onion);
        onion.setVisible(true);

        test.setBounds(370, 530, 60, 45);
        test.setBorder(new LineBorder(new Color(53, 54, 58), 1));
        test.setForeground(Color.RED);
        test.setFocusPainted(true);
        test.setContentAreaFilled(false);
        test.setMargin(new Insets(0, 0, 0, 0));
        // prepare(test);

        bigPic.setBounds(0,0,800,600);
        prepare(bigPic, 0);

        menuSetup();

        frame.setVisible(true);
    }

    public void menuSetup() {
        int heightPos = 530;
        int widthPos = 0;
        if (obrazky.length % 2 == 1) {
            widthPos = ((obrazky.length - 1) / 2) * 60 + (((obrazky.length - 1) / 2) - 1) * 10 + 30;
        } else {
            widthPos = (obrazky.length / 2) * 60 + ((obrazky.length / 2) - 1) * 10 + 5;
        }
        if (widthPos < 20)
            widthPos = 20;

        for (int i = 0; i < obrazky.length; i++) {
            menuBtns.add(new JButton(new ImageIcon(thumbnaily[i].getPath())));
            menuBtns.get(i).setBounds(widthPos, heightPos, 60, 45);
            widthPos = widthPos + 60 + 10;
            menuBtns.get(i).addActionListener(this::clickityClickIsThatADick);
            prepare(menuBtns.get(i));
            if (widthPos >= 580)
                break;
        }
    }

    public void clickityClickIsThatADick(ActionEvent e) {
        Object source = e.getSource();
        for (int i = 0; i < menuBtns.size(); i++) {
            if (menuBtns.get(i).equals(e.getSource())){
                bigPic.setIcon(new ImageIcon(obrazky[i].getPath()));
            }
        }

        frame.update(frame.getGraphics());
    }

    public void prepare(JComponent o) {
        o.setVisible(true);
        onion.add(o, Integer.valueOf(1));
    }

    public void prepare(JComponent o, int z) {
        o.setVisible(true);
        onion.add(o, Integer.valueOf(z));
    }

    public void prepare(JComponent o, Integer z) {
        o.setVisible(true);
        onion.add(o, z);
    }

}
