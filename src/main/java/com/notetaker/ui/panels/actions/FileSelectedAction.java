package com.notetaker.ui.panels.actions;

import com.notetaker.service.FileNode;
import com.notetaker.ui.panels.NotesEditorPanel;
import com.notetaker.ui.panels.SideNavigationPanel;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.io.File;

/**
 * Will update which file is displayed in the NotesEditorPanel.
 */
public class FileSelectedAction implements TreeSelectionListener {

    private JTree tree;

    public FileSelectedAction(JTree tree) {
        this.tree = tree;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {

        String selectedFileName = null;
        TreePath treePath = tree.getSelectionPath();
        if (treePath != null) {
            Object filePathToAdd = treePath.getLastPathComponent();
            if (filePathToAdd instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) filePathToAdd;
                FileNode fileNode = (FileNode) defaultMutableTreeNode.getUserObject();
                selectedFileName = fileNode.getFile().getPath();
            }
        }

        if (selectedFileName == null) {
            return;
        }

        // TODO: Eventually will open new tab
        File fileToOpen = new File(selectedFileName);

        NotesEditorPanel.setOpenFileInEditor(fileToOpen);
        NotesEditorPanel.load();
    }
}
