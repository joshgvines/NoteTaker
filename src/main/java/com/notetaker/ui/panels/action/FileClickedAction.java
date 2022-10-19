package com.notetaker.ui.panels.action;

import com.notetaker.ui.panels.NotesEditorPanel;
import com.notetaker.ui.panels.SideNavigationPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.File;

/**
 * Will update what files are displayed in the NotesEditorPanel.
 */
public class FileClickedAction implements ListSelectionListener {

    private JList<String> fileList = null;

    public void setFileList(JList<String> fileList) {
        this.fileList = fileList;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() || fileList == null) {
            return;
        }
        final String selectedFileName = fileList.getSelectedValue();
        // TODO: Eventually will open new tab
        if (selectedFileName != null && !selectedFileName.isEmpty()) {
            File location = SideNavigationPanel.getLocation();
            String fileLocation = location.getPath() + "\\" + selectedFileName;
            File fileToOpen = new File(fileLocation);

            NotesEditorPanel.setOpenFileInEditor(fileToOpen);
            NotesEditorPanel.load();
        }
    }
}
