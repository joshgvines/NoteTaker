package com.notetaker.ui.panels;

import com.notetaker.service.TreeService;
import com.notetaker.service.NavigationTreeService;
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

        TreeService treeService = new NavigationTreeService();
        SideNavigationPanel sideNavPanel = new SideNavigationPanel(treeService);

        JSplitPane splitNav = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                sideNavPanel,
                NotesEditorPanel.getInstance());

        splitNav.setDividerLocation(200);

        jFrameWindow.add(splitNav);
        jFrameWindow.setJMenuBar(new MainMenuBar(treeService));
    }

    public void show() {
        jFrameWindow.setVisible(true);
    }

}
