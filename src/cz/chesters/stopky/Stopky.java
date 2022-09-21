package cz.chesters.stopky;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class Stopky {
    JFrame frame = new JFrame("Je čas měřit čas");


    ArrayList<JButton> menuBtns = new ArrayList<>();
    JLabel display = new JLabel("Select an image to display");
    File[] obrazky = new File("/imgs/").listFiles();
    JButton test = new JButton(new ImageIcon("imgs/thumbnails/IMG_7456.JPG"));

    public void doShit() {
        funguj();
    }

    public void funguj() {
        frame.setSize(800, 638); // 4:3 plus horní lišta na windowsech
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        test.setBounds(370, 530, 60, 45);
        test.setBorder(new LineBorder(new Color(53, 54, 58), 1));
        test.setForeground(Color.RED);
        test.setFocusPainted(true);
        test.setContentAreaFilled(false);
        test.setMargin(new Insets(0, 0, 0, 0));
        prepare(test);

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

        for (int i = 0; i < obrazky.length; i++){
            menuBtns.add(new JButton(new ImageIcon(obrazky[i].getPath())));
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


        frame.update(frame.getGraphics());
    }

    public void prepare(JComponent o) {
        o.setVisible(true);
        frame.add(o);
    }


}
