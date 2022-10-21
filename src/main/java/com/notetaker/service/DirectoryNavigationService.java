package com.notetaker.service;

import com.notetaker.ui.panels.actions.FileSelectedAction;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;

public class DirectoryNavigationService {

    private DefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;
    private JTree tree;

    public JTree buildTree(File fileRoot) {
        root = new DefaultMutableTreeNode(new FileNode(fileRoot));
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);

        tree.addTreeSelectionListener(new FileSelectedAction(tree));
        createChildren(fileRoot, root);
        return tree;
    }

    private void createChildren(File fileRoot, DefaultMutableTreeNode root) {
        if (fileRoot == null) {
            return;
        }
        File[] files = fileRoot.listFiles();
        if (files != null) {
            for (File child : files) {
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new FileNode(child));
                root.add(childNode);
                if (child.isDirectory()) {
                    createChildren(child, childNode);
                }
            }
        }
    }

    public JTree getTree() {
        return tree;
    }
}
