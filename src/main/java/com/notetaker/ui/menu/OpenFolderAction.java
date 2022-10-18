package com.notetaker.ui.menu;

import com.notetaker.ui.panels.SideNavigationPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class OpenFolderAction implements ActionListener {

    private Component parent;

    public OpenFolderAction(Component parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fChooser = new JFileChooser();
        fChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (fChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            SideNavigationPanel.reloadLocation(fChooser.getSelectedFile());
        }
    }
}
