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
