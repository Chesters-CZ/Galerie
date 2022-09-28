package cz.chesters.galerie;

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
import java.util.Objects;

public class Cibuloid extends JPanel implements ActionListener {

    JLabel display = new JLabel("Select an image to display");

    File[] obrazky = new File("./imgs/").listFiles();
    // MAKE SURE ./imgs/ IS CLEAN. THERE MUST BE NO NON-IMAGE FILES OR FOLDERS IN THERE

    JButton bigPic = new JButton();
    ArrayList<JButton> menuBtns = new ArrayList<>();

    JToolBar bar = new JToolBar();
    JComboBox<String> box = new JComboBox<>(new String[]{"800x600", "640x480"});
    JLabel settings = new JLabel("Settings");
    JLabel gallery = new JLabel("Gallery");
    JLabel resolution = new JLabel("Resolution");
    JComboBox<String> borderSelection = new JComboBox<>(new String[]{"Default", "None", "Normal", "Thick"});
    JLabel borderStyle = new JLabel("Border style");
    Galerie parent;

    JToolBar menu = new JToolBar();


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public Cibuloid(Galerie galerie) {
        parent = galerie;
        bigPic.setBounds(0, 0, 800, 600);
        bigPic.setBorder(null);
        bigPic.setFocusPainted(true);
        bigPic.setContentAreaFilled(false);
        bigPic.setMargin(new Insets(0, 0, 0, 0));
        prepare(bigPic, 2);
        bigPic.addActionListener(this::clickityClickIsThatADick);
        bigPic.setVisible(false);

        box.setEditable(false);
        box.setBounds(120, 75, 635, 30);
        box.setVisible(true);
        box.addActionListener(this::clickityTwoElectricBoogaloo);
        box.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        bar.add(box);

        borderSelection.setEditable(false);
        borderSelection.setBounds(120, 115, 635, 30);
        borderSelection.setVisible(true);
        borderSelection.addActionListener(this::clickityTwoElectricBoogaloo);
        borderSelection.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        bar.add(borderSelection);

        gallery.setBounds(2, 2, 796, 30);
        gallery.setVisible(true);
        gallery.setFont(new Font(Font.SERIF, Font.BOLD, 27));
        gallery.setHorizontalAlignment(SwingConstants.CENTER);
        bar.add(gallery);

        settings.setBounds(2, 42, 796, 25);
        settings.setVisible(true);
        settings.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        settings.setHorizontalAlignment(SwingConstants.CENTER);
        bar.add(settings);

        resolution.setBounds(25, 75, 100, 30);
        resolution.setVisible(true);
        resolution.setHorizontalAlignment(SwingConstants.LEFT);
        bar.add(resolution);

        borderStyle.setBounds(25, 115, 100, 30);
        borderStyle.setVisible(true);
        borderStyle.setHorizontalAlignment(SwingConstants.LEFT);
        bar.add(borderStyle);

        bar.setBounds(0, 0, 800, 200);
        bar.setFloatable(false);
        bar.setVisible(true);
        bar.setLayout(null);
        add(bar);

        this.setLayout(null);

        menuSetup();
    }

    public void menuSetup() {
        goGoGadget(530, 780, new LineBorder(new Color(53, 54, 58), 1));
    }

    public void menuSetup(boolean isBig, LineBorder border) {
        if (isBig)
            goGoGadget(530, 780, border);
        else goGoGadget(370, 620, border);
    }

    public void scratchThat(boolean isBig, LineBorder border) {
        if (isBig)
            bigPic.setBounds(0, 0, 800, 600);
        else bigPic.setBounds(0, 0, 640, 480);

        bigPic.setBorder(null);
        bigPic.setFocusPainted(true);
        bigPic.setContentAreaFilled(false);
        bigPic.setMargin(new Insets(0, 0, 0, 0));
        prepare(bigPic, 2);
        bigPic.addActionListener(this::clickityClickIsThatADick);
        bigPic.setVisible(false);

        box.setEditable(false);
        box.setBounds(120, 75, 635, 30);
        box.setVisible(true);
        box.addActionListener(this::clickityTwoElectricBoogaloo);
        box.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        bar.add(box);

        borderSelection.setEditable(false);
        borderSelection.setBounds(120, 115, 635, 30);
        borderSelection.setVisible(true);
        borderSelection.addActionListener(this::clickityTwoElectricBoogaloo);
        borderSelection.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        bar.add(borderSelection);

        settings.setBounds(2, 2, 796, 25);
        settings.setVisible(true);
        settings.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        settings.setHorizontalAlignment(SwingConstants.CENTER);
        bar.add(settings);

        resolution.setBounds(25, 75, 100, 30);
        resolution.setVisible(true);
        resolution.setHorizontalAlignment(SwingConstants.LEFT);
        bar.add(resolution);

        borderStyle.setBounds(25, 115, 100, 30);
        borderStyle.setVisible(true);
        borderStyle.setHorizontalAlignment(SwingConstants.LEFT);
        bar.add(borderStyle);

        bar.setBounds(0, 0, 800, 200);
        bar.setFloatable(false);
        bar.setVisible(true);
        bar.setLayout(null);
        add(bar);

        this.setLayout(null);

        menuSetup(isBig, border);
    }

    public void goGoGadget(int yPos, int maxX, LineBorder border) {
        menu.setBounds(0, 200, maxX + 20, yPos + 55);
        menu.setFloatable(false);
        menu.setVisible(true);
        menu.setLayout(new FlowLayout());
        int widthPos = 0;
        for (int i = 0; i < obrazky.length; i++) {
            try {
                menuBtns.add(new JButton(new ImageIcon(ImageIO.read(new File(obrazky[i].getPath())).getScaledInstance(60, 45, Image.SCALE_DEFAULT))));
            } catch (IOException e) {
                e.printStackTrace();
            }
            menuBtns.get(i).setBounds(widthPos, yPos, 60, 45);
            widthPos = widthPos + 60 + 10;
            menuBtns.get(i).addActionListener(this::clickityClickIsThatADick);
            menuBtns.get(i).setVisible(true);
            menuBtns.get(i).setBorder(border);
            menuBtns.get(i).setFocusPainted(true);
            menuBtns.get(i).setContentAreaFilled(false);
            menuBtns.get(i).setMargin(new Insets(5, 5, 5, 5));
            menu.add(menuBtns.get(i));
            widthPos++;
        }
                prepare(menu);
    }

    public void clickityClickIsThatADick(ActionEvent e) {
        if (e.getSource().equals(bigPic)) {
            System.out.println("BigClick");
            bigPic.setIcon(null);
            bigPic.setVisible(false);
        }
        for (int i = 0; i < menuBtns.size(); i++) {
            if (menuBtns.get(i).equals(e.getSource())) {
                BufferedImage img;
                try {
                    img = ImageIO.read(new File(obrazky[i].getPath()));
                    bigPic.setIcon(new ImageIcon(img.getScaledInstance(800, 600, Image.SCALE_DEFAULT)));
                    bigPic.setVisible(true);
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
            }
        }
    }

    private void clickityTwoElectricBoogaloo(ActionEvent e) {
        boolean isBig = true;
        LineBorder border = null;
        if (Objects.equals(box.getSelectedItem(), "800x600")) {
            parent.frame.setSize(800, 638);
            try {
                Thread.sleep(50);
            } catch (Exception ignored) {
            }
            bar.setBounds(0, 0, 800, 200);
            settings.setBounds(2, 42, 796, 25);
            gallery.setBounds(2, 2, 796, 25);
            box.setBounds(120, 75, 635, 30);
            bar.setLayout(null);
            borderSelection.setBounds(120, 115, 635, 30);
        }
        if (Objects.equals(box.getSelectedItem(), "640x480")) {
            isBig = false;
            parent.frame.setSize(640, 518);
            try {
                Thread.sleep(50);
            } catch (Exception ignored) {
            }
            bar.setBounds(0, 0, 640, 200);
            settings.setBounds(2, 42, 636, 25);
            gallery.setBounds(2, 2, 636, 25);
            box.setBounds(120, 75, 425, 30);
            bar.setLayout(null);
            borderSelection.setBounds(120, 115, 425, 30);
        }

        if (Objects.equals(borderSelection.getSelectedItem(), "Default")) {
            border = new LineBorder(new Color(53, 54, 58), 1);
        } else if (Objects.equals(borderSelection.getSelectedItem(), "None")) {
            border = new LineBorder(new Color(53, 54, 58), 0);
        } else if (Objects.equals(borderSelection.getSelectedItem(), "Normal")) {
            border = new LineBorder(new Color(53, 54, 58), 2);
        } else if (Objects.equals(borderSelection.getSelectedItem(), "Thick")) {
            border = new LineBorder(new Color(53, 54, 58), 5);
        }

        menuSetup(isBig, border);

        parent.frame.repaint();
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
