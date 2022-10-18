package com.notetaker.ui.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.Field;

public class OpenProjectAction implements ActionListener {

    private FileMenu menu;

    public OpenProjectAction(FileMenu menu) {
        this.menu = menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fChooser = new JFileChooser();

        fChooser.setCurrentDirectory(new File("."));
//        fChooser.setSelectedFile();
        fChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnVal = fChooser.showOpenDialog(menu);
    }
}
