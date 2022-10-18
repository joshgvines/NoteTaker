package com.notetaker.ui;

import com.notetaker.ui.menu.MainMenuBar;
import com.notetaker.ui.panels.NotesEditorPanel;
import com.notetaker.ui.panels.SideNavigationPanel;

import javax.swing.*;
import java.awt.*;

public class NoteTakerWindow {

    private static final String NAME = "Note Taker";

    // Keep JFrame object encapsulated.
    private JFrame jFrameWindow;

    public NoteTakerWindow() {
        initialize();
    }

    private void initialize() {
        try {
            buildFrame();
            buildPanels();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void buildFrame() {
        jFrameWindow = new JFrame(NAME);
        jFrameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrameWindow.setSize(1200, 800);
        jFrameWindow.setLocationRelativeTo(null);
    }

    private void buildPanels() {
        JSplitPane splitnav = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                SideNavigationPanel.getInstance(),
                NotesEditorPanel.getInstance());

        splitnav.setDividerLocation(200);

        jFrameWindow.add(splitnav);
        jFrameWindow.setJMenuBar(MainMenuBar.getInstance());

        jFrameWindow.setBackground(Color.BLACK);
    }

    public void show() {
//        jFrameWindow.pack();
        jFrameWindow.setVisible(true);
    }

}
