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

    File[] obrazky = new File("./imgs/").listFiles();
    // MAKE SURE ./imgs/ IS CLEAN. THERE MUST BE NO NON-IMAGE FILES OR FOLDERS IN THERE

    JButton bigPic = new JButton();
    ArrayList<JButton> menuBtns = new ArrayList<>();

    JToolBar bar = new JToolBar();
    JComboBox<String> box = new JComboBox<>(new String[]{"800x600", "640x480"});
    JLabel settings = new JLabel("Settings");
    JLabel gallery = new JLabel("Gallery");
    JLabel resolution = new JLabel("Resolution");
    JComboBox<String> iconSelection = new JComboBox<>(new String[]{"Default", "Tiny", "Smol", "Humongous"});
    JLabel iconSize = new JLabel("Icon size");
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

        iconSelection.setEditable(false);
        iconSelection.setBounds(120, 115, 635, 30);
        iconSelection.setVisible(true);
        iconSelection.addActionListener(this::clickityTwoElectricBoogaloo);
        iconSelection.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        bar.add(iconSelection);

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

        iconSize.setBounds(25, 115, 100, 30);
        iconSize.setVisible(true);
        iconSize.setHorizontalAlignment(SwingConstants.LEFT);
        bar.add(iconSize);

        bar.setBounds(0, 0, 800, 200);
        bar.setFloatable(false);
        bar.setVisible(true);
        bar.setLayout(null);
        add(bar);

        this.setLayout(null);

        menuSetup();
    }

    public void menuSetup() {
        goGoGadget(530, 780, new Rectangle(60, 45));
    }

    public void menuSetup(boolean isBig, Rectangle size) {
        if (isBig)
            goGoGadget(530, 780, size);
        else goGoGadget(370, 620, size);
    }

    public void goGoGadget(int yPos, int maxX, Rectangle size) {
        for (JButton menuBtn : menuBtns) {
            menu.remove(menuBtn);
            menuBtn.removeNotify();
        }
        menuBtns = new ArrayList<>();
        menu.setBounds(0, 200, maxX + 20, yPos + 55);
        menu.setFloatable(false);
        menu.setVisible(true);
        menu.setLayout(new FlowLayout());
        int widthPos = 0;

        if (obrazky.length > 50) {
            System.out.println("You are trying to display a lot of pictures. Please wait, while the program loads them.");
            System.out.println("Should you get an OutOfMemory Exception, try reducing the number of images and their size.");
        }

        for (int i = 0; i < obrazky.length; i++) {
            try {
                menuBtns.add(new JButton(new ImageIcon(ImageIO.read(new File(obrazky[i].getPath())).getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT))));
            } catch (IOException e) {
                e.printStackTrace();
            }
            menuBtns.get(i).setBounds(widthPos, yPos, size.width, size.height);
            widthPos = widthPos + size.width + 10;
            menuBtns.get(i).addActionListener(this::clickityClickIsThatADick);
            menuBtns.get(i).setVisible(true);
            menuBtns.get(i).setFocusPainted(true);
            menuBtns.get(i).setContentAreaFilled(false);
            menuBtns.get(i).setMargin(new Insets(0, 0, 0, 0));
            menuBtns.get(i).setBorder(new LineBorder(new Color(0, 0, 0), 0));
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
                    bigPic.setIcon(new ImageIcon(img.getScaledInstance(bigPic.getWidth(), bigPic.getHeight(), Image.SCALE_DEFAULT)));
                    bigPic.setVisible(true);
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
            }
        }
    }

    private void clickityTwoElectricBoogaloo(ActionEvent e) {
        boolean isBig = true;
        Rectangle size = null;
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
            iconSelection.setBounds(120, 115, 635, 30);
            bigPic.setBounds(0, 0, 800, 600);
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
            iconSelection.setBounds(120, 115, 425, 30);
            bigPic.setBounds(0, 0, 640, 480);
        }

        if (Objects.equals(iconSelection.getSelectedItem(), "Default")) {
            size = new Rectangle(60, 45);
        } else if (Objects.equals(iconSelection.getSelectedItem(), "Tiny")) {
            size = new Rectangle(16, 12);
        } else if (Objects.equals(iconSelection.getSelectedItem(), "Smol")) {
            size = new Rectangle(32, 24);
        } else if (Objects.equals(iconSelection.getSelectedItem(), "Humongous")) {
            size = new Rectangle(100, 75);
        }

        menuSetup(isBig, size);
        parent.frame.repaint();
        parent.frame.revalidate();
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
