package com.notetaker.ui.menu.actions;

import com.notetaker.ui.panels.NotesEditorPanel;
import com.notetaker.ui.panels.SideNavigationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class UpdateExistingFileAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            File navLocationFile = SideNavigationPanel.getLocation();
            if (navLocationFile != null && navLocationFile.exists()) {
                File openFile = NotesEditorPanel.getOpenFileInEditor();
                if (openFile != null && openFile.exists()) {
                    updateFile(openFile);
                    NotesEditorPanel.load();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateFile(File openFile) {
        String currentText = NotesEditorPanel.getCurrentText();
        try {
            Files.writeString(openFile.toPath(), currentText, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
