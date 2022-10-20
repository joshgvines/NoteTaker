package com.notetaker.ui.menu.actions;

import com.notetaker.ui.panels.NotesEditorPanel;
import com.notetaker.ui.panels.SideNavigationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Will delete the file selected in the SideNavigationPanel and is open in the NotesEditorPanel.
 */
public class DeleteFileAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        File openFile = NotesEditorPanel.getOpenFileInEditor();
        if (openFile != null && openFile.isFile()) {
            openFile.delete();

            NotesEditorPanel.load();
            SideNavigationPanel.load();
        }
    }

}
