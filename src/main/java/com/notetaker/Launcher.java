package com.notetaker;

import com.notetaker.ui.NoteTakerWindow;

import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {

        // Create Single JFrame Thread. JFrame is NOT thread safe.
        SwingUtilities.invokeLater(() -> {
//            try {
//                FlatLightLaf flatLightLaf = new FlatLightLaf();
//                UIManager.setLookAndFeel(flatLightLaf);
//            } catch( Exception ex ) {
//                System.err.println( "Failed to initialize LaF" );
//            }
            NoteTakerWindow window = new NoteTakerWindow();
            window.show();
        });

    }
}