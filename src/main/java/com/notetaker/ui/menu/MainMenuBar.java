package com.notetaker.ui.menu;

import javax.swing.*;

public class MainMenuBar {

    private static JMenuBar mainMenuBar;

    private MainMenuBar() {
        initialize();
    }

    private static void initialize() {
        mainMenuBar = new JMenuBar();
        mainMenuBar.add(new FileMenu());
    }

    public static JMenuBar getInstance() {
        if (mainMenuBar == null) {
            initialize();
            return mainMenuBar;
        }
        return null;
    }

}
