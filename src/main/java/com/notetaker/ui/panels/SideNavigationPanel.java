package com.notetaker.ui.panels;

import com.notetaker.service.FileTreeService;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SideNavigationPanel extends JPanel {

    // TODO: Better Default value
    private static File location;
    private static JScrollPane listScroller;
    private static FileTreeService fileTreeService;

    public SideNavigationPanel(FileTreeService fileTreeService) {
        super(new BorderLayout());
        this.fileTreeService = fileTreeService;
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
            getOSLocation();
            listScroller = new JScrollPane(fileTreeService.buildTree(location));
            listScroller.setPreferredSize(new Dimension(80, 80));
            this.add(listScroller);
        } finally {
            this.revalidate();
        }
    }

    private static void setView() {
        listScroller.setViewportView(fileTreeService.buildTree(location));
    }

    public static void setLocation(File newLocation) {
        location = newLocation;
        setView();
    }

}
