package com.notetaker.ui.menu;

import com.notetaker.service.TreeService;

import javax.swing.*;

public class MainMenuBar extends JMenuBar {

    public MainMenuBar(TreeService treeService) {
        super();
        this.add(new FileMenu(treeService));
        this.add(new EditMenu(treeService));
    }

}
