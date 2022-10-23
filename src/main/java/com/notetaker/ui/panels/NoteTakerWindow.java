package com.notetaker.ui.panels;

import com.notetaker.service.NavigationTreeService;
import com.notetaker.service.TreeService;
import com.notetaker.ui.menu.MainMenuBar;

import javax.swing.*;
import java.awt.*;

public class NoteTakerWindow {

    private static final String NAME = "NoteTaker";

    // Keep JFrame object encapsulated.
    private JFrame jFrameWindow;

    public NoteTakerWindow() {
        jFrameWindow = new JFrame(NAME);
        initialize();
    }

    private void initialize() {
        TreeService treeService = new NavigationTreeService();
        SideNavigationPanel sideNavPanel = new SideNavigationPanel(treeService);

        JSplitPane splitNav = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                sideNavPanel,
                NotesEditorPanel.getInstance());

        splitNav.setDividerLocation(200);

        jFrameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrameWindow.setPreferredSize(new Dimension(1200, 800));

        jFrameWindow.add(splitNav);
        jFrameWindow.setJMenuBar(new MainMenuBar(treeService));
    }

    public void show() {
        jFrameWindow.pack();
        jFrameWindow.setLocationRelativeTo(null);
        jFrameWindow.validate();
        jFrameWindow.setVisible(true);
    }

}
