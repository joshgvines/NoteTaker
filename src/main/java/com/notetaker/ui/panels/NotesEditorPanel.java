package com.notetaker.ui.panels;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;

public class NotesEditorPanel {

    private static JPanel notesPanel;
    private static File currentFile = null;

    private NotesEditorPanel() {
        initialize();
    }

    private static void initialize() {
        notesPanel = new JPanel();
        notesPanel.setLayout(new BorderLayout());
        load();
    }

    public static void reloadOpenFile(File fileToOpen) {
        currentFile = fileToOpen;
        notesPanel.removeAll();
        load();
    }

    private static void load() {
        JScrollPane jScrollPane = new JScrollPane();
        try {
            JTextArea textFile = new JTextArea();
            textFile.read(new FileReader(currentFile), null);
            jScrollPane.setViewportView(textFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        notesPanel.add(jScrollPane);
        notesPanel.validate();
    }

    public static synchronized JPanel getInstance() {
        if (notesPanel == null) {
            initialize();
        }
        return notesPanel;
    }
}
