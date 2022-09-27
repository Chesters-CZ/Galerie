package cz.chesters.stopky;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class Galerie {
    JFrame frame = new JFrame("Galerie");

    Cibuloid onion = new Cibuloid();

    JToolBar bar = new JToolBar();
    JComboBox<String> box = new JComboBox<>(new String[]{"800x600", "640x480"});
    JLabel settings = new JLabel("Settings:");
    JLabel resolution = new JLabel("Resolution");


    public void doShit() {
        galerijuj();
    }

    public void galerijuj() {
        frame.setSize(800, 638); // 4:3 plus horní lišta na windowsech
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setContentPane(onion);
        frame.setResizable(false);

        onion.setBounds(0,20,800,600);
        onion.setVisible(true);

        box.setEditable(false);
        box.setBounds(120,75,635,30);
        box.setVisible(true);
        box.addActionListener(this::clickityTwoElectricBoogaloo);
        box.setFont(new Font(Font.DIALOG, Font.BOLD, 14));

        settings.setBounds(2, 2,796,25);
        settings.setVisible(true);
        settings.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        settings.setHorizontalAlignment(SwingConstants.CENTER);
        bar.add(settings);

        resolution.setBounds(25,75,100,30);
        resolution.setVisible(true);
        resolution.setHorizontalAlignment(SwingConstants.LEFT);
        bar.add(resolution);

        bar.setBounds(0,0,800,200);
        bar.setFloatable(false);
        bar.add(box);
        bar.setVisible(true);
        bar.setLayout(null);

        frame.add(bar);

        frame.setVisible(true);
    }

    private void clickityTwoElectricBoogaloo(ActionEvent e) {
        if (Objects.equals(box.getSelectedItem(), "800x600")){
            frame.setSize(800,638);
            bar.setBounds(0,0,800,200);
            settings.setBounds(2, 2,796,25);
            box.setBounds(120,75,635,30);
            frame.setLayout(null);
            bar.setLayout(null);
            frame.getContentPane().setLayout(null);
            box.setLayout(null);
            SwingUtilities.updateComponentTreeUI(frame);
        } else if (Objects.equals(box.getSelectedItem(), "640x480")){
            frame.setSize(640,518);
            bar.setBounds(0,0,640,200);
            settings.setBounds(2, 2,636,25);
            box.setBounds(120,75,475,30);
            frame.setLayout(null);
            bar.setLayout(null);
            frame.getContentPane().setLayout(null);
            box.setLayout(null);
            SwingUtilities.updateComponentTreeUI(frame);
        }
    }

}
