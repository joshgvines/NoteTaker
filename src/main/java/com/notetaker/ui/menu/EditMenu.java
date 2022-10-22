package com.notetaker.ui.menu;

import com.notetaker.service.FileTreeService;
import com.notetaker.ui.menu.actions.DeleteFileAction;
import com.notetaker.ui.menu.actions.UpdateExistingFileAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static com.notetaker.ui.menu.actions.UpdateExistingFileAction.UpdateFlag;

class EditMenu extends JMenu {

    private static final String MENU_NAME = "Edit";
    private static final String EDIT_DELETE = "Delete";
    private static final String EDIT_RENAME = "Rename";

    private static JMenuItem deleteItem;
    private static JMenuItem renameItem;

    private FileTreeService fileTreeService;

    EditMenu(FileTreeService fileTreeService) {
        super(MENU_NAME);
        this.fileTreeService = fileTreeService;
        deleteItem = new JMenuItem(EDIT_DELETE);
        renameItem = new JMenuItem(EDIT_RENAME);
        initialize();
    }

    private void initialize() {
        this.setMnemonic(KeyEvent.VK_E);

        deleteItem.addActionListener(deleteFileAction());
        renameItem.addActionListener(renameFileAction());

        this.add(deleteItem);
        this.add(renameItem);
    }

    private ActionListener deleteFileAction() {
        deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        return new DeleteFileAction(fileTreeService);
    }

    private ActionListener renameFileAction() {
        renameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        return new UpdateExistingFileAction(fileTreeService, UpdateFlag.IS_NAME_CHANGE, this);
    }

}
