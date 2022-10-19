package com.notetaker.ui.menu.actions;

import com.notetaker.ui.panels.SideNavigationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CreateNewFileAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            File navLocationFile = SideNavigationPanel.getLocation();
            if (navLocationFile != null && navLocationFile.exists()) {
                existingFileIncrement(navLocationFile).createNewFile();
                SideNavigationPanel.load();
            }
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
