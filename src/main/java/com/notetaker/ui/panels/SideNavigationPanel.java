package com.notetaker.ui.panels;

import com.notetaker.service.FileTreeService;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SideNavigationPanel extends JPanel {

    // TODO: Better Default value
    private static File location;
    private static FileTreeService fileTreeService;
    private static JScrollPane listScroller;

    public SideNavigationPanel(FileTreeService fileTreeService) {
        super(new BorderLayout());
        this.fileTreeService = fileTreeService;
        getOSLocation();
        listScroller = new JScrollPane(fileTreeService.buildTree(location));
        listScroller.setPreferredSize(new Dimension(80, 80));
        initialize();
    }

    private void getOSLocation() {
        System.out.println(System.getProperty("os.name"));
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

    private void initialize() {
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
