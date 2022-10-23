package com.notetaker.ui.menu.actions;

import com.notetaker.service.TreeService;
import com.notetaker.ui.panels.NotesEditorPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Will delete the file selected in the SideNavigationPanel and is open in the NotesEditorPanel.
 */
public class DeleteFileAction implements ActionListener {

    private TreeService<File> treeService;

    public DeleteFileAction(TreeService treeService) {
        this.treeService = treeService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File openFile = treeService.getSelectedContent();
        if (openFile != null && openFile.isFile()) {
            openFile.delete();

            NotesEditorPanel.load();
            treeService.removeNode(treeService.getSelectedNode());
        }
    }

}
