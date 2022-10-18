package com.notetaker.ui.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

class FileMenu extends JMenu {

    private static final String MENU_NAME = "File";
    private static final String NEW_NOTE = "New";
    private static final String EXPORT_NOTE = "Export";
    private static final String SAVE_NOTE = "Save";
    private static final String OPEN_PROJECT = "Open Project";

    private JMenuItem newNote;
    private JMenuItem exportNote;
    private JMenuItem saveNote;
    private JMenuItem openProject;

    FileMenu() {
        super(MENU_NAME);
        initialize();
    }

    private void initialize() {
        this.setMnemonic(KeyEvent.VK_F);
        buildMenuItems();
    }

    private void buildMenuItems() {
        newNote = new JMenuItem(NEW_NOTE);
        exportNote = new JMenuItem(EXPORT_NOTE);
        saveNote = new JMenuItem(SAVE_NOTE);
        openProject = new JMenuItem(OPEN_PROJECT);

        newNote.addActionListener(newNoteAction());
        exportNote.addActionListener(exportNoteAction());
        saveNote.addActionListener(saveNoteAction());
        openProject.addActionListener(openProjectAction());

        this.add(newNote);
        this.add(exportNote);
        this.add(saveNote);
        this.add(openProject);
    }

    private ActionListener openProjectAction() {
        openProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        return new OpenProjectAction(this);
    }

    private ActionListener newNoteAction() {
        newNote.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        return e -> System.out.println("New Note");
    }

    private ActionListener exportNoteAction() {
        exportNote.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        return e -> System.out.println("Export Note");
    }

    private ActionListener saveNoteAction() {
        saveNote.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        return e -> System.out.println("Save Note");
    }

}
