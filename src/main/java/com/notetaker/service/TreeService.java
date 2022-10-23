package com.notetaker.service;

import javax.swing.*;
import javax.swing.tree.MutableTreeNode;

/**
 * Service to decouple tree logic form the JPanel classes that will use it.
 *
 * @param <T> The type which the tree will contain.
 */
public interface TreeService<T> {

    /**
     * Will create a new tree based on a root object given.
     *
     * @param fileRoot
     * @return
     */
    JTree buildTree(T fileRoot);

    void addNode(T file);

    void updateNode(T toFile);

    void removeNode(MutableTreeNode treeNode);

    /**
     * Will get the current root node content in the tree.
     * From the perspective a File tree, this would reveal the current open location.
     *
     * @return Given type T
     */
    T getRootContent();

    /**
     * Will get the currently selected node content if the tree contains said listener.
     * From the perspective a File tree, this would reveal the current location of the selected file.
     *
     * @return Given type T
     */
    T getSelectedContent();

    /**
     * Will get the currently selected node (Not the content of the node) if the tree contains said listener.
     * From the perspective a File tree, this would reveal the current location of the selected file.
     *
     * @return MutableTreeNode at selected location in tree.
     */
    MutableTreeNode getSelectedNode();
}
