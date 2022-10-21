package com.notetaker.ui.panels;

import com.notetaker.service.DirectoryNavigationService;
import com.notetaker.ui.panels.actions.FileSelectedAction;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SideNavigationPanel {

    private static JPanel sideNavePanel;
    // TODO: Better Default value
    private static File location;
    private static DirectoryNavigationService dirNavSvc;

    private SideNavigationPanel() {
        initialize();
    }

    private static void initialize() {
        sideNavePanel = new JPanel(new BorderLayout());
        location = new File("\\Users\\");
        load();
    }

    public static void load() {
        try {
            sideNavePanel.removeAll();
            JScrollPane listScroller = new JScrollPane(loadFilesFromCurrentLocation());
            listScroller.setPreferredSize(new Dimension(80, 80));
            sideNavePanel.add(listScroller);
        } finally {
            sideNavePanel.revalidate();
        }
    }

    public static void setLocation(File newLocation) {
        location = newLocation;
    }

    public static File getLocation() {
        return location;
    }

    private static JTree loadFilesFromCurrentLocation() {
        if (dirNavSvc == null) {
            dirNavSvc = new DirectoryNavigationService();
        }
        return dirNavSvc.buildTree(location);
    }

    static synchronized JPanel getInstance() {
        if (sideNavePanel == null) {
            initialize();
        }
        return sideNavePanel;
    }
}
