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
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Double.parseDouble;

public class LayeredPanel extends JPanel implements ActionListener {

    File[] obrazky = new File("./imgs/").listFiles();

    // abych předešel chybám při pokusech zobrazit neobrázkový soubor jako obrázek,
    // galerie bude podporovat jen obrázky s koncovkou uloženou zde
    String[] allowedImageExtensions = {".jpg", ".png"};

    JButton bigPic = new JButton();
    ArrayList<SelectableImageIcon> imageIcons = new ArrayList<>();

    JToolBar bar = new JToolBar();
    JComboBox<String> windowSizeComboBox = new JComboBox<>(new String[]{"800x600", "640x480"});
    JLabel galleryLabel = new JLabel("Gallery");
    JLabel windowSizeLabel = new JLabel("Window size:");
    JComboBox<String> iconSizeComboBox = new JComboBox<>(new String[]{"Default", "Tiny", "Smol", "Humongous"});
    JLabel iconSizeLabel = new JLabel("Icon size");
    JLabel modeLabel = new JLabel("Mode");
    JComboBox<String> modeComboBox = new JComboBox<>(new String[]{"Single picture", "Select images to be presented", "Presentation mode"});
    JLabel imageRateLabel = new JLabel("Present. delays");
    JComboBox<String> imageRateComboBox = new JComboBox<>(new String[]{"0.25s", "0.5s", "0.75s", "1.0s", "1.5s", "2.0s", "2.5s", "3.0s", "4.0s", "5.0s", "10.0s", "15.0s", "20.0s", "30.0s", "45.0s", "60.0s", "120.0"});
    Timer presentationTimer = new Timer();
    TimerTask presentationTimerTask;
    int slideNo;

    Galerie parent;

    JToolBar menu = new JToolBar();


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action: " + e.getSource() + ", " + e.getActionCommand());
    }

    public LayeredPanel(Galerie galerie) {
        parent = galerie;
        bigPic.setBounds(0, 0, 800, 600);
        bigPic.setBorder(null);
        bigPic.setFocusPainted(true);
        bigPic.setContentAreaFilled(false);
        bigPic.setMargin(new Insets(0, 0, 0, 0));
        prepare(bigPic, 3);
        bigPic.addActionListener(this::clickityClickIsThatADick);
        bigPic.setVisible(false);

        windowSizeComboBox.setEditable(false);
        windowSizeComboBox.setBounds(120, 35, 635, 30);
        windowSizeComboBox.setVisible(true);
        windowSizeComboBox.addActionListener(this::clickityTwoElectricBoogaloo);
        windowSizeComboBox.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        bar.add(windowSizeComboBox);

        iconSizeComboBox.setEditable(false);
        iconSizeComboBox.setBounds(120, 75, 635, 30);
        iconSizeComboBox.setVisible(true);
        iconSizeComboBox.addActionListener(this::clickityTwoElectricBoogaloo);
        iconSizeComboBox.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        bar.add(iconSizeComboBox);

        modeComboBox.setEditable(false);
        modeComboBox.setBounds(120, 115, 635, 30);
        modeComboBox.setVisible(true);
        modeComboBox.addActionListener(this::clickityTwoElectricBoogaloo);
        modeComboBox.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        bar.add(modeComboBox);

        imageRateComboBox.setEditable(false);
        imageRateComboBox.setBounds(120, 155, 635, 30);
        imageRateComboBox.setVisible(true);
        imageRateComboBox.addActionListener((this::clickityTwoElectricBoogaloo));
        imageRateComboBox.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        bar.add(imageRateComboBox);

        galleryLabel.setBounds(2, 2, 796, 30);
        galleryLabel.setVisible(true);
        galleryLabel.setFont(new Font(Font.SERIF, Font.BOLD, 27));
        galleryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bar.add(galleryLabel);

        windowSizeLabel.setBounds(25, 35, 100, 30);
        windowSizeLabel.setVisible(true);
        windowSizeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        bar.add(windowSizeLabel);

        iconSizeLabel.setBounds(25, 75, 100, 30);
        iconSizeLabel.setVisible(true);
        iconSizeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        bar.add(iconSizeLabel);

        modeLabel.setBounds(25, 115, 100, 30);
        modeLabel.setVisible(true);
        modeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        bar.add(modeLabel);

        imageRateLabel.setBounds(25, 155, 100, 30);
        imageRateLabel.setVisible(true);
        imageRateLabel.setHorizontalAlignment(SwingConstants.LEFT);
        bar.add(imageRateLabel);

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
        for (SelectableImageIcon s : imageIcons) {
            menu.remove(s.jbutton);
            s.jbutton.removeNotify();
        }


        menu.setBounds(0, 200, maxX + 20, yPos + 55);
        menu.setFloatable(false);
        menu.setVisible(true);
        menu.setLayout(new FlowLayout());
        int widthPos = 0;

        if (obrazky.length > 50) {
            System.out.println("You are trying to display a lot of pictures. Please wait, while the program loads them.");
            System.out.println("Should you get an OutOfMemory Exception, try reducing the number of images and their size.");
        }
        if (!Objects.equals(modeComboBox.getSelectedItem(), "Presentation mode")) {
            System.out.println("obrazky.length: " + obrazky.length + ", imageIcons.length: " + imageIcons.size());
            for (int i = 0; i < obrazky.length; i++) {
                File obrazek = obrazky[i];
                if (imageIcons.size() < obrazky.length) {
                    imageIcons.add(new SelectableImageIcon(new JButton()));
                }
                if (checkExtension(obrazek.getName().substring(obrazek.getName().lastIndexOf(".")))) {
                    System.out.println("extension ok");
                    try {
                        if (Objects.equals(modeComboBox.getSelectedItem(), "Select images to be presented") && !imageIcons.get(i).selected) {
                            System.out.println("Creating gray image");
                            imageIcons.get(i).jbutton = (new JButton(new ImageIcon(GrayFilter.createDisabledImage(
                                    (new ImageIcon(ImageIO.read(new File(obrazek.getPath())).getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT)).getImage())))));
                        } else {
                            System.out.println(Objects.equals(modeComboBox.getSelectedItem(), "Select images to be presented") + ", " + !imageIcons.get(i).selected);
                            imageIcons.get(i).jbutton = (new JButton(new ImageIcon(ImageIO.read(new File(obrazek.getPath())).getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT))));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imageIcons.get(i).jbutton.setBounds(widthPos, yPos, size.width, size.height);
                    widthPos = widthPos + size.width + 10;

                    if (Objects.equals(modeComboBox.getSelectedItem(), "Single picture")) {
                        imageIcons.get(i).jbutton.removeActionListener(this::selectingImages);
                        imageIcons.get(i).jbutton.addActionListener(this::clickityClickIsThatADick);
                    } else if (Objects.equals(modeComboBox.getSelectedItem(), "Select images to be presented")) {
                        imageIcons.get(i).jbutton.removeActionListener(this::clickityClickIsThatADick);
                        imageIcons.get(i).jbutton.addActionListener(this::selectingImages);
                    }

                    imageIcons.get(i).jbutton.setVisible(true);
                    imageIcons.get(i).jbutton.setFocusPainted(true);
                    imageIcons.get(i).jbutton.setContentAreaFilled(false);
                    imageIcons.get(i).jbutton.setMargin(new Insets(0, 0, 0, 0));
                    imageIcons.get(i).jbutton.setBorder(new LineBorder(new Color(0, 0, 0), 0));
                    menu.add(imageIcons.get(i).jbutton);
                    widthPos++;
                } else System.out.println("extension not ok");
            }
        }

        prepare(menu);
    }

    public void beginPresenting() {
        slideNo = 0;
        try {
            bigPic.setIcon(new ImageIcon(ImageIO.read(obrazky[findPosOfXthSelection(slideNo)]).getScaledInstance(bigPic.getWidth(), bigPic.getHeight(), Image.SCALE_DEFAULT)));
            slideNo++;
            bigPic.setVisible(true);
            bigPic.removeActionListener(this::clickityClickIsThatADick);
            bigPic.addActionListener(this::stopPresenting);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There was an error when beginning to present.");
            presentationTimerTask.cancel();
            stopPresenting(null);
        }

        presentationTimerTask = new TimerTask() {
            @Override
            public void run() {
                nextSlide();
            }
        };
        presentationTimer.scheduleAtFixedRate(presentationTimerTask, 0, (int) (parseDouble(Objects.requireNonNull(imageRateComboBox.getSelectedItem()).toString().replace("s", "")) * 1000));

        parent.frame.repaint();
        parent.frame.revalidate();
    }

    public void nextSlide() {
        try {
            bigPic.setIcon(new ImageIcon(ImageIO.read(obrazky[findPosOfXthSelection(slideNo)]).getScaledInstance(bigPic.getWidth(), bigPic.getHeight(), Image.SCALE_DEFAULT)));
            slideNo++;
        } catch (
                Exception e) {
            e.printStackTrace();
            System.out.println("There was an error when showing next slide.");
            modeComboBox.setSelectedItem("Single picture");
        }

        parent.frame.repaint();
        parent.frame.revalidate();
    }

    public void stopPresenting(ActionEvent e) {
        presentationTimerTask.cancel();
        bigPic.setVisible(false);
        bigPic.removeActionListener(this::stopPresenting);
        bigPic.addActionListener(this::clickityClickIsThatADick);
        modeComboBox.setSelectedItem("Single picture");
    }

    public void clickityClickIsThatADick(ActionEvent e) {
        if (e.getSource().equals(bigPic)) {
            System.out.println("BigClick");
            bigPic.setIcon(null);
            bigPic.setVisible(false);
        }

        for (int i = 0; i < imageIcons.size(); i++) {
            if (imageIcons.get(i).jbutton.equals(e.getSource())) {
                BufferedImage img;
                try {
                    img = ImageIO.read(new File(obrazky[i].getPath()));
                    bigPic.setIcon(new ImageIcon(img.getScaledInstance(bigPic.getWidth(), bigPic.getHeight(), Image.SCALE_DEFAULT)));
                    bigPic.setVisible(true);
                } catch (IOException ioE) {
                    ioE.printStackTrace();
                }
            }
        }
    }

    private void clickityTwoElectricBoogaloo(ActionEvent e) {
        boolean isBig = true;
        Rectangle size = null;
        if (Objects.equals(windowSizeComboBox.getSelectedItem(), "800x600")) {
            parent.frame.setSize(800, 638);
            try {
                Thread.sleep(50);
            } catch (Exception ignored) {
            }
            bar.setBounds(0, 0, 800, 200);
            galleryLabel.setBounds(2, 2, 796, 25);
            windowSizeComboBox.setBounds(120, 35, 635, 30);
            bar.setLayout(null);
            iconSizeComboBox.setBounds(120, 75, 635, 30);
            modeComboBox.setBounds(120, 115, 635, 30);
            imageRateComboBox.setBounds(120, 155, 635, 30);
            bigPic.setBounds(0, 0, 800, 600);
        }
        if (Objects.equals(windowSizeComboBox.getSelectedItem(), "640x480")) {
            isBig = false;
            parent.frame.setSize(640, 518);
            try {
                Thread.sleep(50);
            } catch (Exception ignored) {
            }
            bar.setBounds(0, 0, 640, 200);
            galleryLabel.setBounds(2, 2, 636, 25);
            windowSizeComboBox.setBounds(120, 35, 425, 30);
            bar.setLayout(null);
            iconSizeComboBox.setBounds(120, 75, 425, 30);
            modeComboBox.setBounds(120, 115, 425, 30);
            imageRateComboBox.setBounds(120, 155, 425, 30);
            bigPic.setBounds(0, 0, 640, 480);
        }

        if (Objects.equals(iconSizeComboBox.getSelectedItem(), "Default")) {
            size = new Rectangle(60, 45);
        } else if (Objects.equals(iconSizeComboBox.getSelectedItem(), "Tiny")) {
            size = new Rectangle(16, 12);
        } else if (Objects.equals(iconSizeComboBox.getSelectedItem(), "Smol")) {
            size = new Rectangle(32, 24);
        } else if (Objects.equals(iconSizeComboBox.getSelectedItem(), "Humongous")) {
            size = new Rectangle(100, 75);
        }

        if (Objects.equals(modeComboBox.getSelectedItem(), "Presentation mode")) {
            beginPresenting();
        }

        menuSetup(isBig, size);
        parent.frame.repaint();
        parent.frame.revalidate();
    }

    public void selectingImages(ActionEvent e) {
        System.out.println("Selecting images: " + e.getSource() + ", " + e.getActionCommand());
        for (SelectableImageIcon sii : imageIcons) {
            System.out.println(sii.jbutton);
            System.out.println(e.getSource());
            if (e.getSource().equals(sii.jbutton)) {
                sii.toggleSelect();
                System.out.println("Is equal");
            } else System.out.println("Is not equal");
            System.out.println();
        }
        clickityTwoElectricBoogaloo(null);
    }

    public int findPosOfXthSelection(int desired) {
        int foundValids = -1;
        for (int i = 0; i < imageIcons.size(); i++) {
            if (imageIcons.get(i).selected) {
                foundValids++;
            }
            if (foundValids == desired) {
                return i;
            }
        }
        System.out.println("Not enough selected images, wrapping around.");

        int currentValids = -1;
        for (int i = 0; i < imageIcons.size(); i++) {
            if (imageIcons.get(i).selected) {
                currentValids++;
            }
            if (currentValids == desired % (foundValids + 1)) {
                return i;
            }
        }

        System.out.println("Error, no selected images found");
        return -1;
    }

    public void prepare(JComponent o) {
        o.setVisible(true);
        this.add(o, Integer.valueOf(1));
    }

    public void prepare(JComponent o, int z) {
        o.setVisible(true);
        this.add(o, Integer.valueOf(z));
    }

    public boolean checkExtension(String ext) {
        for (String allowed : allowedImageExtensions) {
            if (ext.toLowerCase().equals(allowed)) {
                return true;
            }
        }

        return false;
    }
}
