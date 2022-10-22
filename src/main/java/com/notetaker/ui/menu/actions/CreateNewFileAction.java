package com.notetaker.ui.menu.actions;

import com.notetaker.service.FileTreeService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Will create a new file 'untitled' if the file does not exist.
 * If the file exists, will increment the default name by one.
 * ex: 'untitled 1..n'
 */
public class CreateNewFileAction implements ActionListener {

    private FileTreeService fileTreeService;

    public CreateNewFileAction(FileTreeService fileTreeService) {
        this.fileTreeService = fileTreeService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File navLocationFile = fileTreeService.getLocation();
        if (navLocationFile == null || !navLocationFile.isDirectory()) {
            return;
        }

        try {
            File file = existingFileIncrement(navLocationFile);
            file.createNewFile();
            fileTreeService.addNode(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private File existingFileIncrement(File file) {
        String defaultName = file.getAbsolutePath() + "\\" + "untitled";
        File newFile = new File(defaultName);
        int i = 1;
        while (newFile.exists() && i < 999) {
            newFile = new File(defaultName + " " + i++);
        }
        return newFile;
    }

}
