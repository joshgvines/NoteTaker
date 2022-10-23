package com.notetaker.ui.panels.actions;

import com.notetaker.service.TreeService;
import com.notetaker.ui.panels.NotesEditorPanel;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.io.File;

/**
 * Will update which file is displayed in the NotesEditorPanel.
 */
public class FileSelectedAction implements TreeSelectionListener {

    private TreeService<File> TreeService;

    public FileSelectedAction(TreeService TreeService) {
        this.TreeService = TreeService;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        File selectedFile = TreeService.getSelectedContent();
        if (selectedFile == null) {
            return;
        }

        // TODO: Eventually will open new tab
        NotesEditorPanel.setOpenFileInEditor(selectedFile);
    }
}
