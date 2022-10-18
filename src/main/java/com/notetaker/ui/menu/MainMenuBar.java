package com.notetaker.ui.menu;

import javax.swing.*;

public class MainMenuBar {

    private static JMenuBar mainMenuBar = null;

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
        } finally {
            mainMenuBar.validate();
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
