package com.notetaker.ui.panels;

import javax.swing.*;
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

    public static void reloadLocation(File newLocation) {
        location = newLocation;
        sideNavePanel.removeAll();
        load();
    }

    private static void load() {
        DefaultListModel listModel = new DefaultListModel();

        if (location != null) {
            File[] files = location.listFiles();
            for (int i = 0; i < files.length; i++) {
                listModel.addElement(files[i].getName());
            }
        } else {
            listModel.addElement("That Guy");
        }

        JList<String> navList = new JList<>(listModel);
        JScrollPane listScroller = new JScrollPane(navList);
        listScroller.setPreferredSize(new Dimension(80, 80));

        sideNavePanel.add(listScroller);
        sideNavePanel.validate();
    }

    public static synchronized JPanel getInstance() {
        if (sideNavePanel == null) {
            initialize();
        }
        return sideNavePanel;
    }

}
