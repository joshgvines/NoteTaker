package com.notetaker.ui.menu;

import com.notetaker.service.TreeService;
import com.notetaker.ui.menu.actions.CreateNewFileAction;
import com.notetaker.ui.menu.actions.OpenFolderAction;
import com.notetaker.ui.menu.actions.UpdateExistingFileAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static com.notetaker.ui.menu.actions.UpdateExistingFileAction.UpdateFlag;

class FileMenu extends JMenu {

    private static final String MENU_NAME = "File";
    private static final String NEW_NOTE = "New";
    private static final String EXPORT_NOTE = "Export";
    private static final String SAVE_NOTE = "Save";
    private static final String OPEN_FOLDER = "Open Folder";

    private JMenuItem newNote;
    private JMenuItem exportNote;
    private JMenuItem saveNote;
    private JMenuItem openFolder;

    private TreeService treeService;

    FileMenu(TreeService treeService) {
        super(MENU_NAME);
        this.treeService = treeService;
        newNote = new JMenuItem(NEW_NOTE);
        exportNote = new JMenuItem(EXPORT_NOTE);
        saveNote = new JMenuItem(SAVE_NOTE);
        openFolder = new JMenuItem(OPEN_FOLDER);
        initialize();
    }

    private void initialize() {
        this.setMnemonic(KeyEvent.VK_F);

        newNote.addActionListener(newNoteAction());
        exportNote.addActionListener(exportNoteAction());
        saveNote.addActionListener(saveNoteAction());
        openFolder.addActionListener(openFolderAction());

        this.add(newNote);
        this.add(exportNote);
        this.add(saveNote);
        this.add(openFolder);
    }

    private ActionListener newNoteAction() {
        newNote.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        return new CreateNewFileAction(treeService);
    }

    private ActionListener exportNoteAction() {
        exportNote.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        return e -> System.out.println("Export Note");
    }

    private ActionListener saveNoteAction() {
        saveNote.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        return new UpdateExistingFileAction(treeService, UpdateFlag.IS_OVERWRITE, this);
    }

    private ActionListener openFolderAction() {
        openFolder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        return new OpenFolderAction(treeService, this);
    }

}
