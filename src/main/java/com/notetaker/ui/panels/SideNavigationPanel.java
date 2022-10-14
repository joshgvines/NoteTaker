package com.notetaker.ui.panels;

import javax.swing.*;
import java.awt.*;

public class SideNavigationPanel {

    private static JPanel sideNavePanel;

    private SideNavigationPanel() {
        initialize();
    }

    private static void initialize() {
        sideNavePanel = new JPanel(new BorderLayout());

        DefaultListModel listModel = new DefaultListModel();
        listModel.addElement("That Guy");

        JList<String> navList = new JList<>(listModel);
        JScrollPane listScroller = new JScrollPane(navList);
        listScroller.setPreferredSize(new Dimension(80, 80));

        sideNavePanel.add(listScroller);

    }

    public static synchronized JPanel getInstance() {
        if (sideNavePanel == null) {
            initialize();
        }
        return sideNavePanel;
    }

}
