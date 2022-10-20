package com.notetaker;

import com.notetaker.ui.panels.NoteTakerWindow;

import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {

        // Create Single JFrame Thread. JFrame is NOT thread safe.
        SwingUtilities.invokeLater(() -> {
            NoteTakerWindow window = new NoteTakerWindow();
            window.show();
        });

    }
}