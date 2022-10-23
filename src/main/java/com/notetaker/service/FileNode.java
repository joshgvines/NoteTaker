package com.notetaker.service;

import java.io.File;

public class FileNode {

    private File file;

    public FileNode(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    @Override
    /**
     * Allows the SideNavigationPanel to display file and directory names without showing any
     * information about the file object.
     */
    public String toString() {
        String name = file.getName();
        if (name.equals("")) {
            return file.getAbsolutePath();
        } else {
            return name;
        }
    }

}
