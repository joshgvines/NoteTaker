package com.notetaker.ui.panels;

import com.notetaker.service.FileTreeService;
import com.notetaker.service.NavigationFileTreeService;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SideNavigationPanel extends JPanel {

    private static JPanel sideNavePanel;
    // TODO: Better Default value
    private static File location;
    private static FileTreeService fileTreeService;
    private static JScrollPane listScroller;

    public SideNavigationPanel(FileTreeService fileTreeService) {
        super(new BorderLayout());
        this.fileTreeService = fileTreeService;
        location = new File("\\Users\\");
        listScroller = new JScrollPane(fileTreeService.buildTree(location));
        listScroller.setPreferredSize(new Dimension(80, 80));
        initialize();
    }

    public void initialize() {
        try {
            this.removeAll();
            this.add(listScroller);
        } finally {
            this.revalidate();
        }
    }

    public static void setLocation(File newLocation) {
        location = newLocation;
        listScroller.setViewportView(fileTreeService.buildTree(location));
    }

}
