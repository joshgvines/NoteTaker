package com.notetaker.ui.menu;

import com.notetaker.service.FileTreeService;

import javax.swing.*;

public class MainMenuBar extends JMenuBar {

    private FileTreeService fileTreeService;

    public MainMenuBar(FileTreeService fileTreeService) {
        super();
        this.fileTreeService = fileTreeService;
        initialize();
    }

    private void initialize() {
        try {
            this.add(new FileMenu(fileTreeService));
            this.add(new EditMenu(fileTreeService));
        } finally {
            this.revalidate();
        }
    }

}
