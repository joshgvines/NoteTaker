package com.notetaker.ui.menu.actions;

import com.notetaker.ui.panels.SideNavigationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Will create a new file 'untitled' if the file does not exist.
 * If the file exists, will increment the default name by one.
 * ex: 'untitled 1..n'
 */
public class CreateNewFileAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        File navLocationFile = SideNavigationPanel.getLocation();
        if (navLocationFile == null || !navLocationFile.isDirectory()) {
            return;
        }

        try {
            existingFileIncrement(navLocationFile).createNewFile();
            SideNavigationPanel.load();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private File existingFileIncrement(File file) {
        String defaultName = file.getPath() + "\\" + "untitled";
        File newFile = new File(defaultName);
        int i = 1;
        while (newFile.exists() && i < 999) {
            newFile = new File(defaultName + " " + i++);
        }
        return newFile;
    }

}
