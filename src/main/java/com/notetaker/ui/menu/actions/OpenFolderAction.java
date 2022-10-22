package com.notetaker.ui.menu.actions;

import com.notetaker.service.FileTreeService;
import com.notetaker.ui.panels.SideNavigationPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Will change the current directory of the side navigation and file tree.
 */
public class OpenFolderAction implements ActionListener {

    private FileTreeService fileTreeService;
    private Component parent;

    public OpenFolderAction(FileTreeService fileTreeService, Component parent) {
        this.fileTreeService = fileTreeService;
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fChooser = new JFileChooser();
        fChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        File location = fileTreeService.getLocation();
        if (location == null) {
            return;
        }

        if (fChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            if (!fileTreeService.getLocation().equals(fChooser.getSelectedFile())) {
                File file = fChooser.getSelectedFile();
                SideNavigationPanel.setLocation(file);
            }
        }
    }

}
