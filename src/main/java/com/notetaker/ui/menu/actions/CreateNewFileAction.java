package com.notetaker.ui.menu.actions;

import com.notetaker.service.TreeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Will create a new file 'untitled' if the file does not exist.
 * If the file exists, will increment the default name by one.
 * ex: 'untitled 1..n'
 */
public class CreateNewFileAction implements ActionListener {

    private final Logger LOG = LogManager.getLogger(getClass());

    private TreeService<File> treeService;

    public CreateNewFileAction(TreeService treeService) {
        this.treeService = treeService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File navLocationFile = treeService.getRootContent();
        if (navLocationFile == null || !navLocationFile.isDirectory()) {
            LOG.debug("Current location in navigation was null or not a directory.");
            return;
        }
        createNewFile(navLocationFile);
    }

    private void createNewFile(File navLocationFile) {
        try {
            File newFile = existingFileIncrement(navLocationFile);
            newFile.createNewFile();
            treeService.addNode(newFile);
        } catch (Exception ex) {
            String msg = "Failed to create file.";
            MenuActionErrorHandler.handleFailure(ex, msg, LOG, null);
        }
    }

    private File existingFileIncrement(File file) {
        String defaultName = file.getAbsolutePath() + File.separator + "untitled";
        File newFile = new File(defaultName);
        int i = 1;
        while (newFile.exists() && i < 999) {
            newFile = new File(defaultName + " " + i++);
        }
        return newFile;
    }

}
