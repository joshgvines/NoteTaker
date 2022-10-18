package com.notetaker.ui.panels;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;

public final class NotesEditorPanel {

    private static JPanel notesPanel;
    private static File currentFile;

    private NotesEditorPanel() {
        initialize();
    }

    private static void initialize() {
        notesPanel = new JPanel();
        notesPanel.setLayout(new BorderLayout());
        load();
    }

    protected static void load() {
        try {
            JScrollPane jScrollPane = new JScrollPane();
            jScrollPane.setViewportView(readFileIntoEditor());

            notesPanel.add(jScrollPane);
        } finally {
            notesPanel.validate();
        }
    }

    protected static void setOpenFileInEditor(File fileToOpen) {
        currentFile = fileToOpen;
        notesPanel.removeAll();
        load();
    }

    private static JTextArea readFileIntoEditor() {
        JTextArea textFile = new JTextArea();
        try {
            if (currentFile != null && currentFile.isFile()) {
                textFile.read(new FileReader(currentFile), null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return textFile;
    }

    public static synchronized JPanel getInstance() {
        if (notesPanel == null) {
            initialize();
        }
        return notesPanel;
    }
}
