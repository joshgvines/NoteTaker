package com.notetaker.ui.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

class FileMenu {

    private static final String MENU_NAME = "File";
    private static final String NEW_NOTE = "New";
    private static final String EXPORT_NOTE = "Export";
    private static final String SAVE_NOTE = "Save";
    private static final String OPEN_FOLDER = "Open Folder";

    private static JMenu fileMenu;

    private static JMenuItem newNote;
    private static JMenuItem exportNote;
    private static JMenuItem saveNote;
    private static JMenuItem openProject;

    private FileMenu() {
        initialize();
    }

    private static void initialize() {
        fileMenu = new JMenu(MENU_NAME);
        fileMenu.setMnemonic(KeyEvent.VK_F);
        load();
    }

    protected static void load() {
        try {
            buildMenuItems();
        } finally {
            fileMenu.validate();
        }
    }

    private static void buildMenuItems() {
        newNote = new JMenuItem(NEW_NOTE);
        exportNote = new JMenuItem(EXPORT_NOTE);
        saveNote = new JMenuItem(SAVE_NOTE);
        openProject = new JMenuItem(OPEN_FOLDER);

        newNote.addActionListener(newNoteAction());
        exportNote.addActionListener(exportNoteAction());
        saveNote.addActionListener(saveNoteAction());
        openProject.addActionListener(openFolderAction());

        fileMenu.add(newNote);
        fileMenu.add(exportNote);
        fileMenu.add(saveNote);
        fileMenu.add(openProject);
    }

    private static ActionListener openFolderAction() {
        openProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        return new OpenFolderAction(fileMenu);
    }

    private static ActionListener newNoteAction() {
        newNote.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        return e -> System.out.println("New Note");
    }

    private static ActionListener exportNoteAction() {
        exportNote.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        return e -> System.out.println("Export Note");
    }

    private static ActionListener saveNoteAction() {
        saveNote.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        return e -> System.out.println("Save Note");
    }

    protected static synchronized JMenu getInstance() {
        if (fileMenu == null) {
            initialize();
            return fileMenu;
        }
        return fileMenu;
    }
}
