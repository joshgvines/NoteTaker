package com.notetaker.ui.menu.actions;

import com.notetaker.service.ActionErrorHandler;
import com.notetaker.service.TreeService;
import com.notetaker.ui.panels.NotesEditorPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Will delete the file selected in the SideNavigationPanel and is open in the NotesEditorPanel.
 */
public class DeleteFileAction implements ActionListener {

    private final Logger LOG = LogManager.getLogger(getClass());

    private TreeService<File> treeService;
    private Component parent;

    public DeleteFileAction(TreeService treeService, Component parent) {
        this.treeService = treeService;
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File openFile = treeService.getSelectedContent();
        if (openFile == null || !openFile.isFile()) {
            LOG.debug("Current selected file was null or was not a file.");
            return;
        }
        deleteFile(openFile);
    }

    private void deleteFile(File openFile) {
        try {
            openFile.delete();
            NotesEditorPanel.load();
            treeService.removeNode(treeService.getSelectedNode());
        } catch (Exception ex) {
            String msg = "Failed to delete file.";
            ActionErrorHandler.handleFailure(ex, msg, LOG, parent);
        }
    }

}
