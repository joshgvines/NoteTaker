package com.notetaker.ui.menu;

import javax.swing.*;

public class MainMenuBar {

    private static JMenuBar mainMenuBar;

    private MainMenuBar() {
        initialize();
    }

    private static void initialize() {
        mainMenuBar = new JMenuBar();
        load();
    }

    protected static void load() {
        try {
            mainMenuBar.add(FileMenu.getInstance());
            mainMenuBar.add(EditMenu.getInstance());
        } finally {
            mainMenuBar.revalidate();
        }
    }

    public static synchronized JMenuBar getInstance() {
        if (mainMenuBar == null) {
            initialize();
            return mainMenuBar;
        }
        return mainMenuBar;
    }

}
