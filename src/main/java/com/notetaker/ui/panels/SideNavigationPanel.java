package com.notetaker.ui.panels;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.File;

public class SideNavigationPanel {

    private static JPanel sideNavePanel;
    private static File location = null;

    private SideNavigationPanel() {
        initialize();
    }

    private static void initialize() {
        sideNavePanel = new JPanel(new BorderLayout());
        load();
    }

    protected static void load() {
        try {
            loadFilesFromCurrentLocation();
            JScrollPane listScroller = new JScrollPane(loadFilesFromCurrentLocation());
            listScroller.setPreferredSize(new Dimension(80, 80));
            sideNavePanel.add(listScroller);
        } finally {
            sideNavePanel.validate();
        }
    }

    public static void reloadLocation(File newLocation) {
        location = newLocation;
        sideNavePanel.removeAll();
        load();
    }

    private static JList<String> loadFilesFromCurrentLocation() {
        DefaultListModel listModel = new DefaultListModel();
        JList<String> navList = null;

        if (location != null && location.exists() && !location.isFile()) {
            File[] files = location.listFiles();
            for (int i = 0; i < files.length; i++) {
                listModel.addElement(files[i].getName());
            }
            navList = new JList<>(listModel);
            navList.addListSelectionListener(fileClickedEvent(navList));
        } else {
            listModel.addElement("No Files Found");
        }
        return navList;
    }

    private static ListSelectionListener fileClickedEvent(JList<String> navList) {
        return e -> {
            if (!e.getValueIsAdjusting()) {
                final String selectedFileName = navList.getSelectedValue();
                // TODO: Eventually will open new tab
                if (selectedFileName != null && !selectedFileName.isEmpty()) {
                    String fileLocation = location + "\\" + selectedFileName;
                    File fileToOpen = new File(fileLocation);
                    NotesEditorPanel.setOpenFileInEditor(fileToOpen);
                }
            }
        };
    }

    public static synchronized JPanel getInstance() {
        if (sideNavePanel == null) {
            initialize();
        }
        return sideNavePanel;
    }

}
