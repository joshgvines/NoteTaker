package com.notetaker.ui.menu.actions;

import com.notetaker.ui.panels.SideNavigationPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenFolderAction implements ActionListener {

    private Component parent;

    public OpenFolderAction(Component parent) {
        this.parent = parent;
    }

    @Override
    /**
     * Will change the current directory of the side navigation and files.
     */
    public void actionPerformed(ActionEvent e) {
        JFileChooser fChooser = new JFileChooser();
        fChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (fChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            if (!SideNavigationPanel.getLocation().equals(fChooser)) {
                SideNavigationPanel.setLocation(fChooser.getSelectedFile());
                SideNavigationPanel.load();
            }
        }
    }
}
