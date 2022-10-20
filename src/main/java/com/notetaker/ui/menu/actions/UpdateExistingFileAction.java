package com.notetaker.ui.menu.actions;

import com.notetaker.ui.panels.NotesEditorPanel;
import com.notetaker.ui.panels.SideNavigationPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Will overwrite an existing file with changes made in the NotesEditorPanel.
 */
public class UpdateExistingFileAction implements ActionListener {

    public enum UpdateFlag {
        IS_OVERWRITE,
        IS_NAME_CHANGE
    }

    private Component parent;
    private UpdateFlag updateFlag;

    public UpdateExistingFileAction(UpdateFlag updateFlag, Component parent) {
        this.updateFlag = updateFlag;
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File navLocationFile = SideNavigationPanel.getLocation();
        if (navLocationFile == null || !navLocationFile.isDirectory()) {
            return;
        }

        try {
            File openFile = NotesEditorPanel.getOpenFileInEditor();
            if (openFile != null && openFile.isFile()) {

                switch (updateFlag) {
                    case IS_OVERWRITE -> overwriteFile(openFile);
                    case IS_NAME_CHANGE -> renameFile(openFile);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void overwriteFile(File openFile) throws IOException {
        String currentText = NotesEditorPanel.getCurrentText();
        Files.writeString(openFile.toPath(), currentText, StandardCharsets.UTF_8);

        // Get the new file into memory in the editor panel. Not just the TextArea.
        NotesEditorPanel.load();
    }

    private void renameFile(File openFile) throws IOException {
        String newFileName = JOptionPane.showInputDialog(parent, "New Name:");

        String location = SideNavigationPanel.getLocation().getPath();
        Path newPath = new File(location + "\\" + newFileName).toPath();
        Files.move(openFile.toPath(), newPath);

        SideNavigationPanel.load();
    }

}
