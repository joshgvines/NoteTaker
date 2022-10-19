package com.notetaker.ui.panels;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;

public class NotesEditorPanel {

    private static JPanel notesPanel;
    private static File currentFile;
    private static JTextArea textArea;

    private NotesEditorPanel() {
        initialize();
    }

    private static void initialize() {
        notesPanel = new JPanel();
        notesPanel.setLayout(new BorderLayout());
        textArea = new JTextArea();
        load();
    }

    public static void load() {
        try {
            notesPanel.removeAll();
            JScrollPane jScrollPane = new JScrollPane();
            readFileIntoEditor();
            jScrollPane.setViewportView(textArea);
            notesPanel.add(jScrollPane);
        } finally {
            notesPanel.revalidate();
        }
    }

    protected static void setOpenFileInEditor(File fileToOpen) {
        currentFile = fileToOpen;
    }

    public static File getOpenFileInEditor() {
        return currentFile;
    }

    private static void readFileIntoEditor() {
        if (currentFile == null || !currentFile.isFile()) {
            textArea.setText(null);
            return;
        }
        try (FileReader fileReader = new FileReader(currentFile)) {
            textArea.read(fileReader, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getCurrentText() {
        return textArea.getText();
    }

    public static synchronized JPanel getInstance() {
        if (notesPanel == null) {
            initialize();
        }
        return notesPanel;
    }
}
