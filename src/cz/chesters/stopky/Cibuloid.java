package cz.chesters.stopky;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Cibuloid extends JPanel implements ActionListener {

    JLabel display = new JLabel("Select an image to display");
    File[] obrazky = new File("./imgs/").listFiles();
    File[] thumbnaily = new File("./imgs/thumbnails").listFiles();
    JButton test = new JButton(new ImageIcon("imgs/thumbnails/IMG_7456.JPG"));
    JButton bigPic = new JButton();
    ArrayList<JButton> menuBtns = new ArrayList<>();

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public Cibuloid(){
        test.setBounds(370, 530, 60, 45);
        test.setBorder(new LineBorder(new Color(53, 54, 58), 1));
        test.setForeground(Color.RED);
        test.setFocusPainted(true);
        test.setContentAreaFilled(false);
        test.setMargin(new Insets(0, 0, 0, 0));
        // prepare(test);

        bigPic.setBounds(0, 0, 800, 600);
        bigPic.setBorder(null);
        bigPic.setFocusPainted(true);
        bigPic.setContentAreaFilled(false);
        bigPic.setMargin(new Insets(0, 0, 0, 0));
        prepare(bigPic, 2);
        bigPic.addActionListener(this::clickityClickIsThatADick);
        bigPic.setVisible(false);

        this.setLayout(null);

        menuSetup();
    }

    public void menuSetup() {
        int heightPos = 530;
        int widthPos;
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
            menuBtns.get(i).setBorder(new LineBorder(new Color(53, 54, 58), 1));
            menuBtns.get(i).setFocusPainted(true);
            menuBtns.get(i).setContentAreaFilled(false);
            menuBtns.get(i).setMargin(new Insets(0, 0, 0, 0));
            if (widthPos >= 580)
                break;
        }
    }

    public void clickityClickIsThatADick(ActionEvent e) {
        if (e.getSource().equals(bigPic)){
            System.out.println("BigClick");
            bigPic.setIcon(null);
            bigPic.setVisible(false);
        }
        for (int i = 0; i < menuBtns.size(); i++) {
            if (menuBtns.get(i).equals(e.getSource())) {
                BufferedImage img;
                try {
                    img = ImageIO.read(new File(obrazky[i].getPath()));
                    bigPic.setIcon(new ImageIcon(img.getScaledInstance(800,600, Image.SCALE_DEFAULT)));
                    bigPic.setVisible(true);
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
            }
        }
    }

    public void prepare(JComponent o) {
        o.setVisible(true);
        this.add(o, Integer.valueOf(1));
    }

    public void prepare(JComponent o, int z) {
        o.setVisible(true);
        this.add(o, Integer.valueOf(z));
    }

    public void prepare(JComponent o, Integer z) {
        o.setVisible(true);
        this.add(o, z);
    }
}
