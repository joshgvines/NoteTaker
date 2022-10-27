package com.notetaker;

import com.notetaker.ui.panels.NoteTakerWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class Launcher {

    private static final Logger LOG = LogManager.getLogger(Launcher.class);

    public static void main(String[] args) {

        LOG.info("Starting Application");

        // Create Single JFrame Thread. JFrame is NOT thread safe.
        SwingUtilities.invokeLater(() -> {
            NoteTakerWindow window = new NoteTakerWindow();
            window.show();
        });

    }
}