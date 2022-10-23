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
        notesPanel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        notesPanel.add(new JScrollPane(textArea));
        load();
    }

    public static void load() {
        readFileIntoEditor();
        notesPanel.revalidate();
    }

    public static void setOpenFileInEditor(File fileToOpen) {
        currentFile = fileToOpen;
        load();
    }

    private static void readFileIntoEditor() {
        if (currentFile == null || !currentFile.isFile()) {
            textArea.setText(null);
            return;
        }
        try (FileReader fileReader = new FileReader(currentFile)) {
            textArea.read(fileReader, null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(notesPanel, "Unable To Load File Into Editor.");
            ex.printStackTrace();
        }
    }

    public static String getCurrentText() {
        return textArea.getText();
    }

    static synchronized JPanel getInstance() {
        if (notesPanel == null) {
            initialize();
        }
        return notesPanel;
    }
}
