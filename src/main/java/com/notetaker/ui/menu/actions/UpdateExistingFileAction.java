package com.notetaker.ui.menu.actions;

import com.notetaker.service.FileTreeService;
import com.notetaker.ui.panels.NotesEditorPanel;

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
 * Will overwrite or rename an existing file with changes made in the NotesEditorPanel.
 */
public class UpdateExistingFileAction implements ActionListener {

    public enum UpdateFlag {
        IS_OVERWRITE,
        IS_NAME_CHANGE
    }

    private FileTreeService fileTreeService;
    private Component parent;
    private UpdateFlag updateFlag;

    public UpdateExistingFileAction(FileTreeService fileTreeService,
                                    UpdateFlag updateFlag,
                                    Component parent) {
        this.fileTreeService = fileTreeService;
        this.updateFlag = updateFlag;
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File location = fileTreeService.getLocation();
        if (location == null || !location.isDirectory()) {
            return;
        }

        try {
            File openFile = fileTreeService.getSelectedFile();
            if (openFile != null && openFile.isFile()) {

                // Need to keep Java 11 Compatible =(
                switch (updateFlag) {
                    case IS_OVERWRITE: overwriteFile(openFile);
                        break;
                    case IS_NAME_CHANGE: renameFile(openFile);
                        break;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Will overwrite the selected file contents.
     * TODO: Should I compare to make sure the file actually has changes to be overwritten
     *
     * @param openFile
     * @throws IOException
     */
    private void overwriteFile(File openFile) throws IOException {
        String currentText = NotesEditorPanel.getCurrentText();
        // Do not check for empty string. The user may want that to happen.
        if (currentText == null) {
            return;
        }

        Files.writeString(openFile.toPath(), currentText, StandardCharsets.UTF_8);

        // Get the new file into memory in the editor panel. Not just the TextArea.
        NotesEditorPanel.load();
    }

    /**
     * Will rename a selected file if file name is not already taken.
     * TODO: Probably a good idea to move away from JOptionPane
     *
     * @param openFile
     * @throws IOException
     */
    private void renameFile(File openFile) throws IOException {
        String newFileName = JOptionPane.showInputDialog(parent, "New Name:");
        if (newFileName == null || newFileName.isEmpty()) {
            return;
        }

        String location = fileTreeService.getLocation().getPath();
        File newFileToMove = new File(location + "\\" + newFileName);
        if (newFileToMove.exists()) {
            JOptionPane.showMessageDialog(parent, "File Name Is Already In Use");
        } else {
            Path newPath = newFileToMove.toPath();
            Files.move(openFile.toPath(), newPath);
            fileTreeService.updateNode(newFileToMove);
        }
    }

}
