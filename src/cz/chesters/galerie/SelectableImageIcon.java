package cz.chesters.galerie;

import javax.swing.*;

public class SelectableImageIcon {
    public JButton jbutton;
    public boolean selected;

    public SelectableImageIcon(JButton jbutton) {
        this.jbutton = jbutton;
        selected = false;
    }

    public void toggleSelect() {
        this.selected = !this.selected;
    }
}
