package com.notetaker.ui.menu;

import com.notetaker.service.TreeService;

import javax.swing.*;

public class MainMenuBar extends JMenuBar {

    private TreeService treeService;

    public MainMenuBar(TreeService treeService) {
        super();
        this.treeService = treeService;
        initialize();
    }

    private void initialize() {
        try {
            this.add(new FileMenu(treeService));
            this.add(new EditMenu(treeService));
        } finally {
            this.revalidate();
        }
    }

}
