package com.notetaker.ui.menu.actions;

import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class MenuActionErrorHandler {

    static void handleFailure(Exception ex, String msg, final Logger LOG, Component parent) {
        LOG.error(msg, ex.getCause());
        LOG.debug(msg, ex);
        if (parent != null) {
            JOptionPane.showMessageDialog(parent, msg);
        }
    }

}
