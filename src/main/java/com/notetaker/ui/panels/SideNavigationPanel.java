package com.notetaker.ui.panels;

import com.notetaker.ui.panels.actions.FileSelectedAction;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SideNavigationPanel {

    private static JPanel sideNavePanel;
    // TODO: Better Default value
    private static File location;
    private static FileSelectedAction fileClickedEvent;
    private static JList<String> navList;
    private static DefaultListModel listModel;

    private SideNavigationPanel() {
        initialize();
    }

    private static void initialize() {
        sideNavePanel = new JPanel(new BorderLayout());
        fileClickedEvent = new FileSelectedAction();
        location = new File("\\Users\\");
        listModel = new DefaultListModel();
        navList = new JList<>();
        load();
    }

    public static void load() {
        try {
            sideNavePanel.removeAll();
            loadFilesFromCurrentLocation();
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

    private static JList<String> loadFilesFromCurrentLocation() {
        listModel.clear();

        if (location != null && location.isDirectory()) {
            File[] files = location.listFiles();
            for (int i = 0; i < files.length; i++) {
                listModel.addElement(files[i].getName());
            }
            navList.setModel(listModel);
            fileClickedEvent.setFileList(navList);
            navList.addListSelectionListener(fileClickedEvent);
        } else {
            listModel.addElement("No Files Found");
        }
        return navList;
    }

    static synchronized JPanel getInstance() {
        if (sideNavePanel == null) {
            initialize();
        }
        return sideNavePanel;
    }
}
