package com.notetaker.ui.panels;

import javax.swing.*;
import java.awt.*;

public class NotesEditorPanel {

    private static JPanel notesPanel;

    private NotesEditorPanel() {
        initialize();
    }

    private static void initialize() {
        notesPanel = new JPanel();
        notesPanel.setLayout(new BorderLayout());

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(new JTextArea());
        notesPanel.add(jScrollPane);
    }

    public static synchronized JPanel getInstance() {
        if (notesPanel == null) {
            initialize();
        }
        return notesPanel;
    }

}
