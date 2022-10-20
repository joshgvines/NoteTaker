package com.notetaker.ui.menu;

import com.notetaker.ui.menu.actions.CreateNewFileAction;
import com.notetaker.ui.menu.actions.OpenFolderAction;
import com.notetaker.ui.menu.actions.UpdateExistingFileAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static com.notetaker.ui.menu.actions.UpdateExistingFileAction.UpdateFlag.IS_OVERWRITE;

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
    private static JMenuItem openFolder;

    private FileMenu() {
        initialize();
    }

    private static void initialize() {
        fileMenu = new JMenu(MENU_NAME);
        fileMenu.setMnemonic(KeyEvent.VK_F);
        load();
    }

    static void load() {
        try {
            buildMenuItems();
        } finally {
            fileMenu.revalidate();
        }
    }

    private static void buildMenuItems() {
        newNote = new JMenuItem(NEW_NOTE);
        exportNote = new JMenuItem(EXPORT_NOTE);
        saveNote = new JMenuItem(SAVE_NOTE);
        openFolder = new JMenuItem(OPEN_FOLDER);

        newNote.addActionListener(newNoteAction());
        exportNote.addActionListener(exportNoteAction());
        saveNote.addActionListener(saveNoteAction());
        openFolder.addActionListener(openFolderAction());

        fileMenu.add(newNote);
        fileMenu.add(exportNote);
        fileMenu.add(saveNote);
        fileMenu.add(openFolder);
    }

    private static ActionListener newNoteAction() {
        newNote.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        return new CreateNewFileAction();
    }

    private static ActionListener exportNoteAction() {
        exportNote.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        return e -> System.out.println("Export Note");
    }

    private static ActionListener saveNoteAction() {
        saveNote.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        return new UpdateExistingFileAction(IS_OVERWRITE, fileMenu);
    }

    private static ActionListener openFolderAction() {
        openFolder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        return new OpenFolderAction(getInstance());
    }

    static synchronized JMenu getInstance() {
        if (fileMenu == null) {
            initialize();
            return fileMenu;
        }
        return fileMenu;
    }

}
