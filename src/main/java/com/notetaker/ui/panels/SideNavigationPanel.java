package com.notetaker.ui.panels;

import com.notetaker.service.TreeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SideNavigationPanel extends JPanel {

    private static final Logger LOG = LogManager.getLogger(SideNavigationPanel.class);

    // TODO: Better Default value
    private static File location;
    private static JScrollPane listScroller;
    private static TreeService treeService;

    public SideNavigationPanel(TreeService treeService) {
        super(new BorderLayout());
        this.treeService = treeService;
        listScroller = new JScrollPane();
        initialize();
    }

    private void initialize() {
        try {
            getOSLocation();
            setLocation(location);
            listScroller.setPreferredSize(new Dimension(80, 80));
            this.add(listScroller);
        } finally {
            this.revalidate();
        }
    }

    private void getOSLocation() {
        LOG.debug("OS: " + System.getProperty("os.name"));
        switch (System.getProperty("os.name")) {
            case "Linux":
                break;
            case "Windows":
            case "Windows 8":
            case "Windows 10":
            case "Windows 11":
                String userName = System.getProperty("user.name");
                location = new File(File.separator + "Users" +
                        File.separator + userName +
                        File.separator);
                break;
            default:
                System.err.println("Unknown Operating System.");
        }
    }

    public static void setLocation(File newLocation) {
        LOG.debug("Setting Location: " + newLocation.getAbsolutePath());
        location = newLocation;
        listScroller.setViewportView(treeService.buildTree(location));
    }

}
