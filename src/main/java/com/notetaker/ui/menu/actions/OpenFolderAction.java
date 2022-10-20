package com.notetaker.ui.menu.actions;

import com.notetaker.ui.panels.SideNavigationPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Will change the current directory of the side navigation and file list.
 */
public class OpenFolderAction implements ActionListener {

    private Component parent;

    public OpenFolderAction(Component parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fChooser = new JFileChooser();
        fChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (SideNavigationPanel.getLocation() == null) {
            return;
        }

        if (fChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            if (!SideNavigationPanel.getLocation().equals(fChooser.getSelectedFile())) {
                SideNavigationPanel.setLocation(fChooser.getSelectedFile());
                SideNavigationPanel.load();
            }
        }
    }

}
