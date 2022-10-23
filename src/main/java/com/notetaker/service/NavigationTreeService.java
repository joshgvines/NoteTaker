package com.notetaker.service;

import com.notetaker.ui.panels.actions.FileSelectedAction;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import java.io.File;

public class NavigationTreeService implements TreeService<File> {

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
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new FileNode(file));

        DefaultMutableTreeNode tmpNode = (DefaultMutableTreeNode) getSelectedNode();
        File currentFile = getSelectedContent();
        DefaultMutableTreeNode parent;

        if (currentFile != null && currentFile.isDirectory()) {
            parent = tmpNode;
        } else {
            parent = (DefaultMutableTreeNode) tmpNode.getParent();
        }
        treeModel.insertNodeInto(newNode, parent, parent.getChildCount());
    }

    @Override
    public void updateNode(File toFile) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) getSelectedNode();
        addNode(toFile);
        treeModel.removeNodeFromParent(node);
    }

    @Override
    public void removeNode(MutableTreeNode node) {
        treeModel.removeNodeFromParent(node);
        tree.clearSelection();
    }

    @Override
    public File getRootContent() {
        FileNode node = getFileNode(root);
        if (node != null) {
            File file = node.getFile();
            return file;
        }
        return null;
    }

    @Override
    public File getSelectedContent() {
        FileNode node = getFileNode(tree.getLastSelectedPathComponent());
        if (node != null) {
            File file = node.getFile();
            return file;
        }
        return null;
    }

    private FileNode getFileNode(Object node) {
        if (node instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode defaultNode = (DefaultMutableTreeNode) node;
            node = defaultNode.getUserObject();
        }
        if (node instanceof FileNode) {
            return (FileNode) node;
        }
        return null;
    }

    @Override
    public MutableTreeNode getSelectedNode() {
        return (MutableTreeNode) tree.getLastSelectedPathComponent();
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
