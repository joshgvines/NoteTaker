package com.notetaker.ui.panels.actions;

import com.notetaker.service.FileTreeService;
import com.notetaker.ui.panels.NotesEditorPanel;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.io.File;

/**
 * Will update which file is displayed in the NotesEditorPanel.
 */
public class FileSelectedAction implements TreeSelectionListener {

    private FileTreeService FileTreeService;

    public FileSelectedAction(FileTreeService FileTreeService) {
        this.FileTreeService = FileTreeService;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        File selectedFile = FileTreeService.getSelectedFile();
        if (selectedFile == null || !selectedFile.isFile()) {
            return;
        }

        // TODO: Eventually will open new tab
        NotesEditorPanel.setOpenFileInEditor(selectedFile);
        NotesEditorPanel.load();
    }
}
