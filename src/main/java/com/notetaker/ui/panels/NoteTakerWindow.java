package com.notetaker.ui.panels;

import com.notetaker.service.FileTreeService;
import com.notetaker.service.NavigationFileTreeService;
import com.notetaker.ui.menu.MainMenuBar;

import javax.swing.*;

public class NoteTakerWindow {

    private static final String NAME = "NoteTaker";

    // Keep JFrame object encapsulated.
    private JFrame jFrameWindow;

    public NoteTakerWindow() {
        initialize();
    }

    private void initialize() {
        jFrameWindow = new JFrame(NAME);

        jFrameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrameWindow.setSize(1200, 800);
        jFrameWindow.setLocationRelativeTo(null);

        FileTreeService fileTreeService = new NavigationFileTreeService();
        SideNavigationPanel sideNavPanel = new SideNavigationPanel(fileTreeService);

        JSplitPane splitNav = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                sideNavPanel,
                NotesEditorPanel.getInstance());

        splitNav.setDividerLocation(200);

        jFrameWindow.getContentPane().add(splitNav);
        jFrameWindow.setJMenuBar(new MainMenuBar(fileTreeService));
    }

    public void show() {
        jFrameWindow.setVisible(true);
    }

}
