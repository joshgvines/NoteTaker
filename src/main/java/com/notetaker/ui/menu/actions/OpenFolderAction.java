package com.notetaker.ui.menu.actions;

import com.notetaker.service.TreeService;
import com.notetaker.ui.panels.SideNavigationPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Will change the current directory of the side navigation and file tree.
 */
public class OpenFolderAction implements ActionListener {

    private final Logger LOG = LogManager.getLogger(getClass());

    private TreeService<File> treeService;
    private Component parent;
    private JFileChooser fileChooser;

    public OpenFolderAction(TreeService treeService, Component parent) {
        this.treeService = treeService;
        this.parent = parent;
        fileChooser = new JFileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File location = treeService.getRootContent();
        if (parent == null) {
            LOG.debug("Parent component was null");
            return;
        }
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int dialogMode = fileChooser.showOpenDialog(parent);
        if (dialogMode == JFileChooser.APPROVE_OPTION) {
            openNewLocation(location, fileChooser);
        }
    }

    private void openNewLocation(File location, JFileChooser fileChooser) {
        try {
            File file = fileChooser.getSelectedFile();
            if (file == null) {
                String msg = "Selected file was null";
                LOG.debug(msg);
                JOptionPane.showMessageDialog(parent, msg);
                return;
            }
            // Avoid rebuilding a tree in the same location.
            if (location == null || !location.equals(fileChooser.getSelectedFile())) {
                SideNavigationPanel.setLocation(file);
            }
        } catch (Exception ex) {
            final String msg = "Failed to open new location.";
            MenuActionErrorHandler.handleFailure(ex, msg, LOG, parent);
        }
    }

}
