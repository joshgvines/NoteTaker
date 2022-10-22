package com.notetaker.service;

import com.notetaker.ui.panels.actions.FileSelectedAction;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import java.io.File;

public class NavigationFileTreeService implements FileTreeService {

    private DefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;
    private JTree tree;

    @Override
    public JTree buildTree(File fileRoot) {
        root = new DefaultMutableTreeNode(new FileNode(fileRoot));
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);

        tree.addTreeSelectionListener(new FileSelectedAction(this));
        createChildren(fileRoot, root);
        return tree;
    }

    @Override
    public void addNode(File file) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(new FileNode(file));
        root.add(node);
        treeModel.reload(root);
    }

    @Override
    public void updateNode(File toFile) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(new FileNode(toFile));
        int index = root.getIndex((DefaultMutableTreeNode) tree.getLastSelectedPathComponent());
        root.remove(index);
        root.insert(node, index);
        treeModel.reload(root);
    }

    @Override
    public void removeNode(MutableTreeNode node) {
        treeModel.removeNodeFromParent(node);
        treeModel.reload(root);
    }

    @Override
    public File getLocation() {
//        DefaultMutableTreeNode node = (DefaultMutableTreeNode) root.getUserObject();
        File file = ((FileNode) root.getUserObject()).getFile();
        return file;
    }

    @Override
    public File getSelectedFile() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        File file = ((FileNode) node.getUserObject()).getFile();
        return file;
    }

    @Override
    public MutableTreeNode getSelectedNode() {
        return (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
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

}
