package com.notetaker.ui.menu;

import com.notetaker.ui.panels.SideNavigationPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenProjectAction implements ActionListener {

    private FileMenu menu;

    protected OpenProjectAction(FileMenu menu) {
        this.menu = menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fChooser = new JFileChooser();
        fChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (fChooser.showOpenDialog(menu) == JFileChooser.APPROVE_OPTION) {
            SideNavigationPanel.reloadLocation(fChooser.getSelectedFile());
        }
    }
}
