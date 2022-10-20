package com.notetaker.ui.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

class EditMenu {

    private static final String MENU_NAME = "Edit";

    private static JMenu editMenu;
    private static JMenuItem deleteItem;

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
            deleteItem = new JMenuItem("Delete");

            deleteItem.addActionListener(deleteFileAction());

            editMenu.add(deleteItem);
        } finally {
            editMenu.revalidate();
        }
    }

    private static ActionListener deleteFileAction() {
        deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        return e -> System.out.println("delete");
    }

    static synchronized JMenu getInstance() {
        if (editMenu == null) {
            initialize();
        }
        return editMenu;
    }

}
