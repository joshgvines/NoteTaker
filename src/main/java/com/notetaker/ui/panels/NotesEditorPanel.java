package com.notetaker.ui.panels;

import com.notetaker.service.ActionErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;

public class NotesEditorPanel {

    private static final Logger LOG = LogManager.getLogger(NotesEditorPanel.class);

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
            String msg = "Unable to load file into editor.";
            ActionErrorHandler.handleFailure(ex, msg, LOG, notesPanel);
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
