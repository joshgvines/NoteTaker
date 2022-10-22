package com.notetaker.service;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.io.File;

public interface FileTreeService {

    JTree buildTree(File fileRoot);

    void addNode(File file);

    void updateNode(File toFile);

    void removeNode(MutableTreeNode treeNode);

    File getLocation();

    File getSelectedFile();

    MutableTreeNode getSelectedNode();
}
