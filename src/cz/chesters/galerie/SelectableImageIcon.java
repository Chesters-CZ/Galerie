package cz.chesters.galerie;

import javax.swing.*;

public class SelectableImageIcon {
    public JButton jbutton;
    public boolean selected;

    public SelectableImageIcon(JButton jbutton) {
        this.jbutton = jbutton;
        selected = true;
    }

    public SelectableImageIcon(JButton jbutton, boolean selected) {
        this.jbutton = jbutton;
        this.selected = selected;
    }

    public void select() {
        this.selected = true;
    }

    public void unselect() {
        this.selected = false;
    }

    public void toggleSelect() {
        this.selected = !this.selected;
    }
}
