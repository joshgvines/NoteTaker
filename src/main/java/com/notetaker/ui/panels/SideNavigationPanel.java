package com.notetaker.ui.panels;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.File;

public class SideNavigationPanel {

    private static JPanel sideNavePanel;
    private static File location = null;
    private static JList<String> navList = null;

    private SideNavigationPanel() {
        initialize();
    }

    private static void initialize() {
        sideNavePanel = new JPanel(new BorderLayout());
        load();
    }

    public static void reloadLocation(File newLocation) {
        location = newLocation;
        sideNavePanel.removeAll();
        load();
    }

    private static void load() {
        loadFiles();
        JScrollPane listScroller = new JScrollPane(navList);
        listScroller.setPreferredSize(new Dimension(80, 80));

        sideNavePanel.add(listScroller);
        sideNavePanel.validate();
    }

    private static void loadFiles() {
        DefaultListModel listModel = new DefaultListModel();

        if (location != null) {
            File[] files = location.listFiles();
            for (int i = 0; i < files.length; i++) {
                listModel.addElement(files[i].getName());
            }
            navList = new JList<>(listModel);
            navList.addListSelectionListener(fileClickedEvent());
        } else {
            listModel.addElement("No Files Found");
        }
    }

    private static ListSelectionListener fileClickedEvent() {
        return e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedValue = navList.getSelectedValue();
                // TODO: Eventually will open new tab
                if (selectedValue != null && !selectedValue.isEmpty()) {
                    System.out.println(location + "\\" + selectedValue);
                    File fileToOpen = new File(location + "\\" + selectedValue);
                    NotesEditorPanel.reloadOpenFile(fileToOpen);
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
