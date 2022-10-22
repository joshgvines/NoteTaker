package com.notetaker.ui.menu.actions;

import com.notetaker.service.FileTreeService;
import com.notetaker.ui.panels.NotesEditorPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Will delete the file selected in the SideNavigationPanel and is open in the NotesEditorPanel.
 */
public class DeleteFileAction implements ActionListener {

    private FileTreeService fileTreeService;

    public DeleteFileAction(FileTreeService fileTreeService) {
        this.fileTreeService = fileTreeService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File openFile = fileTreeService.getSelectedFile();
        if (openFile != null && openFile.isFile()) {
            openFile.delete();

            NotesEditorPanel.load();
            fileTreeService.removeNode(fileTreeService.getSelectedNode());
        }
    }

}
