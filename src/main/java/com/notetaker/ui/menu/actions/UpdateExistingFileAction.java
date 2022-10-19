package com.notetaker.ui.menu.actions;

import com.notetaker.ui.panels.NotesEditorPanel;
import com.notetaker.ui.panels.SideNavigationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Will overwrite an existing file with changes made in the NotesEditorPanel.
 */
public class UpdateExistingFileAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        File navLocationFile = SideNavigationPanel.getLocation();
        if (navLocationFile == null || !navLocationFile.isDirectory()) {
            return;
        }

        try {
            File openFile = NotesEditorPanel.getOpenFileInEditor();
            if (openFile != null && openFile.isFile()) {
                String currentText = NotesEditorPanel.getCurrentText();
                Files.writeString(openFile.toPath(), currentText, StandardCharsets.UTF_8);

                // Get the new file into memory in the editor panel. Not just the TextArea.
                NotesEditorPanel.load();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
