package com.notetaker.ui.menu.actions;

import com.notetaker.service.TreeService;
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

    private TreeService<File> treeService;
    private Component parent;

    public OpenFolderAction(TreeService treeService, Component parent) {
        this.treeService = treeService;
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fChooser = new JFileChooser();
        fChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        File location = treeService.getRootContent();
        if (location == null) {
            return;
        }

        if (fChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            if (!treeService.getRootContent().equals(fChooser.getSelectedFile())) {
                File file = fChooser.getSelectedFile();
                SideNavigationPanel.setLocation(file);
            }
        }
    }

}
