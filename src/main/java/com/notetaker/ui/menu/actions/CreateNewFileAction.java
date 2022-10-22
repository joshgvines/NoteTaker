package com.notetaker.ui.menu.actions;

import com.notetaker.service.NavigationTreeService;
import com.notetaker.service.TreeService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Will create a new file 'untitled' if the file does not exist.
 * If the file exists, will increment the default name by one.
 * ex: 'untitled 1..n'
 */
public class CreateNewFileAction implements ActionListener {

    private TreeService<File> treeService;

    public CreateNewFileAction(TreeService treeService) {
        this.treeService = treeService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File navLocationFile = treeService.getRootContent();
        if (navLocationFile == null || !navLocationFile.isDirectory()) {
            return;
        }

        try {
            File file = existingFileIncrement(navLocationFile);
            file.createNewFile();
            treeService.addNode(file);
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
