package com.notetaker.ui.menu;

import com.notetaker.ui.menu.actions.DeleteFileAction;
import com.notetaker.ui.menu.actions.UpdateExistingFileAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static com.notetaker.ui.menu.actions.UpdateExistingFileAction.UpdateFlag.IS_NAME_CHANGE;

class EditMenu {

    private static final String MENU_NAME = "Edit";
    private static final String EDIT_DELETE = "Delete";
    private static final String EDIT_RENAME = "Rename";

    private static JMenu editMenu;
    private static JMenuItem deleteItem;
    private static JMenuItem renameItem;

    private EditMenu() {
        initialize();
    }

    private static void initialize() {
        editMenu = new JMenu(MENU_NAME);
        editMenu.setMnemonic(KeyEvent.VK_E);
        load();
    }

    static void load() {
        try {
            deleteItem = new JMenuItem(EDIT_DELETE);
            renameItem = new JMenuItem(EDIT_RENAME);

            deleteItem.addActionListener(deleteFileAction());
            renameItem.addActionListener(renameFileAction());

            editMenu.add(deleteItem);
            editMenu.add(renameItem);
        } finally {
            editMenu.revalidate();
        }
    }

    private static ActionListener deleteFileAction() {
        deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        return new DeleteFileAction();
    }

    private static ActionListener renameFileAction() {
        renameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        return new UpdateExistingFileAction(IS_NAME_CHANGE, editMenu);
    }

    static synchronized JMenu getInstance() {
        if (editMenu == null) {
            initialize();
        }
        return editMenu;
    }

}
